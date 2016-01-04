package com.matthew.sql.statement;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.Collection;

import com.google.common.base.Strings;

public class SqlStatementBuilder {

    private NameBuilder nameBuilder;
    private String statement;

    private boolean setTakes;
    private ArgumentList takes;

    private boolean setReturns;
    private ArgumentList returns;

    private Collection<Argument> encounteredArguments;

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
        takes = buildArgumentList(arguments);
    }

    public void setReturns(Collection<Argument> arguments) {
        checkState(! setReturns, "Encountered two returns definitions");

        setReturns = true;
        returns = buildArgumentList(arguments);
    }

    private ArgumentList buildArgumentList(Collection<Argument> arguments) {
        ArgumentListBuilder builder = new ArgumentListBuilder();

        if (arguments != null) {
            builder.addAll(arguments);
        }

        return builder.build();
    }

    public void exitTakes() {
        setTakes(encounteredArguments);
        encounteredArguments = null;
    }

    public void exitReturns() {
        setReturns(encounteredArguments);
        encounteredArguments = null;
    }

    public void addArgument(Argument argument) {
        if (encounteredArguments == null) {
            encounteredArguments = new ArrayList<>();
        }

        encounteredArguments.add(argument);
    }

    public SqlStatement build() {
        return new SqlStatement(nameBuilder.build(), statement, takes, returns);
    }

}
