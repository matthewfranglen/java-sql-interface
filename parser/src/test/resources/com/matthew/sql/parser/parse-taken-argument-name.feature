Feature: Can read SQL Statement taken argument name

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement taken argument has a name of "<name>"
        And the loaded statement taken argument has a getter name of "<getter name>"

    Examples: Statements with takes
        | file                                             | name     | getter name |
        | statements/statement-taking-date-argument.sql    | time     | getTime     |
        | statements/statement-taking-double-argument.sql  | ratio    | getRatio    |
        | statements/statement-taking-float-argument.sql   | ratio    | getRatio    |
        | statements/statement-taking-integer-argument.sql | quantity | getQuantity |
        | statements/statement-taking-long-argument.sql    | quantity | getQuantity |
        | statements/statement-taking-text-argument.sql    | name     | getName     |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement taken argument has a name of "<name>"
        And the loaded statement taken argument has a getter name of "<getter name>"

    Examples: Statements with takes
        | directory          | file                                             | name     | getter name |
        | src/test/resources | statements/statement-taking-date-argument.sql    | time     | getTime     |
        | src/test/resources | statements/statement-taking-double-argument.sql  | ratio    | getRatio    |
        | src/test/resources | statements/statement-taking-float-argument.sql   | ratio    | getRatio    |
        | src/test/resources | statements/statement-taking-integer-argument.sql | quantity | getQuantity |
        | src/test/resources | statements/statement-taking-long-argument.sql    | quantity | getQuantity |
        | src/test/resources | statements/statement-taking-text-argument.sql    | name     | getName     |
