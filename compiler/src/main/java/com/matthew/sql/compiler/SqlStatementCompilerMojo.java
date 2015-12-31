package com.matthew.sql.compiler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.maven.model.FileSet;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.FileUtils;

import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.io.Files;
import com.matthew.sql.generator.CodeGenerator;
import com.matthew.sql.parser.SqlStatement;
import com.matthew.sql.parser.SqlStatementLoader;

@Mojo(name = "compile", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class SqlStatementCompilerMojo extends AbstractMojo {

    private static final String OUTPUT_FOLDER = "target/generated-sources/sql-statements";

    @Parameter( defaultValue = "${project}", readonly = true )
    private MavenProject project;

    @Parameter( required = true )
    private FileSet statements;

    private final SqlStatementLoader loader;
    private final CodeGenerator generator;

    private String projectRootDirectory;
    private String generatedSourcesDirectory;

    public SqlStatementCompilerMojo() {
        loader = new SqlStatementLoader();
        generator = new CodeGenerator();
    }

    private void initialize() throws MojoExecutionException {
        try {
            projectRootDirectory = project.getBasedir().getCanonicalPath();
            generatedSourcesDirectory = new File(projectRootDirectory, OUTPUT_FOLDER).getCanonicalPath();
        }
        catch (IOException e) {
            throw new MojoExecutionException("Unable to read directory path", e);
        }
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        initialize();

        try {
            Collection<SqlStatement> statements = getStatements();

            writeStatementCode(statements);
            writeStatementHandlerCode(statements);

            getLog().info("Completed SQL Statement Compilation");
        }
        catch (Exception e) {
            throw new MojoExecutionException("Failed to compile SQL Statements", e);
        }

        project.addCompileSourceRoot(generatedSourcesDirectory);
    }

    private Collection<SqlStatement> getStatements() throws IOException {
        File rootDirectory = new File(projectRootDirectory, statements.getDirectory());
        Collection<File> statementFiles = getStatementFiles(rootDirectory);

        return parseStatementFileList(rootDirectory, statementFiles);
    }

    private Collection<File> getStatementFiles(File directory) throws IOException {
        Joiner joiner = Joiner.on(",");
        String includes = joiner.join(statements.getIncludes());
        String excludes = joiner.join(statements.getExcludes());

        return FileUtils.getFiles(directory, includes, excludes);
    }

    private Collection<SqlStatement> parseStatementFileList(File rootDirectory, Collection<File> statementFiles) {
        Collection<SqlStatement> result = new ArrayList<>(statementFiles.size());

        for (File current : statementFiles) {
            result.add(parseStatementFile(rootDirectory, current));
        }

        return result;
    }

    private SqlStatement parseStatementFile(File rootDirectory, File statement) {
        try {
            return loader.parseFile(rootDirectory, statement);
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void writeStatementCode(Collection<SqlStatement> statements) throws Exception {
        for (SqlStatement current : statements) {
            writeStatementCode(makeStatementCode(current));
        }
    }

    private GeneratedCode makeStatementCode(SqlStatement statement) {
        try {
            return new GeneratedCode(statement, generator.generateStatementCode(statement));
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void writeStatementCode(GeneratedCode code) {
        String path = String.format("%s/%s.java", code.getStatement().getPath(), code.getStatement().getName());
        File file = new File(generatedSourcesDirectory, path);

        write(file, code.getCode());
        getLog().info(String.format("Written Statement: %s.%s", code.getStatement().getPackage(), code.getStatement().getName()));
    }

    private void writeStatementHandlerCode(Collection<SqlStatement> statements) throws Exception {
        String path = "com/matthew/sql/generated/SqlStatementHandler.java";
        File file = new File(generatedSourcesDirectory, path);
        String code = generator.generateStatementHandlerCode(statements);

        write(file, code);
        getLog().info("Written Statement Handler: com.matthew.sql.generated.SqlStatementHandler");
    }

    private void write(File file, String content) {
        try {
            file.getParentFile().mkdirs();
            Files.write(content, file, Charsets.UTF_8);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
