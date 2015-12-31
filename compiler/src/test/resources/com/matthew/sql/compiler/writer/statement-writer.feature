Feature: Can write Statement

    Scenario Outline: Writing different statements

        Given the statements loaded using the inclusion pattern "<include>"
        When the statement is written to a file
        Then the file at "<location>" looks like the resource "<code>"

    Examples:
        | include                                | location                              | code                                         |
        | statements/statement-by-name.sql       | statements/StatementByName.java       | code/statements/StatementByName.java         |
        | statements/statement-with-argument.sql | statements/StatementWithArgument.java | code/statements/StatementWithArgument.java   |
        | statements/statement-with-comment.sql  | statements/StatementWithComment.java  | code/statements/StatementWithComment.java    |
        | statements/statement.sql               | statements/Statement.java             | code/statements/Statement.java               |
        | statement.sql                          | Statement.java                        | code/statements/StatementWithoutPackage.java |
