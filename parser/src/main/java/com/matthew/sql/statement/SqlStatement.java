package com.matthew.sql.statement;

import static com.google.common.base.Preconditions.checkArgument;

import java.io.File;

import com.google.common.base.Strings;

public class SqlStatement {

    private final String name;
    private final String path;
    private final String statement;

    public SqlStatement(String name, String path, String statement) {
        checkArgument(! Strings.isNullOrEmpty(name), "name is null or blank");
        checkArgument(! Strings.isNullOrEmpty(statement), "statement is null or blank");

        this.name = name.trim();
        this.path = stripLeadingSlash(path).trim();
        this.statement = statement.trim();
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

    public boolean inDefaultPackage() {
        return Strings.isNullOrEmpty(path);
    }

    public String getPackage() {
        if (inDefaultPackage()) {
            return "";
        }
        return path.replaceAll(File.separator, ".");
    }

}
