Feature: Can read SQL Statement Package

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement path is "<path>"

    Examples:
        | file                                             | path       |
        | statements/statement-by-name.sql                 | statements |
        | statements/statement-taking-date-argument.sql    | statements |
        | statements/statement-taking-double-argument.sql  | statements |
        | statements/statement-taking-float-argument.sql   | statements |
        | statements/statement-taking-integer-argument.sql | statements |
        | statements/statement-taking-long-argument.sql    | statements |
        | statements/statement-taking-text-argument.sql    | statements |
        | statements/statement-with-comment.sql            | statements |
        | statements/statement.sql                         | statements |
        | statement.sql                                    |            |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement path is "<path>"

    Examples:
        | directory          | file                                             | path       |
        | src/test/resources | statements/statement-by-name.sql                 | statements |
        | src/test/resources | statements/statement-taking-date-argument.sql    | statements |
        | src/test/resources | statements/statement-taking-double-argument.sql  | statements |
        | src/test/resources | statements/statement-taking-float-argument.sql   | statements |
        | src/test/resources | statements/statement-taking-integer-argument.sql | statements |
        | src/test/resources | statements/statement-taking-long-argument.sql    | statements |
        | src/test/resources | statements/statement-taking-text-argument.sql    | statements |
        | src/test/resources | statements/statement-with-comment.sql            | statements |
        | src/test/resources | statements/statement.sql                         | statements |
        | src/test/resources | statement.sql                                    |            |
