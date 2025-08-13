package com.spring.transactionhistorymanagementservice.dto;

import com.spring.transactionhistorymanagementservice.model.LimitTransaction;
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
public class GetLimitTransactionResponse implements Serializable {
    private static final long serialVersionUID = 828158563947871778L;

    private List<LimitTransaction> limitTransactionDto;
}
