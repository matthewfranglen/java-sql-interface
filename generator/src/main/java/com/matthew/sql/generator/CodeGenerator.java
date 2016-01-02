package com.matthew.sql.generator;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.matthew.sql.statement.Argument;
import com.matthew.sql.statement.SqlStatement;

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

        parameters.put("statements", makeStatementParametersList(statements));

        return parameters;
    }

    private Collection<Map<String, ?>> makeStatementParametersList(Collection<SqlStatement> statements) {
        Collection<Map<String, ?>> result = new ArrayList<>(statements.size());

        for (SqlStatement current : statements) {
            result.add(makeStatementParameters(current));
        }

        return result;
    }

    private Map<String, ?> makeStatementParameters(SqlStatement statement) {
        Map<String, Object> parameters = new HashMap<>();

        parameters.put("name", makeStatementName(statement));
        parameters.put("package", makeStatementPackage(statement));
        parameters.put("statement", statement.getStatement());
        parameters.put("takes", makeStatementTakes(statement));
        parameters.put("returns", makeStatementReturns(statement));

        return parameters;
    }

    private Map<String, ?> makeStatementName(SqlStatement statement) {
        Map<String, String> result = new HashMap<>();

        if (statement.inDefaultPackage()) {
            result.put("full", statement.getName());
        }
        else {
            result.put("full", statement.getPackage() + "." + statement.getName());
        }

        result.put("short", statement.getName());

        return result;
    }

    private Map<String, ?> makeStatementPackage(SqlStatement statement) {
        Map<String, Object> result = new HashMap<>();

        result.put("isDefault", statement.inDefaultPackage());
        result.put("name", statement.getPackage());

        return result;
    }

    private Map<String, ?> makeStatementTakes(SqlStatement statement) {
        Map<String, Object> result = makeStatementArguments(statement.getTakes());

        if ((Boolean)result.get("isMultiple")) {
            result.put("type", "Takes");
        }

        return result;
    }

    private Map<String, ?> makeStatementReturns(SqlStatement statement) {
        Map<String, Object> result = makeStatementArguments(statement.getReturns());

        if ((Boolean)result.get("isMultiple")) {
            result.put("type", "Returns");
        }

        return result;
    }

    private Map<String, Object> makeStatementArguments(Collection<Argument> arguments) {
        Map<String, Object> result = new HashMap<>();

        if (arguments == null) {
            result.put("isDefined", false);
            result.put("isEmpty", false);
            result.put("isSingle", false);
            result.put("isMultiple", false);

            return result;
        }

        result.put("isDefined", true);
        result.put("isEmpty", arguments.isEmpty());
        result.put("isSingle", arguments.size() == 1);
        result.put("isMultiple", arguments.size() > 1);

        if (arguments.size() == 1) {
            Argument argument = arguments.iterator().next();

            result.put("argument", argument);
            result.put("type", argument.getType().getJavaType());
        }
        else {
            result.put("arguments", arguments);
        }

        return result;
    }

}
