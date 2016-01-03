package com.matthew.sql.statement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ArgumentListBuilder {

    private List<Argument> arguments;

    public ArgumentListBuilder() {
        arguments = new ArrayList<>();
    }

    public void add(Argument argument) {
        arguments.add(argument);
    }

    public void addAll(Collection<Argument> arguments) {
        this.arguments.addAll(arguments);
    }

    public ArgumentList build() {
        return new ArgumentList(arguments);
    }

}
