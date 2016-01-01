Feature: Can write Statement Handler

    Scenario Outline: Writing different statement handlers

        Given the statements loaded using the inclusion pattern "<include>"
        When the statement handler is written to a file
        Then the statement handler file looks like the resource "<code>"

    Examples:
        | include                                | code                                              |
        | statements/statement-by-name.sql       | code/handlers/StatementHandlerByName.java         |
        | statements/statement-with-argument.sql | code/handlers/StatementHandlerWithArgument.java   |
        | statements/statement-with-comment.sql  | code/handlers/StatementHandlerWithComment.java    |
        | statements/statement.sql               | code/handlers/StatementHandler.java               |
        | statement.sql                          | code/handlers/StatementHandlerWithoutPackage.java |
