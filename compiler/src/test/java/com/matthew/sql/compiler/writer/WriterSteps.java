package com.matthew.sql.compiler.writer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.apache.maven.plugin.logging.Log;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.matthew.sql.compiler.reader.StatementReader;
import com.matthew.sql.statement.SqlStatement;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import freemarker.template.TemplateException;

public class WriterSteps {

    private static final TemporaryFileManager temporaryFileManager = new TemporaryFileManager();

    private Collection<SqlStatement> statements;
    private File generatedSourcesDirectory;

    @Before
    public void init() throws IOException {
        temporaryFileManager.create();
    }

    @After
    public void deinit() throws IOException {
        temporaryFileManager.destroy();
    }

    @Given("^the statements loaded using the inclusion pattern \"(.*)\"$")
    public void givenTheStatementsLoadedUsingTheInclusionPattern(String include) throws IOException {
        Collection<String> includes = Collections.singletonList(include);
        Collection<String> excludes = Collections.emptyList();
        StatementReader reader = new StatementReader(".", "src/test/resources", includes, excludes);

        statements = reader.getStatements();
    }

    @When("^the statement is written to a file$")
    public void whenTheStatementIsWrittenToAFile() throws IOException, TemplateException {
        generatedSourcesDirectory = temporaryFileManager.makeTemporaryFolder();
        StatementWriter writer = new StatementWriter(mock(Log.class), generatedSourcesDirectory.getCanonicalPath());

        writer.writeStatements(statements);
    }

    @When("^the statement handler is written to a file$")
    public void whenTheStatementHandlerIsWrittenToAFile() throws IOException, TemplateException {
        generatedSourcesDirectory = temporaryFileManager.makeTemporaryFolder();
        StatementHandlerWriter writer = new StatementHandlerWriter(mock(Log.class), generatedSourcesDirectory.getCanonicalPath());

        writer.writeStatementHandler(statements);
    }

    @Then("^the file at \"(.*)\" looks like the resource \"(.*)\"$")
    public void thenTheFileLooksLike(String path, String resource) throws IOException {
        String expected = Resources.toString(Resources.getResource(resource), Charsets.UTF_8);
        String actual = Files.toString(new File(generatedSourcesDirectory, path), Charsets.UTF_8);

        assertEquals(expected, actual);
    }

    @Then("^the statement handler file looks like the resource \"(.*)\"$")
    public void thenTheStatementHandlerFileLooksLikeTheResource(String resource) throws IOException {
        String expected = Resources.toString(Resources.getResource(resource), Charsets.UTF_8);
        String actual = Files.toString(new File(generatedSourcesDirectory, StatementHandlerWriter.STATEMENT_HANDLER_PATH), Charsets.UTF_8);

        assertEquals(expected, actual);
    }

}
