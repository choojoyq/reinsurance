package com.altoros.blockchain.reinsurance;

/**
 * @author Nikita Gorbachevski
 */
public class CumulativePosition {

    private final GrossCededNet cedant;
    private final GrossCededNet reinsurer;

    public CumulativePosition(GrossCededNet cedant, GrossCededNet reinsurer) {
        this.cedant = cedant;
        this.reinsurer = reinsurer;
    }

    public GrossCededNet getCedant() {
        return cedant;
    }

    public GrossCededNet getReinsurer() {
        return reinsurer;
    }
}
