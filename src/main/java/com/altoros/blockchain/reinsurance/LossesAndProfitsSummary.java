package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;

/**
 * @author Nikita Gorbachevski
 */
public class LossesAndProfitsSummary {

    private final CumulativePositionsSummary cumulativePositionsSummaryFull;
    private final CumulativePositionsSummary cumulativePositionsSummaryShared;
    private final BigDecimal lossOccurringDuring;
    private final BigDecimal risksAttaching;

    public LossesAndProfitsSummary(CumulativePositionsSummary cumulativePositionsSummaryFull,
                                   CumulativePositionsSummary cumulativePositionsSummaryShared,
                                   BigDecimal lossOccurringDuring, BigDecimal risksAttaching) {
        this.cumulativePositionsSummaryFull = cumulativePositionsSummaryFull;
        this.cumulativePositionsSummaryShared = cumulativePositionsSummaryShared;
        this.lossOccurringDuring = lossOccurringDuring;
        this.risksAttaching = risksAttaching;
    }

    public CumulativePositionsSummary getCumulativePositionsSummaryFull() {
        return cumulativePositionsSummaryFull;
    }

    public CumulativePositionsSummary getCumulativePositionsSummaryShared() {
        return cumulativePositionsSummaryShared;
    }

    public BigDecimal getLossOccurringDuring() {
        return lossOccurringDuring;
    }

    public BigDecimal getRisksAttaching() {
        return risksAttaching;
    }
}
