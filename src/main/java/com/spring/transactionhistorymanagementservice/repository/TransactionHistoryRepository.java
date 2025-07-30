package com.spring.transactionhistorymanagementservice.repository;

import com.spring.transactionhistorymanagementservice.model.TransactionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Long> {

    List<TransactionHistory> findTransactionHistoryByUserProfileId(Long userProfileId);
    Optional<TransactionHistory> findTransactionHistoryByTransactionIdAndTransactionStatusIsNotNull(String transactionId);

    @Modifying
    @Transactional
    @Query("UPDATE TransactionHistory t SET t.transactionStatus = :status WHERE t.transactionId = :transactionId")
    int updateTransactionStatus(@Param("transactionId") String transactionId, @Param("status") String status);
}
