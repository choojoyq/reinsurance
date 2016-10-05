package com.altoros.blockchain.reinsurance;

/**
 * @author Nikita Gorbachevski
 */
public class PremiumsSummary {

    private final Premiums premiumsPaid;
    private final Premiums premiumsIncurred;

    public PremiumsSummary(Premiums premiumsPaid, Premiums premiumsIncurred) {
        this.premiumsPaid = premiumsPaid;
        this.premiumsIncurred = premiumsIncurred;
    }

    public Premiums getPremiumsPaid() {
        return premiumsPaid;
    }

    public Premiums getPremiumsIncurred() {
        return premiumsIncurred;
    }
}
