package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;

/**
 * @author Nikita Gorbachevski
 */
public class Financial {

    private final BigDecimal limit;
    private final BigDecimal priority;
    private final String priorityType;

    public Financial(BigDecimal limit, BigDecimal priority, String priorityType) {
        this.limit = limit;
        this.priority = priority;
        this.priorityType = priorityType;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public BigDecimal getPriority() {
        return priority;
    }

    public String getPriorityType() {
        return priorityType;
    }
}
