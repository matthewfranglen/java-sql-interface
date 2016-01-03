Feature: Can read SQL Statement returned argument name

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement returned argument has a name of "<name>"
        Then the loaded statement returned argument has a getter name of "<getter name>"

    Examples: Statements with returns
        | file                                                | name     | getter name |
        | statements/statement-returning-date-argument.sql    | time     | getTime     |
        | statements/statement-returning-double-argument.sql  | ratio    | getRatio    |
        | statements/statement-returning-float-argument.sql   | ratio    | getRatio    |
        | statements/statement-returning-integer-argument.sql | quantity | getQuantity |
        | statements/statement-returning-long-argument.sql    | quantity | getQuantity |
        | statements/statement-returning-text-argument.sql    | name     | getName     |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement returned argument has a name of "<name>"
        Then the loaded statement returned argument has a getter name of "<getter name>"

    Examples: Statements with returns
        | directory          | file                                                | name     | getter name |
        | src/test/resources | statements/statement-returning-date-argument.sql    | time     | getTime     |
        | src/test/resources | statements/statement-returning-double-argument.sql  | ratio    | getRatio    |
        | src/test/resources | statements/statement-returning-float-argument.sql   | ratio    | getRatio    |
        | src/test/resources | statements/statement-returning-integer-argument.sql | quantity | getQuantity |
        | src/test/resources | statements/statement-returning-long-argument.sql    | quantity | getQuantity |
        | src/test/resources | statements/statement-returning-text-argument.sql    | name     | getName     |
