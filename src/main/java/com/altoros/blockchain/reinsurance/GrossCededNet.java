package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;

/**
 * @author Nikita Gorbachevski
 */
public class GrossCededNet {

    private final BigDecimal gross;
    private final BigDecimal ceded;
    private final BigDecimal net;

    public GrossCededNet(BigDecimal gross, BigDecimal ceded, BigDecimal net) {
        this.gross = gross;
        this.ceded = ceded;
        this.net = net;
    }

    public BigDecimal getGross() {
        return gross;
    }

    public BigDecimal getCeded() {
        return ceded;
    }

    public BigDecimal getNet() {
        return net;
    }
}
