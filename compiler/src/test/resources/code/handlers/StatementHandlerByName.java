package com.matthew.sql.generated;

import javax.sql.DataSource;
import com.matthew.sql.handler.AbstractStatementHandler;

public class SqlStatementHandler extends AbstractStatementHandler {

    public SqlStatementHandler(DataSource dataSource) {
        super(dataSource);
    }

    
    public statements.StatementByName makeStatementByName() {
        return new statements.StatementByName(getTemplate());
    }


}
