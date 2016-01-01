package com.matthew.sql.statement;

public class SqlStatementBuilder {

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
