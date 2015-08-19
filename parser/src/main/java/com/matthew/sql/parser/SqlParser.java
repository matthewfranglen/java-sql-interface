package com.matthew.sql.parser;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.matthew.sql.parser.generated.StatementBaseListener;
import com.matthew.sql.parser.generated.StatementLexer;
import com.matthew.sql.parser.generated.StatementParser;

public class SqlParser {

    public SqlStatement parseResource(String path) throws IOException {
        return parse(toStatementName(path), loadResource(path));
    }

    private String toStatementName(String path) {
        String[] parts = splitByLastSeparator(path);
        return directoryToPackage(parts[0]) + fileToClass(parts[1]);
    }

    private String[] splitByLastSeparator(String path) {
        if (path.contains("/")) {
            int index = path.lastIndexOf("/") + 1;
            return new String[] {path.substring(0, index), path.substring(index)};
        }
        else {
            return new String[] {"", path};
        }
    }

    private String directoryToPackage(String directory) {
        return directory.replaceAll("/", ".");
    }

    private String fileToClass(String file) {
        StringBuilder result = new StringBuilder();

        for (String part : stripExtension(file).split("-")) {
            result.append(capitalizeFirstLetter(part));
        }

        return result.toString();
    }

    private String stripExtension(String file) {
        return file.contains(".")
            ? file.substring(0, file.indexOf("."))
            : file;
    }

    private String capitalizeFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    private String loadResource(String path) throws IOException {
        return Resources.toString(Resources.getResource(path), Charsets.UTF_8);
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
