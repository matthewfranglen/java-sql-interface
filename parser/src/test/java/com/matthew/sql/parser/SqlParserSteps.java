package com.matthew.sql.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.IOException;

import com.matthew.sql.statement.SqlStatement;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SqlParserSteps {

    private SqlStatementLoader loader;

    private File file, directory;
    private String resource;
    private SqlStatement statement;

    @Before
    public void setUp() {
        loader = new SqlStatementLoader();
    }

    @Given("^the file \"(.*)\" and the directory \"(.*)\"$")
    public void givenTheFileAndTheDirectory(String file, String directory) throws IOException {
        this.file = new File(directory, file);
        this.directory = new File(directory);
    }

    @Given("^the resource \"(.*)\"$")
    public void givenTheResource(String resource) throws IOException {
        this.resource = resource;
    }

    @When("^the file is loaded as a statement$")
    public void whenTheFileIsLoadedAsAStatement() throws IOException {
        statement = loader.parseFile(directory, file);
    }

    @When("^the resource is loaded as a statement$")
    public void whenTheResourceIsLoadedAsAStatement() throws IOException {
        statement = loader.parseResource(resource);
    }

    @Then("^the loaded statement takes (\\d+) arguments$")
    public void thenTheLoadedStatementTakesSomeArguments(int count) {
        assertEquals(count, statement.getTakes().size());
    }

    @Then("^the loaded statement takes does not have a list of taken arguments$")
    public void thenTheLoadedStatementTakesDoesNotHaveAListOfTakenArguments() {
        assertFalse(statement.hasTakes());
    }

    @Then("^he loaded statement takes does not have a list of returned arguments$")
    public void thenTheLoadedStatementTakesDoesNotHaveAListOfReturnedArguments() {
        assertFalse(statement.hasReturns());
    }

    @Then("^the loaded statement returns (\\d+) arguments$")
    public void thenTheLoadedStatementReturnsArguments(int count) {
        assertEquals(count, statement.getReturns().size());
    }

    @Then("^the loaded statement body is \"(.*)\"$")
    public void thenTheLoadedStatementBodyIs(String body) {
        assertEquals(body, statement.getStatement());
    }

    @Then("^the loaded statement name is \"(.*)\"$")
    public void thenTheLoadedStatementNameIs(String name) {
        assertEquals(name, statement.getName());
    }

    @Then("^the loaded statement package is \"(.*)\"$")
    public void thenTheLoadedStatementPackageIs(String _package) {
        assertEquals(_package, statement.getPackage());
    }

    @Then("^the loaded statement path is \"(.*)\"$")
    public void thenTheLoadedStatementPathIs(String path) {
        assertEquals(path, statement.getPath());
    }

}
