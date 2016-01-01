package com.matthew.sql.parser;

import com.matthew.sql.parser.generated.StatementBaseListener;
import com.matthew.sql.parser.generated.StatementParser;
import com.matthew.sql.statement.Argument;
import com.matthew.sql.statement.ArgumentType;
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
    public void exitInvalid_type(StatementParser.Invalid_typeContext ctx) {
        throw new IllegalStateException("Invalid type encountered: " + ctx.getText());
    }

    @Override
    public void exitStatement(StatementParser.StatementContext ctx) {
        builder.setStatement(ctx.getText());
    }

	@Override
    public void exitTakes_line(StatementParser.Takes_lineContext ctx) {
        builder.exitTakes();
    }

	@Override
    public void exitReturns_line(StatementParser.Returns_lineContext ctx) {
        builder.exitReturns();
    }

	@Override
    public void enterArgument(StatementParser.ArgumentContext ctx) {
        ArgumentType type = getArgumentType(ctx.argument_type());
        String name = ctx.argument_name().getText();

        builder.addArgument(new Argument(type, name));
    }

    private ArgumentType getArgumentType(StatementParser.Argument_typeContext ctx) {
        if (ctx.TEXT_TYPE() != null) {
            return ArgumentType.TEXT;
        }
        if (ctx.WHOLE_NUMBER_TYPE() != null) {
            return ArgumentType.WHOLE_NUMBER;
        }
        if (ctx.FRACTIONAL_NUMBER_TYPE() != null) {
            return ArgumentType.FRACTIONAL_NUMBER;
        }
        if (ctx.TIMESTAMP_TYPE() != null) {
            return ArgumentType.TIMESTAMP;
        }

        throw new RuntimeException("Unrecognized Argument Type: " + ctx.getText());
    }

}
