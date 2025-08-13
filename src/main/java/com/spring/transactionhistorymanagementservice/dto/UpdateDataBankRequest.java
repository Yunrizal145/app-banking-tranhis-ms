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
public class UpdateDataBankRequest implements Serializable {
    private static final long serialVersionUID = 3127959483307536418L;

    private String bankCode;
    private String bankName;
    private String bankNameAlias;
    private String bankCodeNew;
    private String bankNameNew;
    private String bankNameAliasNew;
    private Boolean deletedData;
}
