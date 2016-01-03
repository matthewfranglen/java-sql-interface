Feature: Can read SQL Statement takes

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement does not have a list of taken arguments
        And the loaded statement takes 0 arguments
        And the loaded statement taken argument list is not an empty list
        And the loaded statement taken argument list is not a singular list
        And the loaded statement taken argument list is not a multiple list

    Examples: Statements without takes
        | file                                  |
        | statements/statement-by-name.sql      |
        | statements/statement-with-comment.sql |
        | statements/statement.sql              |
        | statement.sql                         |

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement has a list of taken arguments
        And the loaded statement takes <count> arguments
        And the loaded statement taken argument list <is empty?> an empty list
        And the loaded statement taken argument list <is singular?> a singular list
        And the loaded statement taken argument list <is multiple?> a multiple list

    Examples: Statements with takes
        | file                                                             | count | is empty? | is singular? | is multiple? |
        | statements/statement-taking-no-arguments.sql                     | 0     | is        | is not       | is not       |
        | statements/statement-taking-date-argument.sql                    | 1     | is not    | is           | is not       |
        | statements/statement-taking-double-argument.sql                  | 1     | is not    | is           | is not       |
        | statements/statement-taking-float-argument.sql                   | 1     | is not    | is           | is not       |
        | statements/statement-taking-integer-argument.sql                 | 1     | is not    | is           | is not       |
        | statements/statement-taking-long-argument.sql                    | 1     | is not    | is           | is not       |
        | statements/statement-taking-text-argument.sql                    | 1     | is not    | is           | is not       |
        | statements/statement-taking-and-returning-multiple-arguments.sql | 6     | is not    | is not       | is           |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement does not have a list of taken arguments
        And the loaded statement takes 0 arguments
        And the loaded statement taken argument list is not an empty list
        And the loaded statement taken argument list is not a singular list
        And the loaded statement taken argument list is not a multiple list

    Examples: Statements without takes
        | directory          | file                                  |
        | src/test/resources | statements/statement-by-name.sql      |
        | src/test/resources | statements/statement-with-comment.sql |
        | src/test/resources | statements/statement.sql              |
        | src/test/resources | statement.sql                         |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement takes <count> arguments
        And the loaded statement takes <count> arguments
        And the loaded statement taken argument list <is empty?> an empty list
        And the loaded statement taken argument list <is singular?> a singular list
        And the loaded statement taken argument list <is multiple?> a multiple list

    Examples: Statements with takes
        | directory          | file                                                             | count | is empty? | is singular? | is multiple? |
        | src/test/resources | statements/statement-taking-no-arguments.sql                     | 0     | is        | is not       | is not       |
        | src/test/resources | statements/statement-taking-date-argument.sql                    | 1     | is not    | is           | is not       |
        | src/test/resources | statements/statement-taking-double-argument.sql                  | 1     | is not    | is           | is not       |
        | src/test/resources | statements/statement-taking-float-argument.sql                   | 1     | is not    | is           | is not       |
        | src/test/resources | statements/statement-taking-integer-argument.sql                 | 1     | is not    | is           | is not       |
        | src/test/resources | statements/statement-taking-long-argument.sql                    | 1     | is not    | is           | is not       |
        | src/test/resources | statements/statement-taking-text-argument.sql                    | 1     | is not    | is           | is not       |
        | src/test/resources | statements/statement-taking-and-returning-multiple-arguments.sql | 6     | is not    | is not       | is           |
