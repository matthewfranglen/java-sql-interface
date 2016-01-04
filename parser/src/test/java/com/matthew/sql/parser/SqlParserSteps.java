package com.matthew.sql.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import com.matthew.sql.statement.ArgumentType;
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

    @Then("^the loaded statement has a list of taken arguments$")
    public void thenTheLoadedStatementTakesHasAListOfTakenArguments() {
        assertTrue(statement.getTakes().isDefined());
    }

    @Then("^the loaded statement does not have a list of taken arguments$")
    public void thenTheLoadedStatementTakesDoesNotHaveAListOfTakenArguments() {
        assertFalse(statement.getTakes().isDefined());
    }

    @Then("^the loaded statement taken argument list is an empty list$")
    public void thenTheLoadedStatementTakenArgumentListIsAnEmptyList() {
        assertTrue(statement.getTakes().isEmpty());
    }

    @Then("^the loaded statement taken argument list is not an empty list$")
    public void thenTheLoadedStatementTakenArgumentListIsNotAnEmptyList() {
        assertFalse(statement.getTakes().isEmpty());
    }

    @Then("^the loaded statement taken argument list is a singular list$")
    public void thenTheLoadedStatementTakenArgumentListIsASingularList() {
        assertTrue(statement.getTakes().isSingular());
    }

    @Then("^the loaded statement taken argument list is not a singular list$")
    public void thenTheLoadedStatementTakenArgumentListIsNotASingularList() {
        assertFalse(statement.getTakes().isSingular());
    }

    @Then("^the loaded statement taken argument list is a multiple list$")
    public void thenTheLoadedStatementTakenArgumentListIsAMultipleList() {
        assertTrue(statement.getTakes().isMultiple());
    }

    @Then("^the loaded statement taken argument list is not a multiple list$")
    public void thenTheLoadedStatementTakenArgumentListIsNotAMultipleList() {
        assertFalse(statement.getTakes().isMultiple());
    }

    @Then("^the loaded statement taken argument has a name of \"(.*)\"$")
    public void thenTheLoadedStatementTakenArgumentHasANameOf(String name) {
        assertEquals(name, statement.getTakes().getFirst().getName());
    }

    @Then("^the loaded statement taken argument has a getter name of \"(.*)\"$")
    public void thenTheLoadedStatementTakenArgumentHasAGetterNameOf(String name) {
        assertEquals(name, statement.getTakes().getFirst().getGetterName());
    }

    @Then("^the loaded statement taken argument has a java type of (.*)$")
    public void thenTheLoadedStatementTakenArgumentHasAJavaTypeOf(String type) {
        assertEquals(type, statement.getTakes().getFirst().getJavaType());
    }

    @Then("^the loaded statement taken argument has a string representation of \"(.*)\"$")
    public void thenTheLoadedStatementTakenArgumentHasAStringRepresentationOf(String representation) {
        assertEquals(representation, statement.getTakes().getFirst().toString());
    }

    @Then("^the loaded statement taken argument list has a string representation of \"(.*)\"$")
    public void thenTheLoadedStatementTakenArgumentListHasAStringRepresentationOf(String representation) {
        assertEquals(representation, statement.getTakes().toString());
    }

    @Then("^the loaded statement taken argument has a type of TEXT$")
    public void thenTheLoadedStatementTakenArgumentHasATypeOfText() {
        assertEquals(ArgumentType.TEXT, statement.getTakes().getFirst().getType());
    }

    @Then("^the loaded statement taken argument has a type of TIMESTAMP$")
    public void thenTheLoadedStatementTakenArgumentHasATypeOfTimestamp() {
        assertEquals(ArgumentType.TIMESTAMP, statement.getTakes().getFirst().getType());
    }

    @Then("^the loaded statement taken argument has a type of FRACTIONAL_NUMBER$")
    public void thenTheLoadedStatementTakenArgumentHasATypeOfFractionalNumber() {
        assertEquals(ArgumentType.FRACTIONAL_NUMBER, statement.getTakes().getFirst().getType());
    }

    @Then("^the loaded statement taken argument has a type of WHOLE_NUMBER$")
    public void thenTheLoadedStatementTakenArgumentHasATypeOfWholeNumber() {
        assertEquals(ArgumentType.WHOLE_NUMBER, statement.getTakes().getFirst().getType());
    }

    @Then("^the loaded statement returns (\\d+) arguments$")
    public void thenTheLoadedStatementReturnsArguments(int count) {
        assertEquals(count, statement.getReturns().size());
    }

    @Then("^the loaded statement has a list of returned arguments$")
    public void thenTheLoadedStatementHasAListOfReturnedArguments() {
        assertTrue(statement.getReturns().isDefined());
    }

    @Then("^the loaded statement does not have a list of returned arguments$")
    public void thenTheLoadedStatementDoesNotHaveAListOfReturnedArguments() {
        assertFalse(statement.getReturns().isDefined());
    }

    @Then("^the loaded statement returned argument list is an empty list$")
    public void thenTheLoadedStatementReturnedArgumentListIsAnEmptyList() {
        assertTrue(statement.getReturns().isEmpty());
    }

    @Then("^the loaded statement returned argument list is not an empty list$")
    public void thenTheLoadedStatementReturnedArgumentListIsNotAnEmptyList() {
        assertFalse(statement.getReturns().isEmpty());
    }

    @Then("^the loaded statement returned argument list is a singular list$")
    public void thenTheLoadedStatementReturnedArgumentListIsASingularList() {
        assertTrue(statement.getReturns().isSingular());
    }

    @Then("^the loaded statement returned argument list is not a singular list$")
    public void thenTheLoadedStatementReturnedArgumentListIsNotASingularList() {
        assertFalse(statement.getReturns().isSingular());
    }

    @Then("^the loaded statement returned argument list is a multiple list$")
    public void thenTheLoadedStatementReturnedArgumentListIsAMultipleList() {
        assertTrue(statement.getReturns().isMultiple());
    }

    @Then("^the loaded statement returned argument list is not a multiple list$")
    public void thenTheLoadedStatementReturnedArgumentListIsNotAMultipleList() {
        assertFalse(statement.getReturns().isMultiple());
    }

    @Then("^the loaded statement returned argument has a name of \"(.*)\"$")
    public void thenTheLoadedStatementReturnedArgumentHasANameOf(String name) {
        assertEquals(name, statement.getReturns().getFirst().getName());
    }

    @Then("^the loaded statement returned argument has a getter name of \"(.*)\"$")
    public void thenTheLoadedStatementReturnedArgumentHasAGetterNameOf(String name) {
        assertEquals(name, statement.getReturns().getFirst().getGetterName());
    }

    @Then("^the loaded statement returned argument has a java type of (.*)$")
    public void thenTheLoadedStatementReturnedArgumentHasAJavaTypeOf(String type) {
        assertEquals(type, statement.getReturns().getFirst().getJavaType());
    }

    @Then("^the loaded statement returned argument has a string representation of \"(.*)\"$")
    public void thenTheLoadedStatementReturnedArgumentHasAStringRepresentationOf(String representation) {
        assertEquals(representation, statement.getReturns().getFirst().toString());
    }

    @Then("^the loaded statement returned argument list has a string representation of \"(.*)\"$")
    public void thenTheLoadedStatementReturnedArgumentListHasAStringRepresentationOf(String representation) {
        assertEquals(representation, statement.getReturns().toString());
    }

    @Then("^the loaded statement returned argument has a type of TEXT$")
    public void thenTheLoadedStatementReturnedArgumentHasATypeOfText() {
        assertEquals(ArgumentType.TEXT, statement.getReturns().getFirst().getType());
    }

    @Then("^the loaded statement returned argument has a type of TIMESTAMP$")
    public void thenTheLoadedStatementReturnedArgumentHasATypeOfTimestamp() {
        assertEquals(ArgumentType.TIMESTAMP, statement.getReturns().getFirst().getType());
    }

    @Then("^the loaded statement returned argument has a type of FRACTIONAL_NUMBER$")
    public void thenTheLoadedStatementReturnedArgumentHasATypeOfFractionalNumber() {
        assertEquals(ArgumentType.FRACTIONAL_NUMBER, statement.getReturns().getFirst().getType());
    }

    @Then("^the loaded statement returned argument has a type of WHOLE_NUMBER$")
    public void thenTheLoadedStatementReturnedArgumentHasATypeOfWholeNumber() {
        assertEquals(ArgumentType.WHOLE_NUMBER, statement.getReturns().getFirst().getType());
    }

    @Then("^the loaded statement body is \"(.*)\"$")
    public void thenTheLoadedStatementBodyIs(String body) {
        assertEquals(body, statement.getStatement());
    }

    @Then("^the loaded statement name is \"(.*)\"$")
    public void thenTheLoadedStatementNameIs(String name) {
        assertEquals(name, statement.getName().getName());
    }

    @Then("^the loaded statement full name is \"(.*)\"$")
    public void thenTheLoadedStatementFullNameIs(String fullName) {
        assertEquals(fullName, statement.getName().getFullName());
    }

    @Then("^the loaded statement package is \"(.*)\"$")
    public void thenTheLoadedStatementPackageIs(String _package) {
        assertEquals(_package, statement.getName().getPackage());
    }

    @Then("^the loaded statement is in the default package$")
    public void thenTheLoadedStatementIsInTheDefaultPackage() {
        assertTrue(statement.getName().isInDefaultPackage());
    }

    @Then("^the loaded statement is not in the default package$")
    public void thenTheLoadedStatementIsNotInTheDefaultPackage() {
        assertFalse(statement.getName().isInDefaultPackage());
    }

    @Then("^the loaded statement path is \"(.*)\"$")
    public void thenTheLoadedStatementPathIs(String path) {
        assertEquals(path, statement.getName().getPath());
    }

}
