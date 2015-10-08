Feature: Can read SQL Statement Package

    Scenario Outline: Reading from different files

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement package is "<package>"

    Examples:
        | file                                   | package    |
        | statements/statement-by-name.sql       | statements |
        | statements/statement-with-argument.sql | statements |
        | statements/statement-with-comment.sql  | statements |
        | statements/statement.sql               | statements |
