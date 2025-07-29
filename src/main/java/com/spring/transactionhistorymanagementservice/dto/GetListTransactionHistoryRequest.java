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
public class GetListTransactionHistoryRequest implements Serializable {
    private static final long serialVersionUID = 5599602490110076375L;

    private Long userProfileId;
}
