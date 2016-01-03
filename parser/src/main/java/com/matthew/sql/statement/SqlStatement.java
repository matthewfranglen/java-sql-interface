package com.matthew.sql.statement;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.base.Strings;

public class SqlStatement {

    private final String statement;

    private final Name name;
    private final ArgumentList takes;
    private final ArgumentList returns;

    public SqlStatement(Name name, String statement, ArgumentList takes, ArgumentList returns) {
        checkArgument(! Strings.isNullOrEmpty(statement), "statement is null or blank");

        this.name = checkNotNull(name, "name is null or blank");
        this.statement = statement;
        this.takes = checkNotNull(takes, "takes is null");
        this.returns = checkNotNull(returns, "returns is null");
    }

    public Name getName() {
        return name;
    }

    public String getStatement() {
        return statement;
    }

    public ArgumentList getTakes() {
        return takes;
    }

    public ArgumentList getReturns() {
        return returns;
    }

}
