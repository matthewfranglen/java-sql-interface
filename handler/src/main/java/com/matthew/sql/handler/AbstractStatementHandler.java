package com.matthew.sql.handler;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class AbstractStatementHandler {

    protected final NamedParameterJdbcTemplate template;

    public AbstractStatementHandler(DataSource dataSource) {
        template = new NamedParameterJdbcTemplate(dataSource);
    }

}
