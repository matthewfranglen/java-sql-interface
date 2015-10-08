package com.matthew.sql.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.matthew.sql.parser.generated.StatementLexer;
import com.matthew.sql.parser.generated.StatementParser;

public class SqlParser {

    public SqlStatement.Builder parse(String content) {
        StatementLexer lexer = new StatementLexer(new ANTLRInputStream(content));
        StatementParser parser = new StatementParser(new CommonTokenStream(lexer));
        StatementBuildingListener listener = new StatementBuildingListener();

        parser.addParseListener(listener);
        parser.root();

        return listener.getBuilder();
    }


}
