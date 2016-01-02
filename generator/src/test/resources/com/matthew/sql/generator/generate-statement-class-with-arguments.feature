Feature: Can create Statement class

    Scenario Outline: Reading from different files

        Given the name "Statement"
        And the path "statements"
        And the statement "statements/statement.sql"
        And the takes "<takes>"
        And the returns "<returns>"
        When the statement is converted to a statement class
        Then the code looks like "<code file>"

    Examples:
        | code file                                                            | takes                    | returns                  |
        | code/StatementWithTextTakeAndTextReturn.java                         | TEXT text                | TEXT text                |
        | code/StatementWithWholeNumberTakeAndWholeNumberReturn.java           | WHOLE_NUMBER number      | WHOLE_NUMBER number      |
        | code/StatementWithFractionalNumberTakeAndFractionalNumberReturn.java | FRACTIONAL_NUMBER number | FRACTIONAL_NUMBER number |
        | code/StatementWithTimestampTakeAndTimestampReturn.java               | TIMESTAMP timestamp      | TIMESTAMP timestamp      |

    Examples:
        | code file                                              | takes                                                                        | returns                                                                      |
        | code/StatementWithMultipleTakesAndMultipleReturns.java | TEXT tOne, WHOLE_NUMBER iTwo, FRACTIONAL_NUMBER fThree, TIMESTAMP tsFour | TEXT tOne, WHOLE_NUMBER iTwo, FRACTIONAL_NUMBER fThree, TIMESTAMP tsFour |
