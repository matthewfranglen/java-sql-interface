Feature: Can read SQL Statement Package

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement package is "<package>"

    Examples:
        | file                                   | package    |
        | statements/statement-by-name.sql       | statements |
        | statements/statement-with-argument.sql | statements |
        | statements/statement-with-comment.sql  | statements |
        | statements/statement.sql               | statements |
        | statement.sql                          |            |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement package is "<package>"

    Examples:
        | directory          | file                                   | package    |
        | src/test/resources | statements/statement-by-name.sql       | statements |
        | src/test/resources | statements/statement-with-argument.sql | statements |
        | src/test/resources | statements/statement-with-comment.sql  | statements |
        | src/test/resources | statements/statement.sql               | statements |
        | src/test/resources | statement.sql                          |            |
