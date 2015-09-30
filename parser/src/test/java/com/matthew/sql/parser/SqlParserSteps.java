package com.matthew.sql.parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SqlParserSteps {

    @Spy private SqlParser parser;
    @InjectMocks private SqlStatementLoader loader;

    private String resource;
    private SqlStatement statement;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Given("^the resource \"(.*)\"$")
    public void givenTheResource(String file) throws IOException {
        resource = file;
    }

    @When("^the resource is loaded as a statement$")
    public void whenTheResourceIsLoadedAsAStatement() throws IOException {
        statement = loader.parseResource(resource);
    }

    @Then("^the loaded statement argument count is (\\d+)$")
    public void thenTheLoadedStatementArgumentCountIs(int count) {
        throw new PendingException();
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

}
