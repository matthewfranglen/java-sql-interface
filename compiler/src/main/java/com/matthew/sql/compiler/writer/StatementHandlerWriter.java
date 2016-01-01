package com.matthew.sql.compiler.writer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.maven.plugin.logging.Log;

import com.matthew.sql.generator.CodeGenerator;
import com.matthew.sql.statement.SqlStatement;

import freemarker.template.TemplateException;

public class StatementHandlerWriter extends AbstractWriter {

    public static final String STATEMENT_HANDLER_PATH = "com/matthew/sql/generated/SqlStatementHandler.java";

    private final CodeGenerator generator;

    private final String generatedSourcesDirectory;

    public StatementHandlerWriter(Log log, String generatedSourcesDirectory) {
        super(log);
        this.generatedSourcesDirectory = generatedSourcesDirectory;

        generator = new CodeGenerator();
    }

    public void writeStatementHandler(Collection<SqlStatement> statements) throws IOException, TemplateException {
        File file = new File(generatedSourcesDirectory, STATEMENT_HANDLER_PATH);
        String code = generator.generateStatementHandlerCode(statements);

        write(file, code);
        getLog().info("Written Statement Handler: com.matthew.sql.generated.SqlStatementHandler");
    }

}
