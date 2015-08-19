Feature: Can read SQL Statement Argument

    Scenario Outline: Reading from different files

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement argument count is <arguments>

    Examples:
        | file                                   | arguments |
        | statements/statement-by-name.sql       | 0         |
        | statements/statement-with-argument.sql | 1         |
        | statements/statement-with-comment.sql  | 0         |
        | statements/statement.sql               | 0         |
