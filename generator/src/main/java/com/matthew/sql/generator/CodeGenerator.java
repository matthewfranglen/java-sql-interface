package com.matthew.sql.generator;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.matthew.sql.parser.SqlStatement;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class CodeGenerator {

    private static final String STATEMENT_HANDLER_TEMPLATE =  "statement-handler.ftl";
    private static final String STATEMENT_TEMPLATE =  "statement.ftl";

    private final Configuration freemarkerConfiguration;

    public CodeGenerator() {
        freemarkerConfiguration = makeFreemarkerConfiguration();
    }

    private Configuration makeFreemarkerConfiguration() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);

        configuration.setClassForTemplateLoading(this.getClass(), "/templates");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        return configuration;
    }

    public String generateStatementHandlerCode(Collection<SqlStatement> statements) throws TemplateNotFoundException, MalformedTemplateNameException, TemplateException, ParseException, IOException {
        return applyTemplate(STATEMENT_HANDLER_TEMPLATE, makeStatementHandlerParameters(statements));
    }

    public String generateStatementCode(SqlStatement statement) throws TemplateNotFoundException, MalformedTemplateNameException, TemplateException, ParseException, IOException {
        return applyTemplate(STATEMENT_TEMPLATE, makeStatementParameters(statement));
    }

    private String applyTemplate(String template, Map<String, ?> model) throws TemplateNotFoundException, MalformedTemplateNameException, TemplateException, ParseException, IOException {
        StringWriter writer = new StringWriter();

        freemarkerConfiguration.getTemplate(template)
            .process(model, writer);

        return writer.toString();
    }

    private Map<String, ?> makeStatementHandlerParameters(Collection<SqlStatement> statements) {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put(
            "statements",
            statements.stream()
                .map(this::makeStatementParameters)
                .collect(Collectors.toList())
        );

        return parameters;
    }

    private Map<String, ?> makeStatementParameters(SqlStatement statement) {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("name", makeStatementName(statement));
        parameters.put("hasPackage", true);
        parameters.put("package", statement.getPackage());
        parameters.put("statement", statement.getStatement());

        return parameters;
    }

    private Map<String, ?> makeStatementName(SqlStatement statement) {
        Map<String, String> name = new HashMap<>();

        name.put("full", statement.getPackage() + "." + statement.getName());
        name.put("short", statement.getName());

        return name;
    }

}
