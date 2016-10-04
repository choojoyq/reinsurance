package com.altoros.blockchain.reinsurance;

import java.time.LocalDate;

/**
 * @author Nikita Gorbachevski
 */
public class ContractSummary {

    private final PremiumsSummary premiumsSummaryPaid;
    private final PremiumsSummary premiumsSummaryIncurred;
    private final ClaimsSummary claimsSummary;
    private final LocalDate evaluationDate;

    public ContractSummary(PremiumsSummary premiumsSummaryPaid, PremiumsSummary premiumsSummaryIncurred, ClaimsSummary claimsSummary, LocalDate evaluationDate) {
        this.premiumsSummaryPaid = premiumsSummaryPaid;
        this.premiumsSummaryIncurred = premiumsSummaryIncurred;
        this.claimsSummary = claimsSummary;
        this.evaluationDate = evaluationDate;
    }

    public PremiumsSummary getPremiumsSummaryPaid() {
        return premiumsSummaryPaid;
    }

    public PremiumsSummary getPremiumsSummaryIncurred() {
        return premiumsSummaryIncurred;
    }

    public ClaimsSummary getClaimsSummary() {
        return claimsSummary;
    }

    public LocalDate getEvaluationDate() {
        return evaluationDate;
    }
}
