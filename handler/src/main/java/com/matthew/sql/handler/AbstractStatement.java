package com.matthew.sql.handler;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class AbstractStatement {

    protected final NamedParameterJdbcTemplate template;

    public AbstractStatement(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

}
