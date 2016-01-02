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
<#list [takes, returns] as interface>
<#if interface.isMultiple>

    public static final class ${interface.type} {
    <#list interface.arguments as argument>
        private final ${argument.javaType} ${argument.name};
    </#list>

        public ${interface.type}(<#list interface.arguments as argument>${argument.javaType} ${argument.name}<#sep>, </#sep></#list>) {
        <#list interface.arguments as argument>
            this.${argument.name} = ${argument.name};
        </#list>
        }

    <#list interface.arguments as argument>
        public ${argument.javaType} get${argument.capitalizedName}() {
            return ${argument.name};
        }
    </#list>
    }
</#if>
</#list>

}
