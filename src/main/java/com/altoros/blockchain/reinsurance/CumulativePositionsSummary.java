package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;

/**
 * @author Nikita Gorbachevski
 */
public class CumulativePositionsSummary {

    private final CumulativePosition loss;
    private final CumulativePosition premiumWritten;
    private final CumulativePosition premiumEarned;
    private final BigDecimal lossOccurringDuring;
    private final BigDecimal risksAttaching;

    public CumulativePositionsSummary(CumulativePosition loss, CumulativePosition premiumWritten, CumulativePosition premiumEarned,
                                      BigDecimal lossOccurringDuring, BigDecimal risksAttaching) {
        this.loss = loss;
        this.premiumWritten = premiumWritten;
        this.premiumEarned = premiumEarned;
        this.lossOccurringDuring = lossOccurringDuring;
        this.risksAttaching = risksAttaching;
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

    public BigDecimal getLossOccurringDuring() {
        return lossOccurringDuring;
    }

    public BigDecimal getRisksAttaching() {
        return risksAttaching;
    }
}
