package com.matthew.sql.generator;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.matthew.sql.parser.SqlStatement;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class StatementGenerator {

    private final Configuration freemarkerConfiguration;

    public StatementGenerator() {
        freemarkerConfiguration = makeFreemarkerConfiguration();
    }

    private Configuration makeFreemarkerConfiguration() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);

        configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        return configuration;
    }

    public String generateStatementCode(SqlStatement statement) throws TemplateNotFoundException, MalformedTemplateNameException, TemplateException, ParseException, IOException {
        StringWriter writer = new StringWriter();
        Template template = freemarkerConfiguration.getTemplate("statement.ftl");
        Map<String, ?> parameters = makeStatementParameters(statement);

        template.process(parameters, writer);

        return writer.toString();
    }

    private Map<String, ?> makeStatementParameters(SqlStatement statement) {
        Map<String, ?> parameters = new HashMap<>();
    }

}
