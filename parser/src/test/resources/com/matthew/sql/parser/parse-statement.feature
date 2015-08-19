Feature: Can read SQL Statement Body

    Scenario Outline: Reading from different files

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement body is "SELECT * FROM table LIMIT 1;"

    Examples:
        | file                        |
        | statement-by-name.sql       |
        | statement-with-argument.sql |
        | statement-with-comment.sql  |
        | statement.sql               |
