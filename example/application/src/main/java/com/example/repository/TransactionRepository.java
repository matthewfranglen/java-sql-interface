package com.example.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import statements.BuyOperation;
import statements.ReportQuery;
import statements.SellOperation;

import com.example.dao.Report;
import com.matthew.sql.generated.SqlStatementHandler;

@Repository
public class TransactionRepository {

    private static final Map<String, Object> emptyMap = Collections.emptyMap();

    @Autowired private DataSource dataSource;

    private BuyOperation buyOperation;
    private SellOperation sellOperation;
    private ReportQuery reportQuery;
    private RowMapper<Report> reportRowMapper;

    @PostConstruct
    public void initialize() {
        SqlStatementHandler statementHandler = new SqlStatementHandler(dataSource);

        buyOperation = statementHandler.makeBuyOperation();
        sellOperation = statementHandler.makeSellOperation();
        reportQuery = statementHandler.makeReportQuery();
        reportRowMapper = new ReportRowMapper();
    }

    public void doBuy() {
        buyOperation.update(emptyMap);
    }

    public void doSell() {
        sellOperation.update(emptyMap);
    }

    public Report getReport() {
        return reportQuery.query(reportRowMapper).get(0);
    }

    private static class ReportRowMapper implements RowMapper<Report> {

        public Report mapRow(ResultSet row, int rowNumber) throws SQLException {
            return new Report(row.getDouble("ratio"));
        }

    }

}
