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
        String directoryPath, fileName;

        int fileSeparatorIndex = resource.lastIndexOf("/");
        if (fileSeparatorIndex >= 0) {
            directoryPath = resource.substring(0, fileSeparatorIndex);
            fileName = resource.substring(fileSeparatorIndex + 1);
        }
        else {
            directoryPath = "";
            fileName = resource;
        }

        String rootDirectoryPath = "";
        String content = loadResource(resource);

        return makeStatement(rootDirectoryPath, directoryPath, fileName, content);
    }

    public SqlStatement parseFile(File rootDirectory, File file) throws IOException {
        String rootDirectoryPath = toPath(rootDirectory);
        String directory = toPath(file.getParentFile());
        String content = loadFile(file);

        return makeStatement(rootDirectoryPath, directory, file.getName(), content);
    }

    private SqlStatement makeStatement(String rootDirectory, String directory, String file, String content) {
        String name = toName(file);
        String path = toRelativePath(rootDirectory, directory);
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

    private String toName(String name) {
        checkNotNull(name);

        return skewerToCamel(stripExtension(name));
    }

    private String toRelativePath(String rootDirectory, String directory) {
        checkNotNull(rootDirectory);
        checkNotNull(directory);
        checkState(directory.startsWith(rootDirectory), "Directory must be contained within the provided Root Directory");

        return directory.substring(rootDirectory.length());
    }

    private String stripExtension(String name) {
        return name.contains(".")
            ? name.substring(0, name.lastIndexOf('.'))
            : name;
    }

    private String skewerToCamel(String name) {
        return Arrays.asList(name.split("-"))
            .stream()
            .map(this::capitalizeFirstLetter)
            .collect(Collectors.joining());
    }

    private String capitalizeFirstLetter(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    private String toPath(File file) throws IOException {
        return file.getCanonicalPath().toString();
    }

}
