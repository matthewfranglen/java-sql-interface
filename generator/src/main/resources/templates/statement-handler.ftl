package com.matthew.sql.generated;

import javax.sql.DataSource;
import com.matthew.sql.handler.AbstractStatementHandler;

public class SqlStatementHandler extends AbstractStatementHandler {

    public SqlStatementHandler(DataSource dataSource) {
        super(dataSource);
    }

    <#list statements as statement>

    public ${statement.name.full} make${statement.name.short}() {
        return new ${statement.name.full}(getTemplate());
    }

    </#list>

}
