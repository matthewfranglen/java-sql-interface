package com.matthew.sql.statement;

import static com.google.common.base.Preconditions.checkState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

public class SqlStatementBuilder {

    private static final Set<CollectionState> COLLECTING_STATES = EnumSet.of(
            CollectionState.COLLECTING_TAKES, CollectionState.COLLECTING_RETURNS
        );

    private String name;
    private String path;
    private String statement;

    private Collection<Argument> takes;
    private Collection<Argument> returns;

    private CollectionState state;
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

    public void enterTakes() {
        checkState(takes == null, "Encountered two takes definitions");
        checkState(state == CollectionState.NOT_COLLECTING, "Encountered takes definition inside argument list");

        state = CollectionState.COLLECTING_TAKES;
        current = new ArrayList<>();
    }

    public void exitTakes() {
        checkState(state == CollectionState.COLLECTING_TAKES, "Left takes definition without entering it");

        state = CollectionState.NOT_COLLECTING;
        takes = current;
        current = null;
    }

    public void enterReturns() {
        checkState(returns == null, "Encountered two returns definitions");
        checkState(state == CollectionState.NOT_COLLECTING, "Encountered takes definition inside argument list");

        state = CollectionState.COLLECTING_RETURNS;
        current = new ArrayList<>();
    }

    public void exitReturns() {
        checkState(state == CollectionState.COLLECTING_RETURNS, "Left returns definition without entering it");

        state = CollectionState.NOT_COLLECTING;
        returns = current;
        current = null;
    }

    public void addArgument(Argument argument) {
        checkState(COLLECTING_STATES.contains(state), "Encountered argument outside of collection");

        current.add(argument);
    }

    public SqlStatement build() {
        return new SqlStatement(name, path, statement);
    }

    private enum CollectionState {
        NOT_COLLECTING,
        COLLECTING_TAKES,
        COLLECTING_RETURNS;
    }

}
