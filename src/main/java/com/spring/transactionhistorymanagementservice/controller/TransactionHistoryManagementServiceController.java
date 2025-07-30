package com.spring.transactionhistorymanagementservice.controller;

import com.spring.transactionhistorymanagementservice.dto.GetListTransactionHistoryRequest;
import com.spring.transactionhistorymanagementservice.dto.GetListTransactionHistoryResponse;
import com.spring.transactionhistorymanagementservice.dto.GetTransactionByTransactionIdRequest;
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

    @PostMapping(value = "/getlisttransactionhistory")
    public GetListTransactionHistoryResponse getListTransactionHistoryResponse(@RequestBody GetListTransactionHistoryRequest request){
        return transactionHistoryService.getListTransactionHistoryResponse(request);
    }

    @PostMapping(value = "/gettransactionhistorybytransactionid")
    public TransactionHistory getTransactionHistoryByTransactionId(@RequestBody GetTransactionByTransactionIdRequest request){
        return transactionHistoryService.getTransactionByTransactionId(request);
    }

    @PostMapping(value = "/updatestatustransaction")
    public void updateStatusTransaction(@RequestBody GetTransactionByTransactionIdRequest request){
        transactionHistoryService.updateStatus(request);
    }
}
