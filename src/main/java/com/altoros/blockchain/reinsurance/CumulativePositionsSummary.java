package com.altoros.blockchain.reinsurance;

/**
 * @author Nikita Gorbachevski
 */
public class CumulativePositionsSummary {

    private final CumulativePosition loss;
    private final CumulativePosition premiumWritten;
    private final CumulativePosition premiumEarned;

    public CumulativePositionsSummary(CumulativePosition loss, CumulativePosition premiumWritten, CumulativePosition premiumEarned) {
        this.loss = loss;
        this.premiumWritten = premiumWritten;
        this.premiumEarned = premiumEarned;
    }

    public CumulativePosition getLoss() {
        return loss;
    }

    public CumulativePosition getPremiumWritten() {
        return premiumWritten;
    }

    public CumulativePosition getPremiumEarned() {
        return premiumEarned;
    }
}
