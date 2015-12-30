package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.dao.Report;
import com.example.repository.TransactionRepository;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired TransactionRepository transactionRepository;

    @RequestMapping(method=RequestMethod.GET)
    public Report getReport() {
        return transactionRepository.getReport();
    }

}
