package com.matthew.sql.parser;

import static com.google.common.base.Preconditions.checkArgument;

import com.google.common.base.Strings;

public class SqlStatement {

    private final String name;
    private final String statement;

    public SqlStatement(String name, String statement) {
        checkArgument(! Strings.isNullOrEmpty(name), "name is null or blank");
        checkArgument(! Strings.isNullOrEmpty(statement), "statement is null or blank");

        this.name = name;
        this.statement = statement;
    }

    public String getName() {
        return name;
    }

    public String getStatement() {
        return statement;
    }

    public static class Builder {

        private String name;
        private String statement;

        public void setName(String name) {
            this.name = name;
        }

        public void setStatement(String statement) {
            this.statement = statement;
        }

        public SqlStatement build() {
            return new SqlStatement(name, statement);
        }
    }
}
