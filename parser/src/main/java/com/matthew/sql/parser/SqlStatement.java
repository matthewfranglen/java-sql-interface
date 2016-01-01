package com.matthew.sql.parser;

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

        if (path.startsWith("/")) {
            path = path.substring(1);
        }

        this.name = name.trim();
        this.path = path.trim();
        this.statement = statement.trim();
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
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

    public String getStatement() {
        return statement;
    }

    public static class Builder {

        private String name;
        private String path;
        private String statement;

        public void setName(String name) {
            this.name = name;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public void setStatement(String statement) {
            this.statement = statement;
        }

        public SqlStatement build() {
            return new SqlStatement(name, path, statement);
        }
    }
}
