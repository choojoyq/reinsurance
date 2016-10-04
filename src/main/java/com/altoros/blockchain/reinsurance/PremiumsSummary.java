package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;

/**
 * @author Nikita Gorbachevski
 */
public class PremiumsSummary {

    private final BigDecimal totalClaims;
    private final BigDecimal reinstatementPremium;
    private final BigDecimal additionalPremium;
    private final BigDecimal depositPremium;
    private final BigDecimal adjustmentPremium;
    private final BigDecimal profitCommission;
    private final BigDecimal noClaimsBonus;

    public PremiumsSummary(BigDecimal totalClaims, BigDecimal reinstatementPremium, BigDecimal additionalPremium,
                           BigDecimal depositPremium, BigDecimal adjustmentPremium, BigDecimal profitCommission, BigDecimal noClaimsBonus) {
        this.totalClaims = totalClaims;
        this.reinstatementPremium = reinstatementPremium;
        this.additionalPremium = additionalPremium;
        this.depositPremium = depositPremium;
        this.adjustmentPremium = adjustmentPremium;
        this.profitCommission = profitCommission;
        this.noClaimsBonus = noClaimsBonus;
    }

    public BigDecimal getTotalClaims() {
        return totalClaims;
    }

    public BigDecimal getReinstatementPremium() {
        return reinstatementPremium;
    }

    public BigDecimal getAdditionalPremium() {
        return additionalPremium;
    }

    public BigDecimal getDepositPremium() {
        return depositPremium;
    }

    public BigDecimal getAdjustmentPremium() {
        return adjustmentPremium;
    }

    public BigDecimal getProfitCommission() {
        return profitCommission;
    }

    public BigDecimal getNoClaimsBonus() {
        return noClaimsBonus;
    }
}
