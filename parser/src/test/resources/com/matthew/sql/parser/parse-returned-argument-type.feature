Feature: Can read SQL Statement returned argument type

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement returned argument has a type of <type>
        And the loaded statement returned argument has a java type of <java type>

    Examples: Statements with returns
        | file                                                | type              | java type        |
        | statements/statement-returning-date-argument.sql    | TIMESTAMP         | java.util.Date   |
        | statements/statement-returning-double-argument.sql  | FRACTIONAL_NUMBER | java.lang.Double |
        | statements/statement-returning-float-argument.sql   | FRACTIONAL_NUMBER | java.lang.Double |
        | statements/statement-returning-integer-argument.sql | WHOLE_NUMBER      | java.lang.Long   |
        | statements/statement-returning-long-argument.sql    | WHOLE_NUMBER      | java.lang.Long   |
        | statements/statement-returning-text-argument.sql    | TEXT              | java.lang.String |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement returned argument has a type of <type>
        And the loaded statement returned argument has a java type of <java type>

    Examples: Statements with returns
        | directory          | file                                                | type              | java type        |
        | src/test/resources | statements/statement-returning-date-argument.sql    | TIMESTAMP         | java.util.Date   |
        | src/test/resources | statements/statement-returning-double-argument.sql  | FRACTIONAL_NUMBER | java.lang.Double |
        | src/test/resources | statements/statement-returning-float-argument.sql   | FRACTIONAL_NUMBER | java.lang.Double |
        | src/test/resources | statements/statement-returning-integer-argument.sql | WHOLE_NUMBER      | java.lang.Long   |
        | src/test/resources | statements/statement-returning-long-argument.sql    | WHOLE_NUMBER      | java.lang.Long   |
        | src/test/resources | statements/statement-returning-text-argument.sql    | TEXT              | java.lang.String |
