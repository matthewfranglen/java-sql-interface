Feature: Can read SQL Statement Argument

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement argument count is <arguments>

    Examples:
        | file                                   | arguments |
        | statements/statement-by-name.sql       | 0         |
        | statements/statement-with-argument.sql | 1         |
        | statements/statement-with-comment.sql  | 0         |
        | statements/statement.sql               | 0         |
        | statement.sql                          | 0         |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement argument count is <arguments>

    Examples:
        | directory          | file                                   | arguments |
        | src/test/resources | statements/statement-by-name.sql       | 0         |
        | src/test/resources | statements/statement-with-argument.sql | 1         |
        | src/test/resources | statements/statement-with-comment.sql  | 0         |
        | src/test/resources | statements/statement.sql               | 0         |
        | src/test/resources | statement.sql                          | 0         |
