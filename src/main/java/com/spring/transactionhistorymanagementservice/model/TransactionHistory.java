package com.spring.transactionhistorymanagementservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.transactionhistorymanagementservice.constant.AccountType;
import com.spring.transactionhistorymanagementservice.constant.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transaction_history", schema = "transaction_history_management_service")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionHistory implements Serializable {
    private static final long serialVersionUID = 1182906161697878380L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "user_profile_id")
    private Long userProfileId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "transaction_description")
    private String transactionDescription;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @Column(name = "transaction_status")
    private TransactionStatus transactionStatus;

    @Column(name = "transaction_curremncy")
    private String transactionCurrency;

    @Column(name = "transaction_amount")
    private BigDecimal transactionAmount;

    @Column(name = "transaction_fee")
    private BigDecimal transactionFee;

    @Column(name = "result_code")
    private String resultCode;

    @Column(name = "from_account_number")
    private String fromAccountNumber;

    @Column(name = "from_account_type")
    private AccountType fromAccountType;

    @Column(name = "to_account_number")
    private String toAccountNumber;

    @Column(name = "to_account_name")
    private String toAccountName;

    @Column(name = "additional_data")
    private String additionalData;
}
