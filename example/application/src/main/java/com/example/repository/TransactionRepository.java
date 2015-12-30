package com.example.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import statements.BuyOperation;
import statements.ReportQuery;
import statements.SellOperation;

import com.example.dao.Report;
import com.matthew.sql.generated.SqlStatementHandler;

@Repository
public class TransactionRepository {

    @Autowired SqlStatementHandler statementHandler;

    private BuyOperation buyOperation;
    private SellOperation sellOperation;
    private ReportQuery reportQuery;

    @PostConstruct
    public void initialize() {
        buyOperation = statementHandler.makeBuyOperation();
        sellOperation = statementHandler.makeSellOperation();
        reportQuery = statementHandler.makeReportQuery();
    }

    public void doBuy() {
        buyOperation.update(Collections.emptyMap());
    }

    public void doSell() {
        sellOperation.update(Collections.emptyMap());
    }

    public Report getReport() {
        return reportQuery.query(this::mapRow).get(0);
    }

    private Report mapRow(ResultSet row, int rowNumber) {
        try {
            return new Report(row.getDouble("ratio"));
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
