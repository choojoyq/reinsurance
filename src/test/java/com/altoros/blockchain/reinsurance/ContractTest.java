package com.altoros.blockchain.reinsurance;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Nikita Gorbachevski
 */
@RunWith(MockitoJUnitRunner.class)
public class ContractTest {

    private Contract contract;
    private List<Claim> claims = new ArrayList<>();

    @Before
    public void setUp() {
        this.contract = new Contract();
        contract.setCedant("ACGS");
        contract.setReinsurer("ART");
        contract.setShare(0.75);
        contract.setInceptionDate(LocalDate.of(2015, 1, 1));
        contract.setExpiryDate(LocalDate.of(2015, 12, 31));
        contract.setBasis("RA");
        contract.setLineOfBusiness("Aviation");
        contract.setPeril("All");
        contract.setTerritory("Worldwide");
        contract.setCurrency("USD");
        contract.setRiskFinancial(new Financial(Long.MAX_VALUE, 0, "Ded"));
        contract.setEventFinancial(new Financial(75_000_000, 25_000_000, "Ded"));
        contract.setPeriodFinancial(new Financial(300_000_000, 0, "Ded"));
        contract.setMinimum(15_375_000);
        contract.setDeposit(15_375_000);
        contract.setAdjustableRate(2.727);
        contract.setSubjectPremiumFinal(563_804_995);
        contract.setReinstatementPremium(3);
        contract.setReinstatementPremiumPercentage(1.1);
        contract.setAdditionalPremium(0);
        contract.setNoClaimsBonus(0);
        contract.setPaymentSchedules(Arrays.asList(
                new PaymentSchedule(LocalDate.of(2015, 1, 1), 7_687_500),
                new PaymentSchedule(LocalDate.of(2015, 6, 1), 7_687_500)));
        contract.setStatus("APPROVED");

        Claim claim = new Claim();
        claim.setCedant("ACGS");
        claim.setLineOfBusiness("Aviation");
        claim.setTerritory("USA");
        claim.setPeril("All");
        claim.setClaimId("123-1");
        claim.setPolicyId(123);
        claim.setEventId(55);
        claim.setPolicyInceptionDate(LocalDate.of(2015, 2, 1));
        claim.setLossDate(LocalDate.of(2015, 3, 31));
        claim.setReportDate(LocalDate.of(2016, 8, 25));
        claim.setPaid(40_000_000);
        claim.setReserved(10_000_000);
        claim.setIncurred(50_000_000);
        claim.setCurrency("USD");
        claim.setStatus("APPROVED");
        claims.add(claim);
    }

    @Test
    public void testFilter() {
        List<Claim> claims = this.contract.filter(this.claims);
        assertEquals(1, claims.size());
    }
}
