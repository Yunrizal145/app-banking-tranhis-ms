package com.spring.transactionhistorymanagementservice.dto;

import com.spring.transactionhistorymanagementservice.constant.BankStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetBankTransactionResponse implements Serializable {
    private static final long serialVersionUID = 1751319184267917269L;

    private List<BankTransactionDto> bankTransactionDtos;
}
