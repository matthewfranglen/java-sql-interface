<#macro argumentsToParameters takes>
<#if takes.empty>
    Map<String, ?> parameters = java.util.Collections.emptyMap();
<#elseif takes.singular>
    Map<String, ?> parameters = java.util.Collections.singletonMap("${takes.first.name}", ${takes.first.name});
<#else>
    Map<String, ?> parameters = new java.util.HashMap<>();
    <#list takes.iterator() as argument>
    parameters.put("${argument.name}", ${argument.name});
    </#list>
</#if>
</#macro>

<#macro instanceToParameters takes>
    Map<String, ?> parameters = new java.util.HashMap<>();
    <#list takes.iterator() as argument>
    parameters.put("${argument.name}", takes.${argument.getterName}());
    </#list>
</#macro>

<#macro methods takes returns>
<#if takes.defined && returns.defined>

<#elseif returns.defined>

<#elseif takes.defined>
    <#if takes.empty>

    public <T> T query(ResultSetExtractor<T> rse) {
        return template.query(getStatement(), rse);
    }

    public void query(RowCallbackHandler rch) {
        template.query(getStatement(), rch);
    }

    public <T> List<T> query(RowMapper<T> rowMapper) {
        return template.query(getStatement(), rowMapper);
    }

    public <T> T queryForObject(Class<T> requiredType) {
        return template.queryForObject(getStatement(), java.util.Collections.emptyMap(), requiredType);
    }

    public <T> T queryForObject(RowMapper<T> rowMapper) {
        return template.queryForObject(getStatement(), java.util.Collections.emptyMap(), rowMapper);
    }

    public SqlRowSet queryForRowSet() {
        return template.queryForRowSet(getStatement(), java.util.Collections.emptyMap());
    }

    <#else>

    public <T> T query(${takes}, ResultSetExtractor<T> rse) {
        <@argumentsToParameters takes></@argumentsToParameters>
        return template.query(getStatement(), parameters, rse);
    }

    public void query(${takes}, RowCallbackHandler rch) {
        <@argumentsToParameters takes></@argumentsToParameters>
        template.query(getStatement(), parameters, rch);
    }

    public <T> List<T> query(${takes}, RowMapper<T> rowMapper) {
        <@argumentsToParameters takes></@argumentsToParameters>
        return template.query(getStatement(), parameters, rowMapper);
    }

    public <T> T queryForObject(${takes}, Class<T> requiredType) {
        <@argumentsToParameters takes></@argumentsToParameters>
        return template.queryForObject(getStatement(), parameters, requiredType);
    }

    public <T> T queryForObject(${takes}, RowMapper<T> rowMapper) {
        <@argumentsToParameters takes></@argumentsToParameters>
        return template.queryForObject(getStatement(), parameters, rowMapper);
    }

    public SqlRowSet queryForRowSet(${takes}) {
        <@argumentsToParameters takes></@argumentsToParameters>
        return template.queryForRowSet(getStatement(), parameters);
    }

    </#if>
    <#if takes.multiple>

    public <T> T query(Takes takes, ResultSetExtractor<T> rse) {
        <@instanceToParameters takes></@instanceToParameters>
        return template.query(getStatement(), parameters, rse);
    }

    public void query(Takes takes, RowCallbackHandler rch) {
        <@instanceToParameters takes></@instanceToParameters>
        template.query(getStatement(), parameters, rch);
    }

    public <T> List<T> query(Takes takes, RowMapper<T> rowMapper) {
        <@instanceToParameters takes></@instanceToParameters>
        return template.query(getStatement(), parameters, rowMapper);
    }

    public <T> T queryForObject(Takes takes, Class<T> requiredType) {
        <@instanceToParameters takes></@instanceToParameters>
        return template.queryForObject(getStatement(), parameters, requiredType);
    }

    public <T> T queryForObject(Takes takes, RowMapper<T> rowMapper) {
        <@instanceToParameters takes></@instanceToParameters>
        return template.queryForObject(getStatement(), parameters, rowMapper);
    }

    public SqlRowSet queryForRowSet(Takes takes) {
        <@instanceToParameters takes></@instanceToParameters>
        return template.queryForRowSet(getStatement(), parameters);
    }

    </#if>
<#else>

    public <T> T query(Map<String,?> paramMap, ResultSetExtractor<T> rse) {
        return template.query(getStatement(), paramMap, rse);
    }

    public void query(Map<String,?> paramMap, RowCallbackHandler rch) {
        template.query(getStatement(), paramMap, rch);
    }

    public <T> List<T> query(Map<String,?> paramMap, RowMapper<T> rowMapper) {
        return template.query(getStatement(), paramMap, rowMapper);
    }

    public <T> T query(ResultSetExtractor<T> rse) {
        return template.query(getStatement(), rse);
    }

    public void query(RowCallbackHandler rch) {
        template.query(getStatement(), rch);
    }

    public <T> List<T> query(RowMapper<T> rowMapper) {
        return template.query(getStatement(), rowMapper);
    }

    public <T> T query(SqlParameterSource paramSource, ResultSetExtractor<T> rse) {
        return template.query(getStatement(), paramSource, rse);
    }

    public void query(SqlParameterSource paramSource, RowCallbackHandler rch) {
        template.query(getStatement(), paramSource, rch);
    }

    public <T> List<T> query(SqlParameterSource paramSource, RowMapper<T> rowMapper) {
        return template.query(getStatement(), paramSource, rowMapper);
    }

    public List<Map<String,Object>> queryForList(Map<String,?> paramMap) {
        return template.queryForList(getStatement(), paramMap);
    }

    public <T> List<T> queryForList(Map<String,?> paramMap, Class<T> elementType) {
        return template.queryForList(getStatement(), paramMap, elementType);
    }

    public List<Map<String,Object>> queryForList(SqlParameterSource paramSource) {
        return template.queryForList(getStatement(), paramSource);
    }

    public <T> List<T> queryForList(SqlParameterSource paramSource, Class<T> elementType) {
        return template.queryForList(getStatement(), paramSource, elementType);
    }

    public Map<String,Object> queryForMap(Map<String,?> paramMap) {
        return template.queryForMap(getStatement(), paramMap);
    }

    public Map<String,Object> queryForMap(SqlParameterSource paramSource) {
        return template.queryForMap(getStatement(), paramSource);
    }

    public <T> T queryForObject(Map<String,?> paramMap, Class<T> requiredType) {
        return template.queryForObject(getStatement(), paramMap, requiredType);
    }

    public <T> T queryForObject(Map<String,?> paramMap, RowMapper<T> rowMapper) {
        return template.queryForObject(getStatement(), paramMap, rowMapper);
    }

    public <T> T queryForObject(SqlParameterSource paramSource, Class<T> requiredType) {
        return template.queryForObject(getStatement(), paramSource, requiredType);
    }

    public <T> T queryForObject(SqlParameterSource paramSource, RowMapper<T> rowMapper) {
        return template.queryForObject(getStatement(), paramSource, rowMapper);
    }

    public SqlRowSet queryForRowSet(Map<String,?> paramMap) {
        return template.queryForRowSet(getStatement(), paramMap);
    }

    public SqlRowSet queryForRowSet(SqlParameterSource paramSource) {
        return template.queryForRowSet(getStatement(), paramSource);
    }

</#if>
</#macro>
