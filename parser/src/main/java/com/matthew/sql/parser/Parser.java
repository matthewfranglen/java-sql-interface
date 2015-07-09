package com.matthew.sql.parser;

public class Parser {

    public Statement parse(String content) {
        Statement.Builder builder = new Statement.Builder();
        StatementParser parser = new StatementParser(content);

        return builder.build();
    }
}
