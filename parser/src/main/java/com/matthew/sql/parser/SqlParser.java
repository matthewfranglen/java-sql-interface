package com.matthew.sql.parser;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.matthew.sql.parser.generated.StatementLexer;
import com.matthew.sql.parser.generated.StatementParser;

@Component
public class SqlParser {

    public SqlStatement parse(File file) throws IOException {
        return parse(toName(file), Files.toString(file, Charsets.UTF_8));
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

    public String toName(File file) {
        checkNotNull(file);

        return snakeToCamel(stripExtension(file.getName()));
    }

    public String toPackage(File rootDirectory, File file) throws IOException {
        checkNotNull(rootDirectory);
        checkNotNull(file);
        checkState(isContained(rootDirectory, file), "File must be contained within the provided Root Directory");

        String path = toPath(file).substring(toPath(rootDirectory).length());

        return path.replaceAll(File.separator, ".");
    }

    private String stripExtension(String name) {
        return name.contains(".")
            ? name.substring(0, name.lastIndexOf('.'))
            : name;
    }

    private String snakeToCamel(String name) {
        return Arrays.asList(name.split("-"))
            .stream()
            .map(this::capitalizeFirstLetter)
            .collect(Collectors.joining());
    }

    private String capitalizeFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    private boolean isContained(File directory, File file) throws IOException {
        return toPath(file).startsWith(toPath(directory));
    }

    private String toPath(File file) throws IOException {
        return file.getCanonicalPath().toString();
    }

}
