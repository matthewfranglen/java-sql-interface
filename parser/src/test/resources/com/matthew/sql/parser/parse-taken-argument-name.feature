Feature: Can read SQL Statement taken argument name

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement taken argument has a name of "<name>"

    Examples: Statements with takes
        | file                                             | name     |
        | statements/statement-taking-date-argument.sql    | time     |
        | statements/statement-taking-double-argument.sql  | ratio    |
        | statements/statement-taking-float-argument.sql   | ratio    |
        | statements/statement-taking-integer-argument.sql | quantity |
        | statements/statement-taking-long-argument.sql    | quantity |
        | statements/statement-taking-text-argument.sql    | name     |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement taken argument has a name of "<name>"

    Examples: Statements with takes
        | directory          | file                                             | name     |
        | src/test/resources | statements/statement-taking-date-argument.sql    | time     |
        | src/test/resources | statements/statement-taking-double-argument.sql  | ratio    |
        | src/test/resources | statements/statement-taking-float-argument.sql   | ratio    |
        | src/test/resources | statements/statement-taking-integer-argument.sql | quantity |
        | src/test/resources | statements/statement-taking-long-argument.sql    | quantity |
        | src/test/resources | statements/statement-taking-text-argument.sql    | name     |
