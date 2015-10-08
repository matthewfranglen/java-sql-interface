package com.matthew.sql.handler;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class AbstractStatementHandler {

    private final NamedParameterJdbcTemplate template;

    public AbstractStatementHandler(DataSource dataSource) {
        template = new NamedParameterJdbcTemplate(dataSource);
    }

    protected NamedParameterJdbcTemplate getTemplate() {
        return template;
    }

}
