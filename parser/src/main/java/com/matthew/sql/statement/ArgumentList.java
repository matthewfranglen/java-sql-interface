package com.matthew.sql.statement;

import java.util.Iterator;
import java.util.List;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public class ArgumentList implements Iterable<Argument> {

    public static final ArgumentList EMPTY = new ArgumentList(null);

    private final List<Argument> arguments;

    public ArgumentList(List<Argument> arguments) {
        if (arguments != null) {
            this.arguments = ImmutableList.copyOf(arguments);
        }
        else {
            this.arguments = null;
        }
    }

    public Iterator<Argument> iterator() {
        return arguments.iterator();
    }

    public Argument getFirst() {
        return arguments.get(0);
    }

    public int size() {
        return isDefined() ? arguments.size() : 0;
    }

    public boolean isDefined() {
        return arguments != null;
    }

    public boolean isEmpty() {
        return isDefined() && arguments.isEmpty();
    }

    public boolean isSingular() {
        return isDefined() && arguments.size() == 1;
    }

    public boolean isMultiple() {
        return isDefined() && arguments.size() > 1;
    }

    public String toString() {
        return Joiner.on(", ").join(arguments);
    }

}
