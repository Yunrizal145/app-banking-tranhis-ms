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
public class TransactionDtoResponse implements Serializable {
    private static final long serialVersionUID = 90921845739863689L;

    private String transactionName;
    private String accountNumber;
    private String transactionAmount;
}
