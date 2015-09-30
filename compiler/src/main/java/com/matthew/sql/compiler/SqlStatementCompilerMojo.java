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
import org.codehaus.plexus.util.FileUtils;

import com.matthew.sql.generator.CodeGenerator;
import com.matthew.sql.parser.SqlStatement;
import com.matthew.sql.parser.SqlStatementLoader;

@Mojo(name = "compiler", defaultPhase = LifecyclePhase.VALIDATE)
public class SqlStatementCompilerMojo extends AbstractMojo {

    @Parameter
    private FileSet statements;

    private final SqlStatementLoader loader;
    private final CodeGenerator generator;

    public SqlStatementCompilerMojo() {
        loader = new SqlStatementLoader();
        generator = new CodeGenerator();
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
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
        File rootDirectory = new File(statements.getDirectory());

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
        getLog().info(code.getCode());
    }

    private void writeStatementHandlerCode(Collection<SqlStatement> statements) throws Exception {
        String code = generator.generateStatementHandlerCode(statements);

        getLog().info(code);
    }

}
