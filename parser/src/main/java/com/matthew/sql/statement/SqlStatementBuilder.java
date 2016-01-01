package com.matthew.sql.statement;

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class SqlStatementBuilder {

    private String name;
    private String path;
    private String statement;

    private Collection<Argument> takes;
    private Collection<Argument> returns;

    private Collection<Argument> current;

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public void exitTakes() {
        checkState(takes == null, "Encountered two takes definitions");

        if (current == null) {
            takes = Collections.emptyList();
        }
        else {
            takes = current;
            current = null;
        }
    }

    public void exitReturns() {
        checkState(returns == null, "Encountered two returns definitions");

        if (current == null) {
            returns = Collections.emptyList();
        }
        else {
            returns = current;
            current = null;
        }
    }

    public void addArgument(Argument argument) {
        if (current == null) {
            current = new ArrayList<>();
        }

        current.add(argument);
    }

    public SqlStatement build() {
        return new SqlStatement(name, path, statement, takes, returns);
    }

}
