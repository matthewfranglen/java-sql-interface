package com.matthew.sql.statement;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.Collection;

import com.google.common.base.Strings;

public class SqlStatementBuilder {

    private NameBuilder nameBuilder;
    private String statement;

    private boolean setTakes;
    private ArgumentList takes;

    private boolean setReturns;
    private ArgumentList returns;

    private ArgumentListBuilder current;

    public SqlStatementBuilder() {
        nameBuilder = new NameBuilder();

        setTakes = false;
        takes = ArgumentList.EMPTY;

        setReturns = false;
        returns = ArgumentList.EMPTY;
    }

    public void setName(String name) {
        nameBuilder.setName(name);
    }

    public void setPath(String path) {
        nameBuilder.setPath(path);
    }

    public void setStatement(String statement) {
        checkArgument(! Strings.isNullOrEmpty(statement), "statement is null or blank");

        this.statement = statement.trim();
    }

    public void setTakes(Collection<Argument> arguments) {
        checkState(! setTakes, "Encountered two takes definitions");
        setTakes = true;

        ArgumentListBuilder builder = new ArgumentListBuilder();
        builder.addAll(arguments);
        takes = builder.build();
    }

    public void setReturns(Collection<Argument> arguments) {
        checkState(! setReturns, "Encountered two returns definitions");
        setReturns = true;

        ArgumentListBuilder builder = new ArgumentListBuilder();
        builder.addAll(arguments);
        returns = builder.build();
    }

    public void exitTakes() {
        checkState(! setTakes, "Encountered two takes definitions");
        setTakes = true;

        if (current != null) {
            takes = current.build();
            current = null;
        }
    }

    public void exitReturns() {
        checkState(! setReturns, "Encountered two returns definitions");
        setReturns = true;

        if (current != null) {
            returns = current.build();
            current = null;
        }
    }

    public void addArgument(Argument argument) {
        if (current == null) {
            current = new ArgumentListBuilder();
        }

        current.add(argument);
    }

    public SqlStatement build() {
        return new SqlStatement(nameBuilder.build(), statement, takes, returns);
    }

}
