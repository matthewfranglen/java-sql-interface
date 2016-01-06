<#import "util-methods.ftl" as utilMethods>

<#macro methods takes returns>
<#if takes.defined && returns.defined>

<#elseif returns.defined>

<#elseif takes.defined>
    <#if takes.empty>

    public int update() {
        return template.update(getStatement(), java.util.Collections.emptyMap());
    }

    public int update(org.springframework.jdbc.support.KeyHolder generatedKeyHolder) {
        return template.update(getStatement(), new org.springframework.jdbc.core.namedparam.MapSqlParameterSource(java.util.Collections.emptyMap()), generatedKeyHolder);
    }

    public int update(org.springframework.jdbc.support.KeyHolder generatedKeyHolder, String[] keyColumnNames) {
        return template.update(getStatement(), new org.springframework.jdbc.core.namedparam.MapSqlParameterSource(java.util.Collections.emptyMap()), generatedKeyHolder, keyColumnNames);
    }

    <#else>

    public int update(${takes}) {
        <@utilMethods.argumentsToParameters takes></@utilMethods.argumentsToParameters>
        return template.update(getStatement(), parameters);
    }

    public int update(${takes}, org.springframework.jdbc.support.KeyHolder generatedKeyHolder) {
        <@utilMethods.argumentsToParameters takes></@utilMethods.argumentsToParameters>
        return template.update(getStatement(), new org.springframework.jdbc.core.namedparam.MapSqlParameterSource(parameters), generatedKeyHolder);
    }

    public int update(${takes}, org.springframework.jdbc.support.KeyHolder generatedKeyHolder, String[] keyColumnNames) {
        <@utilMethods.argumentsToParameters takes></@utilMethods.argumentsToParameters>
        return template.update(getStatement(), new org.springframework.jdbc.core.namedparam.MapSqlParameterSource(parameters), generatedKeyHolder, keyColumnNames);
    }

    </#if>
    <#if takes.multiple>

    public int update(${takes}) {
        <@utilMethods.instanceToParameters takes></@utilMethods.instanceToParameters>
        return template.update(getStatement(), parameters);
    }

    public int update(${takes}, org.springframework.jdbc.support.KeyHolder generatedKeyHolder) {
        <@utilMethods.instanceToParameters takes></@utilMethods.instanceToParameters>
        return template.update(getStatement(), new org.springframework.jdbc.core.namedparam.MapSqlParameterSource(parameters), generatedKeyHolder);
    }

    public int update(${takes}, org.springframework.jdbc.support.KeyHolder generatedKeyHolder, String[] keyColumnNames) {
        <@utilMethods.instanceToParameters takes></@utilMethods.instanceToParameters>
        return template.update(getStatement(), new org.springframework.jdbc.core.namedparam.MapSqlParameterSource(parameters), generatedKeyHolder, keyColumnNames);
    }

    </#if>
<#else>

    public int update(java.util.Map<String,?> paramMap) {
        return template.update(getStatement(), paramMap);
    }

    public int update(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource) {
        return template.update(getStatement(), paramSource);
    }

    public int update(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource, org.springframework.jdbc.support.KeyHolder generatedKeyHolder) {
        return template.update(getStatement(), paramSource, generatedKeyHolder);
    }

    public int update(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource, org.springframework.jdbc.support.KeyHolder generatedKeyHolder, String[] keyColumnNames) {
        return template.update(getStatement(), paramSource, generatedKeyHolder, keyColumnNames);
    }

</#if>
</#macro>
