package statements;

public final class StatementByName {

    private static final String statement = "SELECT * FROM table LIMIT 1;";

    private final org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate template;

    public StatementByName(org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    protected String getStatement() {
        return statement;
    }


    public int[] batchUpdate(java.util.Map<String,?>[] batchValues) {
        return template.batchUpdate(getStatement(), batchValues);
    }

    public int[] batchUpdate(org.springframework.jdbc.core.namedparam.SqlParameterSource[] batchArgs) {
        return template.batchUpdate(getStatement(), batchArgs);
    }



    public <T> T execute(java.util.Map<String,?> paramMap, org.springframework.jdbc.core.PreparedStatementCallback<T> action) {
        return template.execute(getStatement(), paramMap, action);
    }

    public <T> T execute(org.springframework.jdbc.core.PreparedStatementCallback<T> action) {
        return template.execute(getStatement(), action);
    }

    public <T> T execute(org.springframework.jdbc.core.namedparam.SqlParameterSource paramSource, org.springframework.jdbc.core.PreparedStatementCallback<T> action) {
        return template.execute(getStatement(), paramSource, action);
    }



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




}
