package com.spring.transactionhistorymanagementservice.repository;

import com.spring.transactionhistorymanagementservice.model.LimitTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LimitTransactionRepository extends JpaRepository<LimitTransaction, Long> {

    List<LimitTransaction> findLimitTransactionByUserProfileId(Long userProfileId);
}
