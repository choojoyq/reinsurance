package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;

/**
 * @author Nikita Gorbachevski
 */
public class Financial {

    private final BigDecimal priority;
    private final BigDecimal limit;
    private final String priorityType;

    public Financial(BigDecimal priority, BigDecimal limit, String priorityType) {
        this.priority = priority;
        this.limit = limit;
        this.priorityType = priorityType;
    }

    public BigDecimal getPriority() {
        return priority;
    }

    public BigDecimal getLimit() {
        return limit;
    }

    public String getPriorityType() {
        return priorityType;
    }
}
