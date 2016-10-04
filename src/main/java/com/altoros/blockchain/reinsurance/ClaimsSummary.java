package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Nikita Gorbachevski
 */
public class ClaimsSummary {

    private final BigDecimal paid;
    private final BigDecimal incurred;
    private final List<Claim> claims;

    public ClaimsSummary(BigDecimal paid, BigDecimal incurred, List<Claim> claims) {
        this.paid = paid;
        this.incurred = incurred;
        this.claims = claims;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public BigDecimal getIncurred() {
        return incurred;
    }

    public List<Claim> getClaims() {
        return claims;
    }
}
