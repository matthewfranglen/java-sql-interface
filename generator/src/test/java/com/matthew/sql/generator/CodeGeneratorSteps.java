package com.matthew.sql.generator;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.matthew.sql.statement.SqlStatement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CodeGeneratorSteps {

    private String name;
    private String path;
    private String statement;
    private String code;

    @Given("^the name \"(.*)\"$")
    public void givenTheName(String name) throws IOException {
        this.name = name;
    }

    @Given("^the path \"(.*)\"$")
    public void givenThePath(String path) throws IOException {
        this.path = path;
    }

    @Given("^the statement \"(.*)\"$")
    public void givenTheStatement(String file) throws IOException {
        statement = readResource(file);
    }

    @When("^the statement is converted to a statement handler class$")
    public void whenTheStatementIsConvertedToAStatementHandlerClass() throws Exception {
        SqlStatement statementObject = new SqlStatement(name, path, statement);
        code = new CodeGenerator().generateStatementHandlerCode(Arrays.asList(statementObject));
    }

    @When("^the statement is converted to a statement class$")
    public void whenTheStatementIsConvertedToAStatementClass() throws Exception {
        SqlStatement statementObject = new SqlStatement(name, path, statement);
        code = new CodeGenerator().generateStatementCode(statementObject);
    }

    @Then("^the code looks like \"(.*)\"$")
    public void thenTheCodeLooksLike(String file) throws IOException {
        String expected = readResource(file);
        assertEquals(expected, code);
    }

    private String readResource(String resource) throws IOException {
        return Resources.toString(Resources.getResource(resource), Charsets.UTF_8);
    }

}
