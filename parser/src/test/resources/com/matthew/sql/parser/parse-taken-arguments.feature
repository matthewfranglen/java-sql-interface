Feature: Can read SQL Statement takes

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement does not have a list of taken arguments

    Examples: Statements without takes
        | file                                  |
        | statements/statement-by-name.sql      |
        | statements/statement-with-comment.sql |
        | statements/statement.sql              |
        | statement.sql                         |

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement takes <count> arguments

    Examples: Statements with takes
        | file                                             | count |
        | statements/statement-taking-no-arguments.sql     | 0     |
        | statements/statement-taking-date-argument.sql    | 1     |
        | statements/statement-taking-double-argument.sql  | 1     |
        | statements/statement-taking-float-argument.sql   | 1     |
        | statements/statement-taking-integer-argument.sql | 1     |
        | statements/statement-taking-long-argument.sql    | 1     |
        | statements/statement-taking-text-argument.sql    | 1     |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement takes does not have a list of taken arguments

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

    Examples: Statements with takes
        | directory          | file                                             | count |
        | src/test/resources | statements/statement-taking-no-arguments.sql     | 0     |
        | src/test/resources | statements/statement-taking-date-argument.sql    | 1     |
        | src/test/resources | statements/statement-taking-double-argument.sql  | 1     |
        | src/test/resources | statements/statement-taking-float-argument.sql   | 1     |
        | src/test/resources | statements/statement-taking-integer-argument.sql | 1     |
        | src/test/resources | statements/statement-taking-long-argument.sql    | 1     |
        | src/test/resources | statements/statement-taking-text-argument.sql    | 1     |
