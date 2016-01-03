Feature: Can read SQL Statement Name

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement name is "<name>"
        And the loaded statement getter name is "<getter>"

    Examples:
        | file                                             | name                           | getter                            |
        | statements/statement-by-name.sql                 | StatementByName                | getStatementByName                |
        | statements/statement-taking-date-argument.sql    | StatementTakingDateArgument    | getStatementTakingDateArgument    |
        | statements/statement-taking-double-argument.sql  | StatementTakingDoubleArgument  | getStatementTakingDoubleArgument  |
        | statements/statement-taking-float-argument.sql   | StatementTakingFloatArgument   | getStatementTakingFloatArgument   |
        | statements/statement-taking-integer-argument.sql | StatementTakingIntegerArgument | getStatementTakingIntegerArgument |
        | statements/statement-taking-long-argument.sql    | StatementTakingLongArgument    | getStatementTakingLongArgument    |
        | statements/statement-taking-text-argument.sql    | StatementTakingTextArgument    | getStatementTakingTextArgument    |
        | statements/statement-with-comment.sql            | StatementWithComment           | getStatementWithComment           |
        | statements/statement.sql                         | Statement                      | getStatement                      |
        | statement.sql                                    | Statement                      | getStatement                      |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement name is "<name>"
        And the loaded statement getter name is "<getter>"

    Examples:
        | directory          | file                                             | name                           | getter                            |
        | src/test/resources | statements/statement-by-name.sql                 | StatementByName                | getStatementByName                |
        | src/test/resources | statements/statement-taking-date-argument.sql    | StatementTakingDateArgument    | getStatementTakingDateArgument    |
        | src/test/resources | statements/statement-taking-double-argument.sql  | StatementTakingDoubleArgument  | getStatementTakingDoubleArgument  |
        | src/test/resources | statements/statement-taking-float-argument.sql   | StatementTakingFloatArgument   | getStatementTakingFloatArgument   |
        | src/test/resources | statements/statement-taking-integer-argument.sql | StatementTakingIntegerArgument | getStatementTakingIntegerArgument |
        | src/test/resources | statements/statement-taking-long-argument.sql    | StatementTakingLongArgument    | getStatementTakingLongArgument    |
        | src/test/resources | statements/statement-taking-text-argument.sql    | StatementTakingTextArgument    | getStatementTakingTextArgument    |
        | src/test/resources | statements/statement-with-comment.sql            | StatementWithComment           | getStatementWithComment           |
        | src/test/resources | statements/statement.sql                         | Statement                      | getStatement                      |
        | src/test/resources | statement.sql                                    | Statement                      | getStatement                      |
