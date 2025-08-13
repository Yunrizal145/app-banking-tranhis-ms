package com.spring.transactionhistorymanagementservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDataBankResponse implements Serializable {
    private static final long serialVersionUID = 5718977127180355168L;

    private BankTransactionDto updateDataBank;
    private Boolean isSuccess;
}
