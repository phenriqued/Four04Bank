package com.github.Four04Bank.models.Tax;

import java.math.BigDecimal;

public class CreditTax implements Taxes{
    @Override
    public BigDecimal tax() {
        return new BigDecimal("0.1");
    }
}
