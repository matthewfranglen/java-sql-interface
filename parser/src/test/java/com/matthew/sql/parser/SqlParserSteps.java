package com.matthew.sql.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SqlParserSteps {

    private String resource;
    private String source;
    private SqlStatement statement;

    @Test
    public void testParser() throws IOException {
        String source = Resources.toString(Resources.getResource("statement.sql"), Charsets.UTF_8);
        SqlParser parser = new SqlParser();
        SqlStatement statement = parser.parse(source);

        assertEquals(statement.getName(), "sample statement");
        assertEquals(statement.getStatement(), "SELECT * FROM table LIMIT 1;");
    }

    @Given("^the resource \"(.*)\"$")
    public void givenTheResource(String file) throws IOException {
        resource = "statements/" + file;
        source = Resources.toString(Resources.getResource(resource), Charsets.UTF_8);
    }

    @When("^the resource is loaded as a statement$")
    public void whenTheResourceIsLoadedAsAStatement() {
        statement = new SqlParser().parse(source);
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
