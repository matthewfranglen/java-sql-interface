Feature: Can read SQL Statement Package

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement package is "<package>"
        And the loaded statement <is in?> the default package

    Examples:
        | file                                             | package    | is in?    |
        | statements/statement-by-name.sql                 | statements | is not in |
        | statements/statement-taking-date-argument.sql    | statements | is not in |
        | statements/statement-taking-double-argument.sql  | statements | is not in |
        | statements/statement-taking-float-argument.sql   | statements | is not in |
        | statements/statement-taking-integer-argument.sql | statements | is not in |
        | statements/statement-taking-long-argument.sql    | statements | is not in |
        | statements/statement-taking-text-argument.sql    | statements | is not in |
        | statements/statement-with-comment.sql            | statements | is not in |
        | statements/statement.sql                         | statements | is not in |
        | statement.sql                                    |            | is in     |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement package is "<package>"
        And the loaded statement <is in?> the default package

    Examples:
        | directory          | file                                             | package    | is in?    |
        | src/test/resources | statements/statement-by-name.sql                 | statements | is not in |
        | src/test/resources | statements/statement-taking-date-argument.sql    | statements | is not in |
        | src/test/resources | statements/statement-taking-double-argument.sql  | statements | is not in |
        | src/test/resources | statements/statement-taking-float-argument.sql   | statements | is not in |
        | src/test/resources | statements/statement-taking-integer-argument.sql | statements | is not in |
        | src/test/resources | statements/statement-taking-long-argument.sql    | statements | is not in |
        | src/test/resources | statements/statement-taking-text-argument.sql    | statements | is not in |
        | src/test/resources | statements/statement-with-comment.sql            | statements | is not in |
        | src/test/resources | statements/statement.sql                         | statements | is not in |
        | src/test/resources | statement.sql                                    |            | is in     |
