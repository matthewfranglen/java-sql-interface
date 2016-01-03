<#if ! name.inDefaultPackage>
package ${name.package};
</#if>

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.matthew.sql.handler.AbstractStatement;

public final class ${name.name} extends AbstractStatement {

    <@compress single_line=true>private static final String statement = "${statement}";</@compress>

    public ${name.name}(NamedParameterJdbcTemplate template) {
        super(template);
    }

    protected String getStatement() {
        return statement;
    }

<#if takes.defined && returns.defined>
    public List<${returns.multiple?then("Returns", returns.first.type.javaType)}> query(${takes.parameters}) {

    }

<#if takes.multiple>
    public List<${returns.multiple?then("Returns", returns.first.type.javaType)}> query(Takes parameters) {
        return query(<#list takes.iterator() as argument>parameters.${argument.getterName}()<#sep>, </#sep></#list>);
    }
</#if>
<#elseif takes.defined>

<#elseif returns.defined>

</#if>

<#if takes.multiple>
    public static final class Takes {
    <#list takes.iterator() as argument>
        private final ${argument.type.javaType} ${argument.name};
    </#list>

        public Takes(${takes.parameters}) {
        <#list takes.iterator() as argument>
            this.${argument.name} = ${argument.name};
        </#list>
        }

    <#list takes.iterator() as argument>
        public ${argument.type.javaType} ${argument.getterName}() {
            return ${argument.name};
        }
    </#list>
    }
</#if>

<#if returns.multiple>
    public static final class Returns {
    <#list returns.iterator() as argument>
        private final ${argument.type.javaType} ${argument.name};
    </#list>

        public Returns(${returns.parameters}) {
        <#list returns.iterator() as argument>
            this.${argument.name} = ${argument.name};
        </#list>
        }

    <#list returns.iterator() as argument>
        public ${argument.type.javaType} ${argument.getterName}() {
            return ${argument.name};
        }
    </#list>
    }
</#if>

}
