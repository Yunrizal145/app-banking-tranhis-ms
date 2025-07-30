package com.spring.transactionhistorymanagementservice.service;

import com.spring.transactionhistorymanagementservice.dto.GetListTransactionHistoryRequest;
import com.spring.transactionhistorymanagementservice.dto.GetListTransactionHistoryResponse;
import com.spring.transactionhistorymanagementservice.dto.GetTransactionByTransactionIdRequest;
import com.spring.transactionhistorymanagementservice.dto.TransactionDtoResponse;
import com.spring.transactionhistorymanagementservice.model.TransactionHistory;
import com.spring.transactionhistorymanagementservice.repository.TransactionHistoryRepository;
import com.spring.transactionhistorymanagementservice.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            transactionHistoryRepository.saveAndFlush(transactionHistory);
        } catch (Exception e) {
            log.error("   Error getTransactionHistory", e);
            throw e;
        }
    }

    public TransactionHistory getTransactionByTransactionId(GetTransactionByTransactionIdRequest request){
        log.info("start getTransactionHistoryByTransactionId");
        log.info("start getTransactionHistoryByTransactionId req : {}", request);
        TransactionHistory transactionHistory = new TransactionHistory();
        try {

            var getTransactionHistory = transactionHistoryRepository.findTransactionHistoryByTransactionId(request.getTransactionId());
            if (Objects.nonNull(getTransactionHistory)) {
                transactionHistory = getTransactionHistory.get();
            }

            return transactionHistory;
        } catch (Exception e) {
            throw new RuntimeException("Error when get transaction history : {}", e);
        }
    }

    public GetListTransactionHistoryResponse getListTransactionHistoryResponse(GetListTransactionHistoryRequest request) {
        log.info("start get List Transaction");
        log.info("start get List Transaction Request : {}", request);
        GetListTransactionHistoryResponse getListTransaction = new GetListTransactionHistoryResponse();
        List<TransactionDtoResponse> transactionDtoResponseList = new ArrayList<>();
        try {
            var getListTransactionHistory = transactionHistoryRepository.findTransactionHistoryByUserProfileId(request.getUserProfileId());
            getListTransactionHistory.forEach(transaction -> {
                transactionDtoResponseList.add(TransactionDtoResponse.builder()
                                .transactionName(transaction.getToAccountName())
                                .accountNumber(transaction.getToAccountNumber())
                                .transactionAmount(StringUtils.formatRupiah(transaction.getTransactionAmount()))
                        .build());
            });
            getListTransaction.setTransactionHistoryList(transactionDtoResponseList);
            return getListTransaction;
        } catch (Exception e) {
            throw new RuntimeException("Error when get list transaction history, {}", e);
        }
    }

}
