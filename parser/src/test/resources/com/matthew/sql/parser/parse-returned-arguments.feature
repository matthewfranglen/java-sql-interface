Feature: Can read SQL Statement returns

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement takes does not have a list of returned arguments

    Examples: Statements without returns
        | file                                  |
        | statements/statement-by-name.sql      |
        | statements/statement-with-comment.sql |
        | statements/statement.sql              |
        | statement.sql                         |

    Scenario Outline: Reading from different resources

        Given the resource "<file>"
        When the resource is loaded as a statement
        Then the loaded statement returns <count> arguments

    Examples: Statements with returns
        | file                                                | count |
        | statements/statement-returning-no-arguments.sql     | 0     |
        | statements/statement-returning-date-argument.sql    | 1     |
        | statements/statement-returning-double-argument.sql  | 1     |
        | statements/statement-returning-float-argument.sql   | 1     |
        | statements/statement-returning-integer-argument.sql | 1     |
        | statements/statement-returning-long-argument.sql    | 1     |
        | statements/statement-returning-text-argument.sql    | 1     |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement takes does not have a list of returned arguments

    Examples: Statements without returns
        | directory          | file                                  |
        | src/test/resources | statements/statement-by-name.sql      |
        | src/test/resources | statements/statement-with-comment.sql |
        | src/test/resources | statements/statement.sql              |
        | src/test/resources | statement.sql                         |

    Scenario Outline: Reading from different files

        Given the file "<file>" and the directory "<directory>"
        When the file is loaded as a statement
        Then the loaded statement returns <count> arguments

    Examples: Statements with returns
        | directory          | file                                                | count |
        | src/test/resources | statements/statement-returning-no-arguments.sql     | 0     |
        | src/test/resources | statements/statement-returning-date-argument.sql    | 1     |
        | src/test/resources | statements/statement-returning-double-argument.sql  | 1     |
        | src/test/resources | statements/statement-returning-float-argument.sql   | 1     |
        | src/test/resources | statements/statement-returning-integer-argument.sql | 1     |
        | src/test/resources | statements/statement-returning-long-argument.sql    | 1     |
        | src/test/resources | statements/statement-returning-text-argument.sql    | 1     |
