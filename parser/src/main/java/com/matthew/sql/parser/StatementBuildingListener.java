package com.matthew.sql.parser;

import com.matthew.sql.parser.generated.StatementBaseListener;
import com.matthew.sql.parser.generated.StatementParser;

public class StatementBuildingListener extends StatementBaseListener {

    private final SqlStatement.Builder builder;

    public StatementBuildingListener() {
        builder = new SqlStatement.Builder();
    }

    public SqlStatement.Builder getBuilder() {
        return builder;
    }

    @Override
    public void exitStatement(StatementParser.StatementContext ctx) {
        builder.setStatement(ctx.getText());
    }
}
