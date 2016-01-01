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

	@Override public void enterTakes_line(StatementParser.Takes_lineContext ctx) {
        builder.enterTakes();
    }

	@Override public void exitTakes_line(StatementParser.Takes_lineContext ctx) {
        builder.exitTakes();
    }

	@Override public void enterReturns_line(StatementParser.Returns_lineContext ctx) {
        builder.enterReturns();
    }

	@Override public void exitReturns_line(StatementParser.Returns_lineContext ctx) {
        builder.exitReturns();
    }

	@Override public void enterArgument(StatementParser.ArgumentContext ctx) {
    }

    @Override
    public void exitStatement(StatementParser.StatementContext ctx) {
        builder.setStatement(ctx.getText());
    }

}
