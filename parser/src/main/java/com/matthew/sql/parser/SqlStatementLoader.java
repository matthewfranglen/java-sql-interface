package com.matthew.sql.parser;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

@Component
public class SqlStatementLoader {

    @Autowired private SqlParser parser;

    public SqlStatement parseResource(String path) throws IOException {
        return parser.parse(toStatementName(path), loadResource(path));
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

}
