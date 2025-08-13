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
public class SaveDataBankRequest implements Serializable {
    private static final long serialVersionUID = 2313592334338740107L;

    private String bankCode;
    private String bankName;
    private String bankNameAlias;
}
