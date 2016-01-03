Feature: Can read SQL Statement returns

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement does not have a list of returned arguments
        And the loaded statement returns 0 arguments
        And the loaded statement returned argument list is not an empty list
        And the loaded statement returned argument list is not a singular list
        And the loaded statement returned argument list is not a multiple list

    Examples: Statements without returns
        | file                                  |
        | statements/statement-by-name.sql      |
        | statements/statement-with-comment.sql |
        | statements/statement.sql              |
        | statement.sql                         |

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement has a list of returned arguments
        And the loaded statement returns <count> arguments
        And the loaded statement returned argument list <is empty?> an empty list
        And the loaded statement returned argument list <is singular?> a singular list
        And the loaded statement returned argument list <is multiple?> a multiple list

    Examples: Statements with returns
        | file                                                             | count | is empty? | is singular? | is multiple? |
        | statements/statement-returning-no-arguments.sql                  | 0     | is        | is not       | is not       |
        | statements/statement-returning-date-argument.sql                 | 1     | is not    | is           | is not       |
        | statements/statement-returning-double-argument.sql               | 1     | is not    | is           | is not       |
        | statements/statement-returning-float-argument.sql                | 1     | is not    | is           | is not       |
        | statements/statement-returning-integer-argument.sql              | 1     | is not    | is           | is not       |
        | statements/statement-returning-long-argument.sql                 | 1     | is not    | is           | is not       |
        | statements/statement-returning-text-argument.sql                 | 1     | is not    | is           | is not       |
        | statements/statement-taking-and-returning-multiple-arguments.sql | 6     | is not    | is not       | is           |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement does not have a list of returned arguments
        And the loaded statement returns 0 arguments
        And the loaded statement returned argument list is not an empty list
        And the loaded statement returned argument list is not a singular list
        And the loaded statement returned argument list is not a multiple list

    Examples: Statements without returns
        | directory          | file                                  |
        | src/test/resources | statements/statement-by-name.sql      |
        | src/test/resources | statements/statement-with-comment.sql |
        | src/test/resources | statements/statement.sql              |
        | src/test/resources | statement.sql                         |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement has a list of returned arguments
        And the loaded statement returns <count> arguments
        And the loaded statement returned argument list <is empty?> an empty list
        And the loaded statement returned argument list <is singular?> a singular list
        And the loaded statement returned argument list <is multiple?> a multiple list

    Examples: Statements with returns
        | directory          | file                                                             | count | is empty? | is singular? | is multiple? |
        | src/test/resources | statements/statement-returning-no-arguments.sql                  | 0     | is        | is not       | is not       |
        | src/test/resources | statements/statement-returning-date-argument.sql                 | 1     | is not    | is           | is not       |
        | src/test/resources | statements/statement-returning-double-argument.sql               | 1     | is not    | is           | is not       |
        | src/test/resources | statements/statement-returning-float-argument.sql                | 1     | is not    | is           | is not       |
        | src/test/resources | statements/statement-returning-integer-argument.sql              | 1     | is not    | is           | is not       |
        | src/test/resources | statements/statement-returning-long-argument.sql                 | 1     | is not    | is           | is not       |
        | src/test/resources | statements/statement-returning-text-argument.sql                 | 1     | is not    | is           | is not       |
        | src/test/resources | statements/statement-taking-and-returning-multiple-arguments.sql | 6     | is not    | is not       | is           |
