<#import "util-methods.ftl" as utilMethods>

<#macro methods takes returns>
<#if takes.defined && returns.defined>

<#elseif returns.defined>

<#elseif takes.defined>
    <#if takes.empty>

    public <T> T execute(org.springframework.jdbc.core.PreparedStatementCallback<T> action) {
        return template.execute(getStatement(), action);
    }

    <#else>

    public <T> T execute(${takes}, org.springframework.jdbc.core.PreparedStatementCallback<T> action) {
        <@utilMethods.argumentsToParameters takes></@utilMethods.argumentsToParameters>
        return template.execute(getStatement(), parameters, action);
    }

    </#if>
    <#if takes.multiple>

    public <T> T execute(Takes takes, org.springframework.jdbc.core.PreparedStatementCallback<T> action) {
        <@utilMethods.instanceToParameters takes></@utilMethods.instanceToParameters>
        return template.execute(getStatement(), parameters, action);
    }

    </#if>
<#else>

    public <T> T execute(java.util.Map<String,?> paramMap, org.springframework.jdbc.core.PreparedStatementCallback<T> action) {
        return template.execute(getStatement(), paramMap, action);
    }

    public <T> T execute(org.springframework.jdbc.core.PreparedStatementCallback<T> action) {
        return template.execute(getStatement(), action);
    }

    public <T> T execute(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource, org.springframework.jdbc.core.PreparedStatementCallback<T> action) {
        return template.execute(getStatement(), paramSource, action);
    }

</#if>
</#macro>
