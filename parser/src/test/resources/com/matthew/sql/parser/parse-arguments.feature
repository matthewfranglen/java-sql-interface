Feature: Can read SQL Statement Argument

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement takes <count> arguments

    Examples: Statements without Arguments
        | file                                  | count |
        | statements/statement-by-name.sql      | 0     |
        | statements/statement-with-comment.sql | 0     |
        | statements/statement.sql              | 0     |
        | statement.sql                         | 0     |

    Examples: Statements with Arguments
        | file                                             | count |
        | statements/statement-taking-date-argument.sql    | 1     |
        | statements/statement-taking-double-argument.sql  | 1     |
        | statements/statement-taking-float-argument.sql   | 1     |
        | statements/statement-taking-integer-argument.sql | 1     |
        | statements/statement-taking-long-argument.sql    | 1     |
        | statements/statement-taking-text-argument.sql    | 1     |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement takes <count> arguments

    Examples: Statements without Arguments
        | directory          | file                                  | count |
        | src/test/resources | statements/statement-by-name.sql      | 0     |
        | src/test/resources | statements/statement-with-comment.sql | 0     |
        | src/test/resources | statements/statement.sql              | 0     |
        | src/test/resources | statement.sql                         | 0     |

    Examples: Statements with Arguments
        | directory          | file                                             | count |
        | src/test/resources | statements/statement-taking-date-argument.sql    | 1     |
        | src/test/resources | statements/statement-taking-double-argument.sql  | 1     |
        | src/test/resources | statements/statement-taking-float-argument.sql   | 1     |
        | src/test/resources | statements/statement-taking-integer-argument.sql | 1     |
        | src/test/resources | statements/statement-taking-long-argument.sql    | 1     |
        | src/test/resources | statements/statement-taking-text-argument.sql    | 1     |
