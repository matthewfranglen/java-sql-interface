<#import "argument-class.ftl" as argumentClass>
<#import "batch-update-methods.ftl" as batchUpdateMethods>
<#import "execute-methods.ftl" as executeMethods>
<#import "query-methods.ftl" as queryMethods>
<#import "update-methods.ftl" as updateMethods>
<#if ! name.inDefaultPackage>
package ${name.package};
</#if>

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import com.matthew.sql.handler.AbstractStatement;

public final class ${name.name} {

    <@compress single_line=true>private static final String statement = "${statement}";</@compress>

    private final org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate template;

    public ${name.name}(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    protected String getStatement() {
        return statement;
    }

<@batchUpdateMethods.methods takes returns></@batchUpdateMethods.methods>

<@executeMethods.methods takes returns></@executeMethods.methods>

<@queryMethods.methods takes returns></@queryMethods.methods>

<@updateMethods.methods takes returns></@updateMethods.methods>

<@argumentClass.define takes "Takes"></@argumentClass.define>

<@argumentClass.define returns "Returns"></@argumentClass.define>

}
