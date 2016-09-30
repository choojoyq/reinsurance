package com.altoros.blockchain.reinsurance;

/**
 * @author Nikita Gorbachevski
 */
public class Financial {
    private final long priority;
    private final long limit;
    private final String priorityType;

    public Financial(long priority, long limit, String priorityType) {
        this.priority = priority;
        this.limit = limit;
        this.priorityType = priorityType;
    }

    public long getPriority() {
        return priority;
    }

    public long getLimit() {
        return limit;
    }

    public String getPriorityType() {
        return priorityType;
    }
}
