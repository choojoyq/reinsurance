package com.altoros.blockchain.reinsurance;

import java.time.LocalDate;

/**
 * @author Nikita Gorbachevski
 */
public class ContractSummary {

    private final PremiumsSummary premiumsSummaryFull;
    private final PremiumsSummary premiumsSummaryShared;
    private final ClaimsSummary claimsSummary;
    private final LocalDate evaluationDate;

    public ContractSummary(PremiumsSummary premiumsSummaryFull, PremiumsSummary premiumsSummaryShared,
                           ClaimsSummary claimsSummary, LocalDate evaluationDate) {
        this.premiumsSummaryFull = premiumsSummaryFull;
        this.premiumsSummaryShared = premiumsSummaryShared;
        this.claimsSummary = claimsSummary;
        this.evaluationDate = evaluationDate;
    }

    public PremiumsSummary getPremiumsSummaryFull() {
        return premiumsSummaryFull;
    }

    public PremiumsSummary getPremiumsSummaryShared() {
        return premiumsSummaryShared;
    }

    public ClaimsSummary getClaimsSummary() {
        return claimsSummary;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }
}
