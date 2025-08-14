package com.spring.transactionhistorymanagementservice.service;

import com.spring.transactionhistorymanagementservice.constant.BankStatus;
import com.spring.transactionhistorymanagementservice.constant.TransactionCategory;
import com.spring.transactionhistorymanagementservice.constant.TransactionStatus;
import com.spring.transactionhistorymanagementservice.dto.BankTransactionDto;
import com.spring.transactionhistorymanagementservice.dto.GetBankTransactionResponse;
import com.spring.transactionhistorymanagementservice.dto.GetLimitTransactionRequest;
import com.spring.transactionhistorymanagementservice.dto.GetLimitTransactionResponse;
import com.spring.transactionhistorymanagementservice.dto.GetListTransactionHistoryRequest;
import com.spring.transactionhistorymanagementservice.dto.GetListTransactionHistoryResponse;
import com.spring.transactionhistorymanagementservice.dto.GetTransactionByTransactionIdRequest;
import com.spring.transactionhistorymanagementservice.dto.SaveDataBankRequest;
import com.spring.transactionhistorymanagementservice.dto.SaveLimitTransactionUserRequest;
import com.spring.transactionhistorymanagementservice.dto.TransactionDtoResponse;
import com.spring.transactionhistorymanagementservice.dto.UpdateDataBankRequest;
import com.spring.transactionhistorymanagementservice.dto.UpdateDataBankResponse;
import com.spring.transactionhistorymanagementservice.model.BankTransaction;
import com.spring.transactionhistorymanagementservice.model.LimitTransaction;
import com.spring.transactionhistorymanagementservice.model.TransactionHistory;
import com.spring.transactionhistorymanagementservice.repository.BankTransactionRepository;
import com.spring.transactionhistorymanagementservice.repository.LimitTransactionRepository;
import com.spring.transactionhistorymanagementservice.repository.TransactionHistoryRepository;
import com.spring.transactionhistorymanagementservice.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class TransactionHistoryService {

    @Autowired
    private TransactionHistoryRepository transactionHistoryRepository;

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @Autowired
    private LimitTransactionRepository limitTransactionRepository;

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
            transactionHistory.setTransactionCategory(null);
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

            var getTransactionHistory = transactionHistoryRepository.findTransactionHistoryByTransactionIdAndTransactionStatusIsNotNull(request.getTransactionId());
            log.info("data transaction : {}", getTransactionHistory);
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
            var getListTransactionHistory = transactionHistoryRepository.findTransactionHistoryByUserProfileIdOrderByIdDesc(request.getUserProfileId());
            getListTransactionHistory.forEach(transaction -> {
                if (TransactionStatus.SUCCESS.equals(transaction.getTransactionStatus())) {
                    transactionDtoResponseList.add(TransactionDtoResponse.builder()
                                    .transactionName(transaction.getToAccountName())
                                    .accountNumber(transaction.getToAccountNumber())
                                    .transactionAmount(StringUtils.formatRupiah(transaction.getTransactionAmount()))
                            .build());
                }
            });
            getListTransaction.setTransactionHistoryList(transactionDtoResponseList);
            return getListTransaction;
        } catch (Exception e) {
            throw new RuntimeException("Error when get list transaction history, {}", e);
        }
    }

    public void updateStatus(GetTransactionByTransactionIdRequest request) {
        int rowsUpdated = transactionHistoryRepository.updateTransactionStatus(request.getTransactionId(), request.getStatus());
        if (rowsUpdated > 0) {
            log.info("Update berhasil");
        } else {
            log.info("Data tidak ditemukan atau tidak diubah");
        }
    }

    public boolean saveDataBank(SaveDataBankRequest request) {
        log.info("start save data bank");
        log.info("start save data bank req : {}", request);
        try {
            BankTransaction bankTransaction = new BankTransaction();
            bankTransaction.setBankCode(request.getBankCode());
            bankTransaction.setBankName(request.getBankName());
            bankTransaction.setBankNameAlias(request.getBankNameAlias());
            bankTransaction.setBankStatus(BankStatus.AKTIF);
            bankTransaction.setIsDeleted(false);

            bankTransactionRepository.save(bankTransaction);
            log.info("save data bank done");
            return true;
        } catch (Exception e) {
            log.error("Error when save data bank: {}", e.getMessage(), e);
            return false;
        }
    }

    public GetBankTransactionResponse getBankTransaction() {
        log.info("start getDataBankTransaction");
        List<BankTransaction> bankTransactionList = new ArrayList<>();
        List<BankTransactionDto> bankTransactionDtos = new ArrayList<>();
        try {
            bankTransactionList = bankTransactionRepository.findBankTransactionByIsDeleted(false);

            bankTransactionList.forEach(bankTransaction -> {
                BankTransactionDto bankTransactionDto1 = new BankTransactionDto();
                bankTransactionDto1.setBankCode(bankTransaction.getBankCode());
                bankTransactionDto1.setBankName(bankTransaction.getBankName());
                bankTransactionDto1.setBankNameAlias(bankTransaction.getBankNameAlias());
                bankTransactionDto1.setBankStatus(bankTransaction.getBankStatus());

                bankTransactionDtos.add(bankTransactionDto1);
            });

            return GetBankTransactionResponse.builder()
                    .bankTransactionDtos(bankTransactionDtos)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("error when get data bank transaction");
        }
    }

    public UpdateDataBankResponse updateDataBank(UpdateDataBankRequest request) {
        log.info("updateDataBank");
        log.info("updateDataBank req : {}", request);
        BankTransactionDto bankTransactionDto = new BankTransactionDto();
        BankTransaction bankTransaction = null;
        Boolean isSuccess = Boolean.FALSE;
        try {
            var getDataBank = bankTransactionRepository.findBankTransactionByBankCodeAndIsDeleted(request.getBankCode(), false);
            if (getDataBank.isPresent()) {
                if (!request.getDeletedData()) {
                    log.info("start update data bank");
                    bankTransaction = getDataBank.get();
                    bankTransactionDto.setBankCode(request.getBankCodeNew());
                    bankTransactionDto.setBankName(request.getBankNameNew());
                    bankTransactionDto.setBankNameAlias(request.getBankNameAliasNew());

                    bankTransaction.setBankCode(bankTransactionDto.getBankCode());
                    bankTransaction.setBankName(bankTransactionDto.getBankName());
                    bankTransaction.setBankNameAlias(bankTransactionDto.getBankNameAlias());
                    bankTransactionRepository.save(bankTransaction);
                    log.info("update data bank success");
                    isSuccess = Boolean.TRUE;

                    return UpdateDataBankResponse.builder()
                            .isSuccess(isSuccess)
                            .updateDataBank(bankTransactionDto)
                            .build();
                } else {
                    log.info("start delete data bank");
                    bankTransaction = getDataBank.get();
                    bankTransaction.setIsDeleted(true);
                    bankTransactionRepository.save(bankTransaction);
                    log.info("delete data bank success");
                }
            }
            return UpdateDataBankResponse.builder()
                    .isSuccess(isSuccess)
                    .updateDataBank(bankTransactionDto)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("error when update data bank");
        }
    }

    public void saveLimitTransactionUser(SaveLimitTransactionUserRequest request) {
        log.info("start save limit transaction user");
        log.info("start save limit transaction user req : {}", request);
        try {
            var getData = limitTransactionRepository.findLimitTransactionByUserProfileId(request.getUserProfileId());
            if (getData.isEmpty()) {
                // Save Limit Transaction Antar Bank for User
                log.info("start save limit transaction antar bank for user : {}", request.getUserProfileId());
                LimitTransaction limitTransactionAntarBank = new LimitTransaction();
                limitTransactionAntarBank.setUserProfileId(request.getUserProfileId());
                limitTransactionAntarBank.setLimitFee(5);
                limitTransactionAntarBank.setMaxAmountTransaction(BigDecimal.valueOf(1000000));
                limitTransactionAntarBank.setMaxAmountTransactionOfDay(BigDecimal.valueOf(500000));
                limitTransactionAntarBank.setMaxTransactionTotal(20);
                limitTransactionAntarBank.setMinAmountTransaction(BigDecimal.valueOf(10000));
                limitTransactionAntarBank.setTransactionCategory(TransactionCategory.ANTAR_BANK);
                limitTransactionAntarBank.setTransactionFee(BigDecimal.valueOf(2500));
                limitTransactionRepository.save(limitTransactionAntarBank);
                log.info("save limit transaction antar bank for user : {} Success", request.getUserProfileId());

                // Save Limit Transaction Sesama Bank for User
                log.info("start save limit transaction sesama bank for user : {}", request.getUserProfileId());
                LimitTransaction limitTransactionSesamaBank = new LimitTransaction();
                limitTransactionSesamaBank.setUserProfileId(request.getUserProfileId());
                limitTransactionSesamaBank.setLimitFee(0);
                limitTransactionSesamaBank.setMaxAmountTransaction(BigDecimal.valueOf(1000000));
                limitTransactionSesamaBank.setMaxAmountTransactionOfDay(BigDecimal.valueOf(500000));
                limitTransactionSesamaBank.setMaxTransactionTotal(20);
                limitTransactionSesamaBank.setMinAmountTransaction(BigDecimal.valueOf(1));
                limitTransactionSesamaBank.setTransactionCategory(TransactionCategory.SESAMA_BANK);
                limitTransactionSesamaBank.setTransactionFee(BigDecimal.ZERO);
                limitTransactionRepository.save(limitTransactionSesamaBank);
                log.info("start save limit transaction sesama bank for user : {} Success", request.getUserProfileId());
            } else {
                log.error("save data failed, user have data limit transaction");
                throw new RuntimeException("error when save data limit transaction");
            }
        } catch (Exception e) {
            throw new RuntimeException("error when save data limit transaction");
        }
    }

    public GetLimitTransactionResponse getLimitTransaction(GetLimitTransactionRequest request) {
        log.info("start getLimitTransaction");
        log.info("start getLimitTransaction req : {}", request);
        try {
            var limitTransaction = limitTransactionRepository.findLimitTransactionByUserProfileId(request.getUserProfileId());
            if (limitTransaction.isEmpty()) {
                throw new RuntimeException("error when get limit transaction");
            }
            return GetLimitTransactionResponse.builder()
                    .limitTransactionDto(limitTransaction)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("error when get limit transaction");
        }
    }
}
