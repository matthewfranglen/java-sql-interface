package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.Report;
import com.example.repository.TransactionRepository;

@Controller
public class ReportController {

    @Autowired TransactionRepository transactionRepository;

    @RequestMapping(path="/report", method=RequestMethod.GET)
    public @ResponseBody Report getReport() {
        return transactionRepository.getReport();
    }

}
