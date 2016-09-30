package com.altoros.blockchain.reinsurance;

import java.time.LocalDate;

/**
 * @author Nikita Gorbachevski
 */
public class PaymentSchedule {
    private final LocalDate date;
    private final long amount;

    public PaymentSchedule(LocalDate date, long amount) {
        this.date = date;
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getAmount() {
        return amount;
    }
}
