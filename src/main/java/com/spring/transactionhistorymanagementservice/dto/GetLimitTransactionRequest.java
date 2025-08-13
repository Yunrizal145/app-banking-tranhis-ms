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
public class GetLimitTransactionRequest implements Serializable {
    private static final long serialVersionUID = 3170052294745885234L;

    private Long userProfileId;
}
