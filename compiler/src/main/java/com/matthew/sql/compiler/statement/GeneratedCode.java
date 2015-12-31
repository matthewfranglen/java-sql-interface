package com.matthew.sql.compiler.statement;

import com.matthew.sql.parser.SqlStatement;

public class GeneratedCode {

    private final SqlStatement statement;
    private final String code;

    public GeneratedCode(SqlStatement statement, String code) {
        this.statement = statement;
        this.code = code;
    }

    public SqlStatement getStatement() {
        return statement;
    }

    public String getCode() {
        return code;
    }

}
