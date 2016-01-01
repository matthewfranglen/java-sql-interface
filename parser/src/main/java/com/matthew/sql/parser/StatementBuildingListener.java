package com.matthew.sql.parser;

import com.matthew.sql.parser.generated.StatementBaseListener;
import com.matthew.sql.parser.generated.StatementParser;
import com.matthew.sql.statement.SqlStatementBuilder;

public class StatementBuildingListener extends StatementBaseListener {

    private final SqlStatementBuilder builder;

    public StatementBuildingListener() {
        builder = new SqlStatementBuilder();
    }

    public SqlStatementBuilder getBuilder() {
        return builder;
    }

    @Override
    public void exitStatement(StatementParser.StatementContext ctx) {
        builder.setStatement(ctx.getText());
    }

}
