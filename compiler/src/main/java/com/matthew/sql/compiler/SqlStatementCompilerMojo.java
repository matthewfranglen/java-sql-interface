package com.matthew.sql.compiler;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.stream.Collectors;

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

    private String basedir;

    public SqlStatementCompilerMojo() {
        loader = new SqlStatementLoader();
        generator = new CodeGenerator();
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            basedir = project.getBasedir().getCanonicalPath();
        }
        catch (IOException e) {
            throw new MojoExecutionException("Unable to read project base directory path", e);
        }

        try {
            Collection<SqlStatement> statements = getStatements();

            writeStatementCode(statements);
            writeStatementHandlerCode(statements);

            getLog().info("Completed SQL Statement Compilation");
        }
        catch (Exception e) {
            throw new MojoExecutionException("Failed to compile SQL Statements", e);
        }
    }

    private Collection<SqlStatement> getStatements() throws IOException {
        File rootDirectory = new File(basedir, statements.getDirectory());

        return getStatementFiles(rootDirectory).stream()
            .map((File file) -> parseStatementFile(rootDirectory, file))
            .collect(Collectors.toList());
    }

    private Collection<File> getStatementFiles(File directory) throws IOException {
        String includes = statements.getIncludes()
            .stream()
            .collect(Collectors.joining(","));
        String excludes = statements.getExcludes()
            .stream()
            .collect(Collectors.joining(","));

        return FileUtils.getFiles(directory, includes, excludes);
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
        statements.stream()
            .map(this::makeStatementCode)
            .forEach(this::writeStatementCode);
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
        String path = String.format("%s/%s/%s.java", OUTPUT_FOLDER, code.getStatement().getPath(), code.getStatement().getName());
        File file = new File(basedir, path);

        write(file, code.getCode());
        getLog().info(String.format("Written Statement: %s.%s", code.getStatement().getPackage(), code.getStatement().getName()));
    }

    private void writeStatementHandlerCode(Collection<SqlStatement> statements) throws Exception {
        String path = String.format("%s/com/matthew/sql/generated/SqlStatementHandler.java", OUTPUT_FOLDER);
        File file = new File(basedir, path);
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
