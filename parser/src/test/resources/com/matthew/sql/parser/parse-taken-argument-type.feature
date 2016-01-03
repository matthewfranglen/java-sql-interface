Feature: Can read SQL Statement taken argument type

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement taken argument has a type of <type>
        And the loaded statement taken argument has a java type of <java type>

    Examples: Statements with takes
        | file                                             | type              | java type        |
        | statements/statement-taking-date-argument.sql    | TIMESTAMP         | java.util.Date   |
        | statements/statement-taking-double-argument.sql  | FRACTIONAL_NUMBER | java.lang.Double |
        | statements/statement-taking-float-argument.sql   | FRACTIONAL_NUMBER | java.lang.Double |
        | statements/statement-taking-integer-argument.sql | WHOLE_NUMBER      | java.lang.Long   |
        | statements/statement-taking-long-argument.sql    | WHOLE_NUMBER      | java.lang.Long   |
        | statements/statement-taking-text-argument.sql    | TEXT              | java.lang.String |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement taken argument has a type of <type>
        And the loaded statement taken argument has a java type of <java type>

    Examples: Statements with takes
        | directory          | file                                             | type              | java type        |
        | src/test/resources | statements/statement-taking-date-argument.sql    | TIMESTAMP         | java.util.Date   |
        | src/test/resources | statements/statement-taking-double-argument.sql  | FRACTIONAL_NUMBER | java.lang.Double |
        | src/test/resources | statements/statement-taking-float-argument.sql   | FRACTIONAL_NUMBER | java.lang.Double |
        | src/test/resources | statements/statement-taking-integer-argument.sql | WHOLE_NUMBER      | java.lang.Long   |
        | src/test/resources | statements/statement-taking-long-argument.sql    | WHOLE_NUMBER      | java.lang.Long   |
        | src/test/resources | statements/statement-taking-text-argument.sql    | TEXT              | java.lang.String |
