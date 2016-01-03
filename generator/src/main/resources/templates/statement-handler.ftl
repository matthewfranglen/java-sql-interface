package com.matthew.sql.generated;

import javax.sql.DataSource;
import com.matthew.sql.handler.AbstractStatementHandler;

public class SqlStatementHandler extends AbstractStatementHandler {

    public SqlStatementHandler(DataSource dataSource) {
        super(dataSource);
    }

    <#list statements as statement>

    public ${statement.name.fullName} make${statement.name.name}() {
        return new ${statement.name.fullName}(getTemplate());
    }

    </#list>

}
