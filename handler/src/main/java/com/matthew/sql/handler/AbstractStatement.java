package com.matthew.sql.handler;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;

public abstract class AbstractStatement {

    protected final NamedParameterJdbcTemplate template;

    public AbstractStatement(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    protected abstract String getStatement();

    public int[] batchUpdate(Map<String,?>[] batchValues) {
        return template.batchUpdate(getStatement(), batchValues);
    }

    public int[] batchUpdate(SqlParameterSource[] batchArgs) {
        return template.batchUpdate(getStatement(), batchArgs);
    }

    public <T> T execute(Map<String,?> paramMap, PreparedStatementCallback<T> action) {
        return template.execute(getStatement(), paramMap, action);
    }

    public <T> T execute(PreparedStatementCallback<T> action) {
        return template.execute(getStatement(), action);
    }

    public <T> T execute(SqlParameterSource paramSource, PreparedStatementCallback<T> action) {
        return template.execute(getStatement(), paramSource, action);
    }

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

    public int update(Map<String,?> paramMap) {
        return template.update(getStatement(), paramMap);
    }

    public int update(SqlParameterSource paramSource) {
        return template.update(getStatement(), paramSource);
    }

    public int update(SqlParameterSource paramSource, KeyHolder generatedKeyHolder) {
        return template.update(getStatement(), paramSource, generatedKeyHolder);
    }

    public int update(SqlParameterSource paramSource, KeyHolder generatedKeyHolder, String[] keyColumnNames) {
        return template.update(getStatement(), paramSource, generatedKeyHolder, keyColumnNames);
    }

}