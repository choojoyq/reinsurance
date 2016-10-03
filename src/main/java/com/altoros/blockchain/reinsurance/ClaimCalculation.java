package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;

/**
 * @author Nikita Gorbachevski
 */
public class ClaimCalculation {

    private BigDecimal paid;
    private BigDecimal reserved;
    private BigDecimal incurred;
    private final Claim claim;

    public ClaimCalculation(Claim claim) {
        this.claim = claim;
        this.paid = claim.getPaid();
        this.reserved = claim.getReserved();
        this.incurred = claim.getIncurred();
    }

    // special constructor for reducing
    public ClaimCalculation(ClaimCalculation cc1, ClaimCalculation cc2) {
        if (cc1.getClaim() != cc2.getClaim()) {
            throw new RuntimeException("claim calculations have to belong to the same claim");
        }
        this.claim = cc1.getClaim();
        this.paid = cc1.getPaid().add(cc2.getPaid());
        this.incurred = cc1.getIncurred().add(cc2.getIncurred());
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public BigDecimal getReserved() {
        return reserved;
    }

    public void setReserved(BigDecimal reserved) {
        this.reserved = reserved;
    }

    public BigDecimal getIncurred() {
        return incurred;
    }

    public void setIncurred(BigDecimal incurred) {
        this.incurred = incurred;
    }

    public Claim getClaim() {
        return claim;
    }
}
