<#import "util-methods.ftl" as utilMethods>

<#macro methods takes returns>
<#if takes.defined && returns.defined>

<#elseif returns.defined>

<#elseif takes.defined>
    <#if takes.empty>

    public <T> T query(org.springframework.jdbc.core.ResultSetExtractor<T> rse) {
        return template.query(getStatement(), rse);
    }

    public void query(org.springframework.jdbc.core.RowCallbackHandler rch) {
        template.query(getStatement(), rch);
    }

    public <T> java.util.List<T> query(org.springframework.jdbc.core.RowMapper<T> rowMapper) {
        return template.query(getStatement(), rowMapper);
    }

    public <T> T queryForObject(Class<T> requiredType) {
        return template.queryForObject(getStatement(), java.util.Collections.emptyMap(), requiredType);
    }

    public <T> T queryForObject(org.springframework.jdbc.core.RowMapper<T> rowMapper) {
        return template.queryForObject(getStatement(), java.util.Collections.emptyMap(), rowMapper);
    }

    public org.springframework.jdbc.support.rowset.SqlRowSet queryForRowSet() {
        return template.queryForRowSet(getStatement(), java.util.Collections.emptyMap());
    }

    <#else>

    public <T> T query(${takes}, org.springframework.jdbc.core.ResultSetExtractor<T> rse) {
        <@utilMethods.argumentsToParameters takes></@utilMethods.argumentsToParameters>
        return template.query(getStatement(), parameters, rse);
    }

    public void query(${takes}, org.springframework.jdbc.core.RowCallbackHandler rch) {
        <@utilMethods.argumentsToParameters takes></@utilMethods.argumentsToParameters>
        template.query(getStatement(), parameters, rch);
    }

    public <T> java.util.List<T> query(${takes}, org.springframework.jdbc.core.RowMapper<T> rowMapper) {
        <@utilMethods.argumentsToParameters takes></@utilMethods.argumentsToParameters>
        return template.query(getStatement(), parameters, rowMapper);
    }

    public <T> T queryForObject(${takes}, Class<T> requiredType) {
        <@utilMethods.argumentsToParameters takes></@utilMethods.argumentsToParameters>
        return template.queryForObject(getStatement(), parameters, requiredType);
    }

    public <T> T queryForObject(${takes}, org.springframework.jdbc.core.RowMapper<T> rowMapper) {
        <@utilMethods.argumentsToParameters takes></@utilMethods.argumentsToParameters>
        return template.queryForObject(getStatement(), parameters, rowMapper);
    }

    public org.springframework.jdbc.support.rowset.SqlRowSet queryForRowSet(${takes}) {
        <@utilMethods.argumentsToParameters takes></@utilMethods.argumentsToParameters>
        return template.queryForRowSet(getStatement(), parameters);
    }

    </#if>
    <#if takes.multiple>

    public <T> T query(Takes takes, org.springframework.jdbc.core.ResultSetExtractor<T> rse) {
        <@utilMethods.instanceToParameters takes></@utilMethods.instanceToParameters>
        return template.query(getStatement(), parameters, rse);
    }

    public void query(Takes takes, org.springframework.jdbc.core.RowCallbackHandler rch) {
        <@utilMethods.instanceToParameters takes></@utilMethods.instanceToParameters>
        template.query(getStatement(), parameters, rch);
    }

    public <T> java.util.List<T> query(Takes takes, org.springframework.jdbc.core.RowMapper<T> rowMapper) {
        <@utilMethods.instanceToParameters takes></@utilMethods.instanceToParameters>
        return template.query(getStatement(), parameters, rowMapper);
    }

    public <T> T queryForObject(Takes takes, Class<T> requiredType) {
        <@utilMethods.instanceToParameters takes></@utilMethods.instanceToParameters>
        return template.queryForObject(getStatement(), parameters, requiredType);
    }

    public <T> T queryForObject(Takes takes, org.springframework.jdbc.core.RowMapper<T> rowMapper) {
        <@utilMethods.instanceToParameters takes></@utilMethods.instanceToParameters>
        return template.queryForObject(getStatement(), parameters, rowMapper);
    }

    public org.springframework.jdbc.support.rowset.SqlRowSet queryForRowSet(Takes takes) {
        <@utilMethods.instanceToParameters takes></@utilMethods.instanceToParameters>
        return template.queryForRowSet(getStatement(), parameters);
    }

    </#if>
<#else>

    public <T> T query(java.util.Map<String,?> paramMap, org.springframework.jdbc.core.ResultSetExtractor<T> rse) {
        return template.query(getStatement(), paramMap, rse);
    }

    public void query(java.util.Map<String,?> paramMap, org.springframework.jdbc.core.RowCallbackHandler rch) {
        template.query(getStatement(), paramMap, rch);
    }

    public <T> java.util.List<T> query(java.util.Map<String,?> paramMap, org.springframework.jdbc.core.RowMapper<T> rowMapper) {
        return template.query(getStatement(), paramMap, rowMapper);
    }

    public <T> T query(org.springframework.jdbc.core.ResultSetExtractor<T> rse) {
        return template.query(getStatement(), rse);
    }

    public void query(org.springframework.jdbc.core.RowCallbackHandler rch) {
        template.query(getStatement(), rch);
    }

    public <T> java.util.List<T> query(org.springframework.jdbc.core.RowMapper<T> rowMapper) {
        return template.query(getStatement(), rowMapper);
    }

    public <T> T query(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource, org.springframework.jdbc.core.ResultSetExtractor<T> rse) {
        return template.query(getStatement(), paramSource, rse);
    }

    public void query(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource, org.springframework.jdbc.core.RowCallbackHandler rch) {
        template.query(getStatement(), paramSource, rch);
    }

    public <T> java.util.List<T> query(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource, org.springframework.jdbc.core.RowMapper<T> rowMapper) {
        return template.query(getStatement(), paramSource, rowMapper);
    }

    public java.util.List<java.util.Map<String,Object>> queryForList(java.util.Map<String,?> paramMap) {
        return template.queryForList(getStatement(), paramMap);
    }

    public <T> java.util.List<T> queryForList(java.util.Map<String,?> paramMap, Class<T> elementType) {
        return template.queryForList(getStatement(), paramMap, elementType);
    }

    public java.util.List<java.util.Map<String,Object>> queryForList(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource) {
        return template.queryForList(getStatement(), paramSource);
    }

    public <T> java.util.List<T> queryForList(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource, Class<T> elementType) {
        return template.queryForList(getStatement(), paramSource, elementType);
    }

    public java.util.Map<String,Object> queryForMap(java.util.Map<String,?> paramMap) {
        return template.queryForMap(getStatement(), paramMap);
    }

    public java.util.Map<String,Object> queryForMap(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource) {
        return template.queryForMap(getStatement(), paramSource);
    }

    public <T> T queryForObject(java.util.Map<String,?> paramMap, Class<T> requiredType) {
        return template.queryForObject(getStatement(), paramMap, requiredType);
    }

    public <T> T queryForObject(java.util.Map<String,?> paramMap, org.springframework.jdbc.core.RowMapper<T> rowMapper) {
        return template.queryForObject(getStatement(), paramMap, rowMapper);
    }

    public <T> T queryForObject(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource, Class<T> requiredType) {
        return template.queryForObject(getStatement(), paramSource, requiredType);
    }

    public <T> T queryForObject(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource, org.springframework.jdbc.core.RowMapper<T> rowMapper) {
        return template.queryForObject(getStatement(), paramSource, rowMapper);
    }

    public org.springframework.jdbc.support.rowset.SqlRowSet queryForRowSet(java.util.Map<String,?> paramMap) {
        return template.queryForRowSet(getStatement(), paramMap);
    }

    public org.springframework.jdbc.support.rowset.SqlRowSet queryForRowSet(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource) {
        return template.queryForRowSet(getStatement(), paramSource);
    }

</#if>
</#macro>
