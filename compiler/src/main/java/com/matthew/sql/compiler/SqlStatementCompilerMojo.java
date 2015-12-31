package com.matthew.sql.compiler;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.maven.model.FileSet;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.matthew.sql.compiler.reader.StatementReader;
import com.matthew.sql.compiler.writer.StatementHandlerWriter;
import com.matthew.sql.compiler.writer.StatementWriter;
import com.matthew.sql.parser.SqlStatement;

@Mojo(name="compile", defaultPhase=LifecyclePhase.GENERATE_SOURCES)
public class SqlStatementCompilerMojo extends AbstractMojo {

    private static final String OUTPUT_FOLDER = "target/generated-sources/sql-statements";

    @Parameter(defaultValue="${project}", readonly=true)
    private MavenProject project;

    @Parameter(required=true)
    private FileSet statements;

    public void execute() throws MojoExecutionException, MojoFailureException {
        String projectRootDirectory;
        String generatedSourcesDirectory;

        try {
            projectRootDirectory = project.getBasedir().getCanonicalPath();
            generatedSourcesDirectory = new File(projectRootDirectory, OUTPUT_FOLDER).getCanonicalPath();
        }
        catch (IOException e) {
            throw new MojoExecutionException("Unable to read directory path", e);
        }

        try {
            StatementReader reader = new StatementReader(projectRootDirectory, statements.getDirectory(), statements.getIncludes(), statements.getExcludes());
            StatementWriter statementWriter = new StatementWriter(getLog(), generatedSourcesDirectory);
            StatementHandlerWriter statementHandlerWriter = new StatementHandlerWriter(getLog(), generatedSourcesDirectory);

            Collection<SqlStatement> loadedStatements = reader.getStatements();

            statementWriter.writeStatements(loadedStatements);
            statementHandlerWriter.writeStatementHandler(loadedStatements);

            getLog().info("Completed SQL Statement Compilation");
        }
        catch (Exception e) {
            throw new MojoExecutionException("Failed to compile SQL Statements", e);
        }

        project.addCompileSourceRoot(generatedSourcesDirectory);
    }

}
