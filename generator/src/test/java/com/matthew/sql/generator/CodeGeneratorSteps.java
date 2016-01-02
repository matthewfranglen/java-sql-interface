package com.matthew.sql.generator;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.matthew.sql.statement.Argument;
import com.matthew.sql.statement.ArgumentType;
import com.matthew.sql.statement.SqlStatement;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CodeGeneratorSteps {

    private String name;
    private String path;
    private String statement;
    private String code;
    private List<Argument> takes;
    private List<Argument> returns;

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

    @Given("^the takes \"(.*)\"$")
    public void givenTheTakes(List<String> takes) {
        this.takes = parseArgumentList(takes);
    }

    @Given("^the returns \"(.*)\"$")
    public void givenTheReturns(List<String> returns) {
        this.returns = parseArgumentList(returns);
    }

    private List<Argument> parseArgumentList(List<String> arguments) {
        List<Argument> result = new ArrayList<>();

        for (String argument : arguments) {
            result.add(parseArgument(argument));
        }

        return result;
    }

    private Argument parseArgument(String argument) {
        String[] split = argument.trim().split(" +", 2);
        return new Argument(ArgumentType.valueOf(split[0].trim()), split[1].trim());
    }

    @When("^the statement is converted to a statement handler class$")
    public void whenTheStatementIsConvertedToAStatementHandlerClass() throws Exception {
        SqlStatement statementObject = new SqlStatement(name, path, statement, takes, returns);
        code = new CodeGenerator().generateStatementHandlerCode(Arrays.asList(statementObject));
    }

    @When("^the statement is converted to a statement class$")
    public void whenTheStatementIsConvertedToAStatementClass() throws Exception {
        SqlStatement statementObject = new SqlStatement(name, path, statement, takes, returns);
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
