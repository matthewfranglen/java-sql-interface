package com.matthew.sql.handler;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public abstract class AbstractStatementHandler {

    private final DataSource dataSource;

    public AbstractStatementHandler(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    protected NamedParameterJdbcTemplate makeTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

}
