<#if hasPackage>
package ${package};
</#if>

import com.matthew.sql.handler.AbstractStatement;

public class ${shortName} extends AbstractStatement {

    private static final String statement = "${statement.statement}";

    public ${shortName}(NamedParameterJdbcTemplate template) {
        super(template);
    }

    protected String getStatement() {
        return statement;
    }

}
