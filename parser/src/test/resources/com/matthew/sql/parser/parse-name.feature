Feature: Can read SQL Statement Name

    Scenario Outline: Reading from different files

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement name is "<name>"

    Examples:
        | file                                   | name                             |
        | statements/statement-by-name.sql       | statements.StatementByName       |
        | statements/statement-with-argument.sql | statements.StatementWithArgument |
        | statements/statement-with-comment.sql  | statements.StatementWithComment  |
        | statements/statement.sql               | statements.Statement             |
