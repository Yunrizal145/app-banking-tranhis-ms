package com.spring.transactionhistorymanagementservice.controller;

import com.spring.transactionhistorymanagementservice.model.TransactionHistory;
import com.spring.transactionhistorymanagementservice.service.TransactionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionHistoryManagementServiceController {

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @PostMapping(value = "/savetransactionhistory", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void savetransactionhistory(@RequestBody TransactionHistory transactionHistory) {
        transactionHistoryService.saveTransactionHistory(transactionHistory);
    }
}
