package com.matthew.sql.parser;

import java.io.File;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.matthew.sql.parser.generated.StatementBaseListener;
import com.matthew.sql.parser.generated.StatementLexer;
import com.matthew.sql.parser.generated.StatementParser;

@Component
public class SqlParser {

    public SqlStatement parse(File file) throws IOException {
        return parse(file.getName(), Files.toString(file, Charsets.UTF_8));
    }

    public SqlStatement parse(String name, String content) {
        StatementLexer lexer = new StatementLexer(new ANTLRInputStream(content));
        StatementParser parser = new StatementParser(new CommonTokenStream(lexer));
        StatementBuildingListener listener = new StatementBuildingListener();

        listener.setName(name);
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

        public void setName(String name) {
            builder.setName(name);
        }

        @Override
        public void exitStatement(StatementParser.StatementContext ctx) {
            builder.setStatement(ctx.getText());
        }
    }
}
