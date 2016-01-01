package com.matthew.sql.compiler.writer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.maven.plugin.logging.Log;

import com.matthew.sql.compiler.statement.GeneratedCode;
import com.matthew.sql.generator.CodeGenerator;
import com.matthew.sql.statement.SqlStatement;

import freemarker.core.ParseException;
import freemarker.template.TemplateException;

public class StatementWriter extends AbstractWriter {

    private final CodeGenerator generator;

    private final String generatedSourcesDirectory;

    public StatementWriter(Log log, String generatedSourcesDirectory) {
        super(log);
        this.generatedSourcesDirectory = generatedSourcesDirectory;

        generator = new CodeGenerator();
    }

    public void writeStatements(Collection<SqlStatement> statements) throws ParseException, TemplateException, IOException {
        for (SqlStatement current : statements) {
            GeneratedCode code = makeStatementCode(current);
            writeStatement(code);
        }
    }

    private GeneratedCode makeStatementCode(SqlStatement statement) throws ParseException, TemplateException, IOException {
        return new GeneratedCode(statement, generator.generateStatementCode(statement));
    }

    private void writeStatement(GeneratedCode code) throws IOException {
        SqlStatement statement = code.getStatement();

        String path = String.format("%s/%s.java", statement.getPath(), statement.getName());
        File file = new File(generatedSourcesDirectory, path);

        write(file, code.getCode());
        getLog().info(String.format("Written Statement: %s.%s", statement.getPackage(), statement.getName()));
    }

}
