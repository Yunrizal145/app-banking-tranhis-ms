package com.spring.transactionhistorymanagementservice.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class StringUtils {

    public static String formatRupiah(BigDecimal amount) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        DecimalFormat formatter = new DecimalFormat("Rp #,##0.00", symbols);
        return formatter.format(amount);
    }
}
