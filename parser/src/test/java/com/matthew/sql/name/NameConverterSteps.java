package com.matthew.sql.name;

import static org.junit.Assert.assertEquals;

import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class NameConverterSteps {

    private NameConverter converter;
    private List<String> splitWords;
    private String joinedWord;

    @Given("^a CamelCase converter$")
    public void givenACamelCaseConverter() {
        converter = new CamelCaseConverter();
    }

    @Given("^a Separator converter using \"(.*)\"$")
    public void givenASeparatorConverterUsing(String separator) {
        converter = new SeparatorConverter(separator);
    }

    @When("^the word \"(.*)\" is split$")
    public void whenTheWordIsSplit(String word) {
        splitWords = converter.split(word);
    }

    @When("^the words (.*) are joined$")
    public void whenTheWordsAreJoined(List<String> words) {
        joinedWord = converter.join(words);
    }

    @Then("^the returned array is (.*)$")
    public void thenTheReturnedArrayIs(List<String> words) {
        assertEquals(words, splitWords);
    }

    @Then("^the returned string is \"(.*)\"$")
    public void thenTheReturnedStringIs(String word) {
        assertEquals(word, joinedWord);
    }

}
