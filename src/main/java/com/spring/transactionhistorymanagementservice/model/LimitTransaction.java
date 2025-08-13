package com.spring.transactionhistorymanagementservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.transactionhistorymanagementservice.constant.TransactionCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "limit_transaction", schema = "transaction_history_management_service")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LimitTransaction implements Serializable {
    private static final long serialVersionUID = 8503117934233705963L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "user_profile_id")
    private Long userProfileId;

    @Column(name = "transaction_category")
    @Enumerated(EnumType.STRING)
    private TransactionCategory transactionCategory;

    @Column(name = "min_amount_transaction")
    private BigDecimal minAmountTransaction;

    @Column(name = "max_amount_transaction")
    private BigDecimal maxAmountTransaction;

    @Column(name = "max_amount_transaction_of_day")
    private BigDecimal maxAmountTransactionOfDay;

    @Column(name = "max_transaction_total")
    private Integer maxTransactionTotal;

    @Column(name = "transaction_fee")
    private BigDecimal transactionFee;

    @Column(name = "limit_fee")
    private Integer limitFee;
}
