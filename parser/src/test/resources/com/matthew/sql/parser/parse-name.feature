Feature: Can read SQL Statement Name

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement name is "<name>"

    Examples:
        | file                                   | name                  |
        | statements/statement-by-name.sql       | StatementByName       |
        | statements/statement-with-argument.sql | StatementWithArgument |
        | statements/statement-with-comment.sql  | StatementWithComment  |
        | statements/statement.sql               | Statement             |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement name is "<name>"

    Examples:
        | directory          | file                                   | name                  |
        | src/test/resources | statements/statement-by-name.sql       | StatementByName       |
        | src/test/resources | statements/statement-with-argument.sql | StatementWithArgument |
        | src/test/resources | statements/statement-with-comment.sql  | StatementWithComment  |
        | src/test/resources | statements/statement.sql               | Statement             |
