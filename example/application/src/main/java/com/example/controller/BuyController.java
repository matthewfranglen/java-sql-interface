package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.repository.TransactionRepository;

@Controller
public class BuyController {

    @Autowired TransactionRepository transactionRepository;

    @RequestMapping(path="/buy", method=RequestMethod.POST)
    public @ResponseBody void doBuy() {
        transactionRepository.doBuy();
    }

}
