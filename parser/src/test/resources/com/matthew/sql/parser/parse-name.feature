Feature: Can read SQL Statement Name

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement name is "<name>"

    Examples:
        | file                                             | name                           |
        | statements/statement-by-name.sql                 | StatementByName                |
        | statements/statement-taking-date-argument.sql    | StatementTakingDateArgument    |
        | statements/statement-taking-double-argument.sql  | StatementTakingDoubleArgument  |
        | statements/statement-taking-float-argument.sql   | StatementTakingFloatArgument   |
        | statements/statement-taking-integer-argument.sql | StatementTakingIntegerArgument |
        | statements/statement-taking-long-argument.sql    | StatementTakingLongArgument    |
        | statements/statement-taking-text-argument.sql    | StatementTakingTextArgument    |
        | statements/statement-with-comment.sql            | StatementWithComment           |
        | statements/statement.sql                         | Statement                      |
        | statement.sql                                    | Statement                      |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement name is "<name>"

    Examples:
        | directory          | file                                             | name                           |
        | src/test/resources | statements/statement-by-name.sql                 | StatementByName                |
        | src/test/resources | statements/statement-taking-date-argument.sql    | StatementTakingDateArgument    |
        | src/test/resources | statements/statement-taking-double-argument.sql  | StatementTakingDoubleArgument  |
        | src/test/resources | statements/statement-taking-float-argument.sql   | StatementTakingFloatArgument   |
        | src/test/resources | statements/statement-taking-integer-argument.sql | StatementTakingIntegerArgument |
        | src/test/resources | statements/statement-taking-long-argument.sql    | StatementTakingLongArgument    |
        | src/test/resources | statements/statement-taking-text-argument.sql    | StatementTakingTextArgument    |
        | src/test/resources | statements/statement-with-comment.sql            | StatementWithComment           |
        | src/test/resources | statements/statement.sql                         | Statement                      |
        | src/test/resources | statement.sql                                    | Statement                      |
