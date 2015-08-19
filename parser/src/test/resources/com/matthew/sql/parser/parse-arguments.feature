Feature: Can read SQL Statement Argument

    Scenario Outline: Reading from different files

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement argument count is <arguments>

    Examples:
        | file                        | arguments |
        | statement-by-name.sql       | 0         |
        | statement-with-argument.sql | 1         |
        | statement-with-comment.sql  | 0         |
        | statement.sql               | 0         |
