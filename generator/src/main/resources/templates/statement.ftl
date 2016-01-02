<#if ! package.isDefault>
package ${package.name};
</#if>

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.matthew.sql.handler.AbstractStatement;

public class ${name.short} extends AbstractStatement {

    <@compress single_line=true>private static final String statement = "${statement}";</@compress>

    public ${name.short}(NamedParameterJdbcTemplate template) {
        super(template);
    }

    protected String getStatement() {
        return statement;
    }

}
