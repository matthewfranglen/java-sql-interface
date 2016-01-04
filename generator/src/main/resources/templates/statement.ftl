<#import "argument-class.ftl" as argumentClass>
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
    public List<${returns.multiple?then("Returns", returns.first.type.javaType)}> query(${takes}) {

    }

<#if takes.multiple>
    public List<${returns.multiple?then("Returns", returns.first.type.javaType)}> query(Takes parameters) {
        return query(<#list takes.iterator() as argument>parameters.${argument.getterName}()<#sep>, </#sep></#list>);
    }
</#if>
<#elseif takes.defined>

<#elseif returns.defined>

</#if>

<@argumentClass.define takes "Takes"></@argumentClass.define>

<@argumentClass.define returns "Returns"></@argumentClass.define>

}
