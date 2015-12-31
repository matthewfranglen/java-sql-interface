package com.matthew.sql.compiler.writer;

import java.io.File;
import java.util.Collection;

import org.apache.maven.plugin.logging.Log;

import com.matthew.sql.generator.CodeGenerator;
import com.matthew.sql.parser.SqlStatement;

public class StatementHandlerWriter extends AbstractWriter {

    private final CodeGenerator generator;

    private final String generatedSourcesDirectory;

    public StatementHandlerWriter(Log log, String generatedSourcesDirectory) {
        super(log);
        this.generatedSourcesDirectory = generatedSourcesDirectory;

        generator = new CodeGenerator();
    }

    public void writeStatementHandler(Collection<SqlStatement> statements) throws Exception {
        String path = "com/matthew/sql/generated/SqlStatementHandler.java";
        File file = new File(generatedSourcesDirectory, path);
        String code = generator.generateStatementHandlerCode(statements);

        write(file, code);
        getLog().info("Written Statement Handler: com.matthew.sql.generated.SqlStatementHandler");
    }

}
