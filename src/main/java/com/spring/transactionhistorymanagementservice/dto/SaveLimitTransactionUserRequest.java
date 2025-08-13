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
public class SaveLimitTransactionUserRequest implements Serializable {
    private static final long serialVersionUID = -6596031940596118357L;

    private Long userProfileId;
}
