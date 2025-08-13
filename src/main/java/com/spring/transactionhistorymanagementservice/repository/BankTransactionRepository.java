package com.spring.transactionhistorymanagementservice.repository;

import com.spring.transactionhistorymanagementservice.constant.TransactionStatus;
import com.spring.transactionhistorymanagementservice.model.BankTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {

    Optional<BankTransaction> findBankTransactionByBankCodeAndIsDeleted(String bankCode, Boolean isDeleted);
    List<BankTransaction> findBankTransactionByIsDeleted(Boolean isDeleted);

    @Modifying
    @Transactional
    @Query("UPDATE BankTransaction bt SET bt.bankCode = :bankCode, bt.bankName = :bankName, bt.bankNameAlias = :bankNameAlias WHERE bt.bankCode = :bankCodeOld")
    List<BankTransaction> updateDataBank(@Param("bankCode") String bankCode, @Param("bankName") String bankName, @Param("bankNameAlias") String bankNameAlias, @Param("bankCodeOld") String bankCodeOld);
}
