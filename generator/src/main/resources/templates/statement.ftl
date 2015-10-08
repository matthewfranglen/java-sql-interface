<#if hasPackage>
package ${package};
</#if>

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
