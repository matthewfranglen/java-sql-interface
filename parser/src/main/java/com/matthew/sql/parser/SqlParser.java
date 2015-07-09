package com.matthew.sql.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.matthew.sql.parser.generated.StatementBaseListener;
import com.matthew.sql.parser.generated.StatementLexer;
import com.matthew.sql.parser.generated.StatementParser;

public class SqlParser {

    public SqlStatement parse(String content) {
        StatementLexer lexer = new StatementLexer(new ANTLRInputStream(content));
        StatementParser parser = new StatementParser(new CommonTokenStream(lexer));
        StatementBuildingListener listener = new StatementBuildingListener();

        parser.addParseListener(listener);
        parser.root();

        return listener.getStatement();
    }

    private static class StatementBuildingListener extends StatementBaseListener {

        private final SqlStatement.Builder builder;

        public StatementBuildingListener() {
            builder = new SqlStatement.Builder();
        }

        public SqlStatement getStatement() {
            return builder.build();
        }

        @Override
        public void exitName(StatementParser.NameContext ctx) {
            builder.setName(ctx.getText());
        }

        @Override
        public void exitStatement(StatementParser.StatementContext ctx) {
            builder.setStatement(ctx.getText());
        }
    }
}
