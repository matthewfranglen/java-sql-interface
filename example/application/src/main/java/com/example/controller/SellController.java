package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.repository.TransactionRepository;

@Controller
@RequestMapping("/sell")
public class SellController {

    @Autowired TransactionRepository transactionRepository;

    @RequestMapping(method=RequestMethod.POST)
    public void doSell() {
        transactionRepository.doSell();
    }

}
