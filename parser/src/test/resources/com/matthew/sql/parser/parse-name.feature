Feature: Can read SQL Statement Name

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement name is "<name>"
        And the loaded statement full name is "<full name>"

    Examples:
        | file                                             | name                           | full name                                 |
        | statements/statement-by-name.sql                 | StatementByName                | statements.StatementByName                |
        | statements/statement-taking-date-argument.sql    | StatementTakingDateArgument    | statements.StatementTakingDateArgument    |
        | statements/statement-taking-double-argument.sql  | StatementTakingDoubleArgument  | statements.StatementTakingDoubleArgument  |
        | statements/statement-taking-float-argument.sql   | StatementTakingFloatArgument   | statements.StatementTakingFloatArgument   |
        | statements/statement-taking-integer-argument.sql | StatementTakingIntegerArgument | statements.StatementTakingIntegerArgument |
        | statements/statement-taking-long-argument.sql    | StatementTakingLongArgument    | statements.StatementTakingLongArgument    |
        | statements/statement-taking-text-argument.sql    | StatementTakingTextArgument    | statements.StatementTakingTextArgument    |
        | statements/statement-with-comment.sql            | StatementWithComment           | statements.StatementWithComment           |
        | statements/statement.sql                         | Statement                      | statements.Statement                      |
        | statement.sql                                    | Statement                      | Statement                                 |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement name is "<name>"
        And the loaded statement full name is "<full name>"

    Examples:
        | directory          | file                                             | name                           | full name                                 |
        | src/test/resources | statements/statement-by-name.sql                 | StatementByName                | statements.StatementByName                |
        | src/test/resources | statements/statement-taking-date-argument.sql    | StatementTakingDateArgument    | statements.StatementTakingDateArgument    |
        | src/test/resources | statements/statement-taking-double-argument.sql  | StatementTakingDoubleArgument  | statements.StatementTakingDoubleArgument  |
        | src/test/resources | statements/statement-taking-float-argument.sql   | StatementTakingFloatArgument   | statements.StatementTakingFloatArgument   |
        | src/test/resources | statements/statement-taking-integer-argument.sql | StatementTakingIntegerArgument | statements.StatementTakingIntegerArgument |
        | src/test/resources | statements/statement-taking-long-argument.sql    | StatementTakingLongArgument    | statements.StatementTakingLongArgument    |
        | src/test/resources | statements/statement-taking-text-argument.sql    | StatementTakingTextArgument    | statements.StatementTakingTextArgument    |
        | src/test/resources | statements/statement-with-comment.sql            | StatementWithComment           | statements.StatementWithComment           |
        | src/test/resources | statements/statement.sql                         | Statement                      | statements.Statement                      |
        | src/test/resources | statement.sql                                    | Statement                      | Statement                                 |
