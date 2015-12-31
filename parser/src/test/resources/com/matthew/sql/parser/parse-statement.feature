Feature: Can read SQL Statement Body

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement body is "SELECT * FROM table LIMIT 1;"

    Examples:
        | file                                   |
        | statements/statement-by-name.sql       |
        | statements/statement-with-argument.sql |
        | statements/statement-with-comment.sql  |
        | statements/statement.sql               |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement body is "SELECT * FROM table LIMIT 1;"

    Examples:
        | directory          | file                                   |
        | src/test/resources | statements/statement-by-name.sql       |
        | src/test/resources | statements/statement-with-argument.sql |
        | src/test/resources | statements/statement-with-comment.sql  |
        | src/test/resources | statements/statement.sql               |
