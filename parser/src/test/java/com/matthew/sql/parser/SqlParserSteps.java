package com.matthew.sql.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SqlParserSteps {

    private String resource;
    private SqlStatement statement;

    @Given("^the resource \"(.*)\"$")
    public void givenTheResource(String file) throws IOException {
        resource = file;
    }

    @When("^the resource is loaded as a statement$")
    public void whenTheResourceIsLoadedAsAStatement() throws IOException {
        statement = new SqlParser().parseResource(resource);
    }

    @Then("^the loaded statement argument count is (\\d+)$")
    public void thenTheLoadedStatementArgumentCountIs(int count) {
        fail("TBC");
    }

    @Then("^the loaded statement body is \"(.*)\"$")
    public void thenTheLoadedStatementBodyIs(String body) {
        assertEquals(body, statement.getStatement());
    }

    @Then("^the loaded statement name is \"(.*)\"$")
    public void thenTheLoadedStatementNameIs(String name) {
        assertEquals(name, statement.getName());
    }

}
