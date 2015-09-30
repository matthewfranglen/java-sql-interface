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
import com.matthew.sql.parser.SqlParser;
import com.matthew.sql.parser.SqlStatement;

@Mojo(name = "compiler", defaultPhase = LifecyclePhase.VALIDATE)
public class SqlStatementCompilerMojo extends AbstractMojo {

    @Parameter
    private FileSet statements;

    private final SqlParser parser;
    private final CodeGenerator generator;

    public SqlStatementCompilerMojo() {
        parser = new SqlParser();
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
        return getStatementFiles().stream()
            .map(this::parseStatementFile)
            .collect(Collectors.toList());
    }

    private Collection<File> getStatementFiles() throws IOException {
        File directory = new File(statements.getDirectory());
        String includes = statements.getIncludes()
            .stream()
            .collect(Collectors.joining(","));
        String excludes = statements.getIncludes()
            .stream()
            .collect(Collectors.joining(","));

        return FileUtils.getFiles(directory, includes, excludes);
    }

    private SqlStatement parseStatementFile(File statement) {
        try {
            return parser.parse(statement);
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
