Feature: Can read SQL Statement returned argument type

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement returned argument has a type of "<type>"

    Examples: Statements with returns
        | file                                                | type              |
        | statements/statement-returning-date-argument.sql    | TIMESTAMP         |
        | statements/statement-returning-double-argument.sql  | FRACTIONAL_NUMBER |
        | statements/statement-returning-float-argument.sql   | FRACTIONAL_NUMBER |
        | statements/statement-returning-integer-argument.sql | WHOLE_NUMBER      |
        | statements/statement-returning-long-argument.sql    | WHOLE_NUMBER      |
        | statements/statement-returning-text-argument.sql    | TEXT              |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement returned argument has a type of "<type>"

    Examples: Statements with returns
        | directory          | file                                                | type              |
        | src/test/resources | statements/statement-returning-date-argument.sql    | TIMESTAMP         |
        | src/test/resources | statements/statement-returning-double-argument.sql  | FRACTIONAL_NUMBER |
        | src/test/resources | statements/statement-returning-float-argument.sql   | FRACTIONAL_NUMBER |
        | src/test/resources | statements/statement-returning-integer-argument.sql | WHOLE_NUMBER      |
        | src/test/resources | statements/statement-returning-long-argument.sql    | WHOLE_NUMBER      |
        | src/test/resources | statements/statement-returning-text-argument.sql    | TEXT              |
