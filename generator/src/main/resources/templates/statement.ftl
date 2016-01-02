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

<#if takes.isDefined && returns.isDefined>
    public List<${returns.type}> query(${takes.asParameters}) {

    }

<#if takes.isMultiple>
    public List<${returns.type}> query(${takes.type} parameters) {
        return query(<#list takes.arguments as argument>parameters.get${argument.capitalizedName}()<#sep>, </#sep></#list>);
    }
</#if>
<#elseif takes.isDefined>

<#elseif returns.isDefined>

</#if>

<#list [takes, returns] as interface>
<#if interface.isMultiple>

    public static final class ${interface.type} {
    <#list interface.arguments as argument>
        private final ${argument.type.javaType} ${argument.name};
    </#list>

        public ${interface.type}(${interface.asParameters}) {
        <#list interface.arguments as argument>
            this.${argument.name} = ${argument.name};
        </#list>
        }

    <#list interface.arguments as argument>
        public ${argument.type.javaType} get${argument.capitalizedName}() {
            return ${argument.name};
        }
    </#list>
    }
</#if>
</#list>

}
