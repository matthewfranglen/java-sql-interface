package com.matthew.sql.generated;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.matthew.sql.handler.AbstractStatementHandler;

public class SqlStatementHandler extends AbstractStatementHandler {

    public SqlStatementHandler(DataSource dataSource) {
        super(dataSource);
    }

    
    public statements.StatementByName makeStatementByName() {
        return new statements.StatementByName(getTemplate());
    }


}
