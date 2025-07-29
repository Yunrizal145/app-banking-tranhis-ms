package com.spring.transactionhistorymanagementservice.dto;

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
public class GetListTransactionHistoryResponse implements Serializable {
    private static final long serialVersionUID = -1238057411466215918L;

    private List<TransactionDtoResponse> transactionHistoryList;
}
