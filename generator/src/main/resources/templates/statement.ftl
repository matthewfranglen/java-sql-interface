<#if hasPackage>
package ${package};
</#if>

import com.matthew.sql.handler.AbstractStatement;

public class ${name.short} extends AbstractStatement {

    private static final String statement = "${statement}";

    public ${name.short}(NamedParameterJdbcTemplate template) {
        super(template);
    }

    protected String getStatement() {
        return statement;
    }

}
