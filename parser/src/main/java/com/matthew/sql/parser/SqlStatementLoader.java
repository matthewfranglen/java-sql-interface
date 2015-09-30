package com.matthew.sql.parser;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;

@Component
public class SqlStatementLoader {

    @Autowired private SqlParser parser;

    public SqlStatement parseResource(String resource) throws IOException {
        File file = new File(resource);
        File rootDirectory = new File("");

        String name = toName(file);
        String path = toPath(rootDirectory, file);
        String content = loadResource(path);
        SqlStatement.Builder builder = parser.parse(content);

        builder.setName(name);
        builder.setPath(path);

        return builder.build();
    }

    public SqlStatement parseFile(File rootDirectory, File file) throws IOException {
        String name = toName(file);
        String path = toPath(rootDirectory, file);
        String content = loadFile(file);
        SqlStatement.Builder builder = parser.parse(content);

        builder.setName(name);
        builder.setPath(path);

        return builder.build();
    }

    private String loadResource(String path) throws IOException {
        return Resources.toString(Resources.getResource(path), Charsets.UTF_8);
    }

    private String loadFile(File file) throws IOException {
        return Files.toString(file, Charsets.UTF_8);
    }

    private String toName(File file) {
        checkNotNull(file);

        return snakeToCamel(stripExtension(file.getName()));
    }

    private String toPath(File rootDirectory, File file) throws IOException {
        checkNotNull(rootDirectory);
        checkNotNull(file);
        checkState(isContained(rootDirectory, file), "File must be contained within the provided Root Directory");

        return toPath(file).substring(toPath(rootDirectory).length());
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
