package com.spring.transactionhistorymanagementservice.repository;

import com.spring.transactionhistorymanagementservice.model.TransactionLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLogsRepository extends JpaRepository<TransactionLogs, Long> {
}
