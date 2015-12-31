Feature: Can write Statement

    Scenario Outline: Writing different statements

        Given the statement loaded from the resource "<statement>"
        When the statement is written to a file
        Then the file at "<location>" looks like the resource "<code>"

    Examples:
        | statement                              | location                              | code                            |
        | statements/statement-by-name.sql       | statements/StatementByName.java       | code/StatementByName.java       |
        | statements/statement-with-argument.sql | statements/StatementWithArgument.java | code/StatementWithArgument.java |
        | statements/statement-with-comment.sql  | statements/StatementWithComment.java  | code/StatementWithComment.java  |
        | statements/statement.sql               | statements/Statement.java             | code/Statement.java             |
        | statement.sql                          | Statement.java                        | Statement.java                  |
