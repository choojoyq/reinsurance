package com.altoros.blockchain.reinsurance;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Nikita Gorbachevski
 */
@RunWith(MockitoJUnitRunner.class)
public abstract class BaseContractTest {

    protected void compareGrossCededNet(GrossCededNet g1, GrossCededNet g2) {
        Assert.assertEquals(g1.getGross(), g2.getGross());
        Assert.assertEquals(g1.getCeded(), g2.getCeded());
        Assert.assertEquals(g1.getNet(), g2.getNet());
    }

    protected void comparePremiums(Premiums p1, Premiums p2) {
        Assert.assertEquals(p1.getTotalClaims(), p2.getTotalClaims());
        Assert.assertEquals(p1.getReinstatementPremium(), p2.getReinstatementPremium());
        Assert.assertEquals(p1.getAdditionalPremium(), p2.getAdditionalPremium());
        Assert.assertEquals(p1.getDepositPremium(), p2.getDepositPremium());
        Assert.assertEquals(p1.getAdjustmentPremium(), p2.getAdjustmentPremium());
        Assert.assertEquals(p1.getProfitCommission(), p2.getProfitCommission());
        Assert.assertEquals(p1.getNoClaimsBonus(), p2.getNoClaimsBonus());
    }
}
