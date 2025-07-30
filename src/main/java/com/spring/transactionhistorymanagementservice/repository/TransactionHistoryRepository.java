package com.spring.transactionhistorymanagementservice.repository;

import com.spring.transactionhistorymanagementservice.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    List<TransactionHistory> findTransactionHistoryByUserProfileId(Long userProfileId);
    Optional<TransactionHistory> findTransactionHistoryByTransactionId(String transactionId);
}
