Feature: Can read SQL Statement returned argument name

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement returned argument has a name of "<name>"

    Examples: Statements with returns
        | file                                                | name     |
        | statements/statement-returning-date-argument.sql    | time     |
        | statements/statement-returning-double-argument.sql  | ratio    |
        | statements/statement-returning-float-argument.sql   | ratio    |
        | statements/statement-returning-integer-argument.sql | quantity |
        | statements/statement-returning-long-argument.sql    | quantity |
        | statements/statement-returning-text-argument.sql    | name     |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement returned argument has a name of "<name>"

    Examples: Statements with returns
        | directory          | file                                                | name     |
        | src/test/resources | statements/statement-returning-date-argument.sql    | time     |
        | src/test/resources | statements/statement-returning-double-argument.sql  | ratio    |
        | src/test/resources | statements/statement-returning-float-argument.sql   | ratio    |
        | src/test/resources | statements/statement-returning-integer-argument.sql | quantity |
        | src/test/resources | statements/statement-returning-long-argument.sql    | quantity |
        | src/test/resources | statements/statement-returning-text-argument.sql    | name     |
