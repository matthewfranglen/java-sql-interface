Feature: Can read SQL Statement taken argument type

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement taken argument has a type of "<type>"

    Examples: Statements with takes
        | file                                             | type              |
        | statements/statement-taking-date-argument.sql    | TIMESTAMP         |
        | statements/statement-taking-double-argument.sql  | FRACTIONAL_NUMBER |
        | statements/statement-taking-float-argument.sql   | FRACTIONAL_NUMBER |
        | statements/statement-taking-integer-argument.sql | WHOLE_NUMBER      |
        | statements/statement-taking-long-argument.sql    | WHOLE_NUMBER      |
        | statements/statement-taking-text-argument.sql    | TEXT              |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement taken argument has a type of "<type>"

    Examples: Statements with takes
        | directory          | file                                             | type              |
        | src/test/resources | statements/statement-taking-date-argument.sql    | TIMESTAMP         |
        | src/test/resources | statements/statement-taking-double-argument.sql  | FRACTIONAL_NUMBER |
        | src/test/resources | statements/statement-taking-float-argument.sql   | FRACTIONAL_NUMBER |
        | src/test/resources | statements/statement-taking-integer-argument.sql | WHOLE_NUMBER      |
        | src/test/resources | statements/statement-taking-long-argument.sql    | WHOLE_NUMBER      |
        | src/test/resources | statements/statement-taking-text-argument.sql    | TEXT              |
