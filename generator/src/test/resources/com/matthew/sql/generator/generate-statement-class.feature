Feature: Can create Statement class

    Scenario Outline: Reading from different files

        Given the name "<name>"
        And the path "<path>"
        And the statement "<statement file>"
        When the statement is converted to a statement class
        Then the code looks like "<code file>"

    Examples:
        | name      | path       | statement file           | code file                         |
        | Statement | statements | statements/statement.sql | code/Statement.java               |
        | Statement |            | statements/statement.sql | code/StatementWithoutPackage.java |
