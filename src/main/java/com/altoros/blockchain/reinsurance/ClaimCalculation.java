package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;

/**
 * @author Nikita Gorbachevski
 */
public class ClaimCalculation {

    private final BigDecimal amount;
    private final Claim claim;

    public ClaimCalculation(Claim claim, BigDecimal amount) {
        this.claim = claim;
        this.amount = amount;
    }

    // special constructor for reducing
    public ClaimCalculation(ClaimCalculation cc1, ClaimCalculation cc2) {
        this.claim = cc1.getClaim();
        this.amount = cc1.getAmount().add(cc2.getAmount());
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Claim getClaim() {
        return claim;
    }
}
