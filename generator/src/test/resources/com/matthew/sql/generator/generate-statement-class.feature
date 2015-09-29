Feature: Can create Statement class

    Scenario Outline: Reading from different files

        Given the name "<name>"
        And the statement "<statement file>"
        When the statement is converted to a statement class
        Then the code looks like "<code file>"

    Examples:
        | name      | statement file           | code file           |
        | Statement | statements/statement.sql | code/Statement.java |
