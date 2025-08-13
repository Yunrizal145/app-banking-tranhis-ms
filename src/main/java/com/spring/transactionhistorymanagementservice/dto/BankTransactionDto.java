package com.spring.transactionhistorymanagementservice.dto;

import com.spring.transactionhistorymanagementservice.constant.BankStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankTransactionDto implements Serializable {
    private static final long serialVersionUID = 6416909321991927919L;

    private String bankCode;
    private String bankName;
    private String bankNameAlias;
    private BankStatus bankStatus;
}
