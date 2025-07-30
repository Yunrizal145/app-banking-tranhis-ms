package com.spring.transactionhistorymanagementservice.dto;

import com.spring.transactionhistorymanagementservice.constant.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTransactionByTransactionIdRequest implements Serializable {
    private static final long serialVersionUID = 2773419894011923385L;

    private String transactionId;
    private TransactionStatus status;
}
