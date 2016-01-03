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
import com.matthew.sql.statement.SqlStatementBuilder;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CodeGeneratorSteps {

    private SqlStatementBuilder statementBuilder;
    private String code;

    @Before
    public void initialize() {
        statementBuilder = new SqlStatementBuilder();
    }

    @Given("^the name \"(.*)\"$")
    public void givenTheName(String name) throws IOException {
        statementBuilder.setName(name);
    }

    @Given("^the path \"(.*)\"$")
    public void givenThePath(String path) throws IOException {
        statementBuilder.setPath(path);
    }

    @Given("^the statement \"(.*)\"$")
    public void givenTheStatement(String file) throws IOException {
        statementBuilder.setStatement(readResource(file));
    }

    @Given("^the takes \"(.*)\"$")
    public void givenTheTakes(List<String> takes) {
        statementBuilder.setTakes(parseArgumentList(takes));
    }

    @Given("^the returns \"(.*)\"$")
    public void givenTheReturns(List<String> returns) {
        statementBuilder.setReturns(parseArgumentList(returns));
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
        SqlStatement statementObject = statementBuilder.build();
        code = new CodeGenerator().generateStatementHandlerCode(Arrays.asList(statementObject));
    }

    @When("^the statement is converted to a statement class$")
    public void whenTheStatementIsConvertedToAStatementClass() throws Exception {
        SqlStatement statementObject = statementBuilder.build();
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
