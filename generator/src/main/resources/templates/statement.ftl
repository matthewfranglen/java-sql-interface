<#if ! package.isDefault>
package ${package.name};
</#if>

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.matthew.sql.handler.AbstractStatement;

public final class ${name.short} extends AbstractStatement {

    <@compress single_line=true>private static final String statement = "${statement}";</@compress>

    public ${name.short}(NamedParameterJdbcTemplate template) {
        super(template);
    }

    protected String getStatement() {
        return statement;
    }
<#list [takes, returns] as argument>
<#if argument.isMultiple>

    public static final class ${argument.type} {

    }
</#if>
</#list>

}
