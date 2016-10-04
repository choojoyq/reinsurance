package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Nikita Gorbachevski
 */
public class PaymentSchedule {

    private final LocalDate date;
    private final BigDecimal amount;

    public PaymentSchedule(LocalDate date, BigDecimal amount) {
        this.date = date;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
