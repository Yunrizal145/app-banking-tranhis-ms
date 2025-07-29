package com.spring.transactionhistorymanagementservice.service;

import com.spring.transactionhistorymanagementservice.model.TransactionHistory;
import com.spring.transactionhistorymanagementservice.repository.TransactionHistoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@Service
public class TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Transactional
    public void saveTransactionHistory(TransactionHistory transactionHistory) {
        log.info("Start save Transaction service");
        log.info("Start save Transaction service request : {}", transactionHistory);
        log.info("Compliance Transaction Information, " +
                        "ID: {}, " +
                        "User Profile ID: {}," +
                        "Account Number: {}," +
                        "Date {}," +
                        "Amount {} {}", transactionHistory.getTransactionId(),
                transactionHistory.getUserProfileId(), transactionHistory.getFromAccountNumber(),
                transactionHistory.getTransactionDate(), transactionHistory.getTransactionCurrency(),
                transactionHistory.getTransactionAmount());
        try {
            transactionHistoryRepository.save(transactionHistory);
        } catch (Exception e) {
            log.error("   Error getTransactionHistory", e);
            throw e;
        }
    }

}
