package com.matthew.sql.statement;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;
import java.util.Collection;

import com.google.common.base.Strings;

public class SqlStatement {

    private final String name;
    private final String path;
    private final String statement;

    private final Collection<Argument> takes;
    private final Collection<Argument> returns;

    public SqlStatement(String name, String path, String statement, Collection<Argument> takes, Collection<Argument> returns) {
        checkArgument(! Strings.isNullOrEmpty(name), "name is null or blank");
        checkArgument(! Strings.isNullOrEmpty(statement), "statement is null or blank");

        this.name = name.trim();
        this.path = stripLeadingSlash(path).trim();
        this.statement = statement.trim();
        this.takes = takes;
        this.returns = returns;
    }

    private String stripLeadingSlash(String path) {
        return path.startsWith("/") ? path.substring(1) : path;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public String getStatement() {
        return statement;
    }

    public Collection<Argument> getTakes() {
        return takes;
    }

    public Collection<Argument> getReturns() {
        return returns;
    }

    public boolean inDefaultPackage() {
        return Strings.isNullOrEmpty(path);
    }

    public boolean hasTakes() {
        return takes != null;
    }

    public boolean hasReturns() {
        return returns != null;
    }

    public String getPackage() {
        if (inDefaultPackage()) {
            return "";
        }
        return path.replaceAll(File.separator, ".");
    }

}
