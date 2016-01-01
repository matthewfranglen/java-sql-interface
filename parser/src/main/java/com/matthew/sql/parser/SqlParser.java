package com.matthew.sql.parser;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import com.matthew.sql.parser.generated.StatementLexer;
import com.matthew.sql.parser.generated.StatementParser;
import com.matthew.sql.statement.SqlStatementBuilder;

public class SqlParser {

    public SqlStatementBuilder parse(String content) {
        StatementLexer lexer = new StatementLexer(new ANTLRInputStream(content));
        StatementParser parser = new StatementParser(new CommonTokenStream(lexer));

        parser.setErrorHandler(new BailErrorStrategy());
        StatementParser.RootContext ctx = parser.root();

        StatementBuildingListener listener = new StatementBuildingListener();
        ParseTreeWalker walker = new ParseTreeWalker();

        walker.walk(listener, ctx);

        return listener.getBuilder();
    }

}
