package com.matthew.sql.compiler.reader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.codehaus.plexus.util.FileUtils;

import com.google.common.base.Joiner;
import com.matthew.sql.parser.SqlStatement;
import com.matthew.sql.parser.SqlStatementLoader;

public class StatementReader {

    private final SqlStatementLoader loader;

    private final String projectRootDirectory;
    private final String statementRootDirectory;
    private final Collection<String> includedFiles;
    private final Collection<String> excludedFiles;

    public StatementReader(String projectRootDirectory, String statementRootDirectory, Collection<String> includedFiles, Collection<String> excludedFiles) {
        this.projectRootDirectory = projectRootDirectory;
        this.statementRootDirectory = statementRootDirectory;
        this.includedFiles = includedFiles;
        this.excludedFiles = excludedFiles;

        loader = new SqlStatementLoader();
    }

    public Collection<SqlStatement> getStatements() throws IOException {
        File rootDirectory = new File(projectRootDirectory, statementRootDirectory);
        Collection<File> statementFiles = getStatementFiles(rootDirectory);

        return parseStatementFileList(rootDirectory, statementFiles);
    }

    private Collection<File> getStatementFiles(File directory) throws IOException {
        Joiner joiner = Joiner.on(",");
        String includes = joiner.join(includedFiles);
        String excludes = joiner.join(excludedFiles);

        return FileUtils.getFiles(directory, includes, excludes);
    }

    private Collection<SqlStatement> parseStatementFileList(File rootDirectory, Collection<File> statementFiles) {
        Collection<SqlStatement> result = new ArrayList<>(statementFiles.size());

        for (File current : statementFiles) {
            result.add(parseStatementFile(rootDirectory, current));
        }

        return result;
    }

    private SqlStatement parseStatementFile(File rootDirectory, File statement) {
        try {
            return loader.parseFile(rootDirectory, statement);
        }
        catch (RuntimeException e) {
            throw e;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
