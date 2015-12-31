package com.matthew.sql.compiler.writer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Collections;

import org.apache.maven.plugin.logging.Log;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.matthew.sql.compiler.reader.StatementReader;
import com.matthew.sql.parser.SqlStatement;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import freemarker.template.TemplateException;

public class StatementWriterSteps {

    private static int temporaryFolderCounter = 0;

    private Path testFolder;

    private Collection<SqlStatement> statements;
    private File generatedSourcesDirectory;

    @Before
    public void init() throws IOException {
        testFolder = java.nio.file.Files.createTempDirectory(null);
    }

    @After
    public void deinit() throws IOException {
        delete(testFolder.toFile());
    }

    private File makeTemporaryFolder() throws IOException {
        File result = new File(testFolder.toFile(), String.valueOf(temporaryFolderCounter));
        temporaryFolderCounter++;

        if (! result.mkdir()) {
            throw new IOException("Failed to create folder: " + result);
        }
        return result;
    }

    private void delete(File file) throws IOException {
        if (file.isDirectory()) {
            for (File current : file.listFiles()) {
                delete(current);
            }
        }

        if (! file.delete()) {
            throw new IOException("Failed to delete file: " + file);
        }
    }

    @Given("^the statement loaded from the resource \"(.*)\"$")
    public void givenTheStatementLoadedFromTheResource(String resource) throws IOException {
        Collection<String> includes = Collections.singletonList(resource);
        Collection<String> excludes = Collections.emptyList();
        StatementReader reader = new StatementReader(".", "src/test/resources", includes, excludes);

        statements = reader.getStatements();
    }

    @When("^the statement is written to a file$")
    public void whenTheStatementIsWrittenToAFile() throws IOException, TemplateException {
        generatedSourcesDirectory = makeTemporaryFolder();
        StatementWriter writer = new StatementWriter(mock(Log.class), generatedSourcesDirectory.getCanonicalPath());

        writer.writeStatements(statements);
    }

    @Then("^the file at \"(.*)\" looks like the resource \"(.*)\"$")
    public void thenTheFileLooksLike(String path, String resource) throws IOException {
        String expected = Resources.toString(Resources.getResource(resource), Charsets.UTF_8);
        String actual = Files.toString(new File(generatedSourcesDirectory, path), Charsets.UTF_8);

        assertEquals(expected, actual);
    }

}
