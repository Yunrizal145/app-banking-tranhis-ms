package com.spring.transactionhistorymanagementservice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.spring.transactionhistorymanagementservice.constant.BankStatus;
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

@Entity
@Table(name = "bank_transaction", schema = "transaction_history_management_service")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankTransaction implements Serializable {
    private static final long serialVersionUID = 4883142276453441681L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    @Column(name = "bank_code")
    private String bankCode;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_name_alias")
    private String bankNameAlias;

    @Column(name = "bank_status")
    private BankStatus bankStatus;

    @Column(name = "is_deleted")
    private Boolean isDeleted;
}
