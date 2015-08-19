Feature: Can read SQL Statement Name

    Scenario Outline: Reading from different files

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement name is "<name>"

    Examples:
        | file                        | name                    |
        | statement-by-name.sql       | statement-by-name       |
        | statement-with-argument.sql | statement-with-argument |
        | statement-with-comment.sql  | statement-with-comment  |
        | statement.sql               | statement               |
