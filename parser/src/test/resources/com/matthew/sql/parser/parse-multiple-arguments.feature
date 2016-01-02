Feature: Can read SQL Statement with multiple takes and returns

    Scenario: Reading from different resources

        Given the resource "statements/statement-taking-and-returning-multiple-arguments.sql"
        When the resource is loaded as a statement
        Then the loaded statement takes 6 arguments
        And the loaded statement returns 6 arguments

    Scenario: Reading from different files

        Given the file "statements/statement-taking-and-returning-multiple-arguments.sql" and the directory "src/test/resources"
        When the file is loaded as a statement
        Then the loaded statement takes 6 arguments
        And the loaded statement returns 6 arguments
