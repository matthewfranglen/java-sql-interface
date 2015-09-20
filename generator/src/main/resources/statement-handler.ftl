package com.matthew.sql;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class SqlStatementHandler {

    private final DataSource dataSource;

    public SqlStatementHandler(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    private NamedParameterJdbcTemplate makeTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    <#list statements as statement>

    private final String statement${statement.shortName} = "${statement.statement}";

    public ${statement.fullName} execute${statement.shortName}() {

    }

    public ${statement.fullName} query${statement.shortName}() {

    }

    public ${statement.fullName} update${statement.shortName}() {

    }


    </#list>

}
