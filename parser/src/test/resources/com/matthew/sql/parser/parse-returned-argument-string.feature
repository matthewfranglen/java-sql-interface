Feature: Can read SQL Statement returned argument string representation

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement returned argument has a string representation of "<representation>"

    Examples: Statements with takes
        | file                                                | representation          |
        | statements/statement-returning-date-argument.sql    | java.util.Date time     |
        | statements/statement-returning-double-argument.sql  | java.lang.Double ratio  |
        | statements/statement-returning-float-argument.sql   | java.lang.Double ratio  |
        | statements/statement-returning-integer-argument.sql | java.lang.Long quantity |
        | statements/statement-returning-long-argument.sql    | java.lang.Long quantity |
        | statements/statement-returning-text-argument.sql    | java.lang.String name   |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement returned argument has a string representation of "<representation>"

    Examples: Statements with takes
        | directory          | file                                                | representation          |
        | src/test/resources | statements/statement-returning-date-argument.sql    | java.util.Date time     |
        | src/test/resources | statements/statement-returning-double-argument.sql  | java.lang.Double ratio  |
        | src/test/resources | statements/statement-returning-float-argument.sql   | java.lang.Double ratio  |
        | src/test/resources | statements/statement-returning-integer-argument.sql | java.lang.Long quantity |
        | src/test/resources | statements/statement-returning-long-argument.sql    | java.lang.Long quantity |
        | src/test/resources | statements/statement-returning-text-argument.sql    | java.lang.String name   |
