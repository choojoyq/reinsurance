package com.altoros.blockchain.reinsurance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        contract.setShare(1);
        contract.setInceptionDate(LocalDate.of(2015, 1, 1));
        contract.setExpiryDate(LocalDate.of(2015, 12, 31));
        contract.setBasis("LOD");
        contract.setLineOfBusiness("Energy Onshore");
        contract.setPeril("All");
        contract.setTerritory("Worldwide");
        contract.setCurrency("USD");
        contract.setRiskFinancial(new Financial(new BigDecimal(30_000_000), new BigDecimal(20_000_000), "Ded"));
        contract.setEventFinancial(new Financial(BigDecimal.ZERO, new BigDecimal(40_000_000), "Ded"));
        contract.setPeriodFinancial(new Financial(BigDecimal.ZERO, new BigDecimal(60_000_000), "Ded"));
        contract.setMinimum(8_400_000);
        contract.setDeposit(8_400_000);
        contract.setAdjustableRate(4.599);
        contract.setSubjectPremiumFinal(182_643_997);
        contract.setReinstatementPremium(2);
        contract.setReinstatementPremiumPercentage(1);
        contract.setAdditionalPremium(0);
        contract.setProfitCommission(0);
        contract.setNoClaimsBonus(0);
        contract.setPaymentSchedules(Arrays.asList(
                new PaymentSchedule(LocalDate.of(2015, 1, 1), 4_200_000),
                new PaymentSchedule(LocalDate.of(2015, 6, 1), 4_200_000)));
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
        claim.setPaid(new BigDecimal(40_000_000));
        claim.setReserved(new BigDecimal(10_000_000));
        claim.setIncurred(new BigDecimal(50_000_000));
        claim.setCurrency("USD");
        claim.setStatus("APPROVED");
        claims.add(claim);

        claim = new Claim();
        claim.setCedant("ACGS");
        claim.setLineOfBusiness("Aviation");
        claim.setTerritory("USA");
        claim.setPeril("All");
        claim.setClaimId("123-2");
        claim.setPolicyId(123);
        claim.setEventId(56);
        claim.setPolicyInceptionDate(LocalDate.of(2015, 2, 1));
        claim.setLossDate(LocalDate.of(2015, 4, 15));
        claim.setReportDate(LocalDate.of(2016, 8, 25));
        claim.setPaid(new BigDecimal(30_000_000));
        claim.setReserved(new BigDecimal(10_000_000));
        claim.setIncurred(new BigDecimal(40_000_000));
        claim.setCurrency("USD");
        claim.setStatus("APPROVED");
        this.claims.add(claim);

        claim = new Claim();
        claim.setCedant("ACGS");
        claim.setLineOfBusiness("Aviation");
        claim.setTerritory("USA");
        claim.setPeril("All");
        claim.setClaimId("125-1");
        claim.setPolicyId(125);
        claim.setEventId(55);
        claim.setPolicyInceptionDate(LocalDate.of(2015, 2, 1));
        claim.setLossDate(LocalDate.of(2015, 3, 31));
        claim.setReportDate(LocalDate.of(2016, 8, 25));
        claim.setPaid(new BigDecimal(10_000_000));
        claim.setReserved(new BigDecimal(10_000_000));
        claim.setIncurred(new BigDecimal(20_000_000));
        claim.setCurrency("USD");
        claim.setStatus("APPROVED");
        this.claims.add(claim);

        claim = new Claim();
        claim.setCedant("ACGS");
        claim.setLineOfBusiness("Aviation");
        claim.setTerritory("USA");
        claim.setPeril("All");
        claim.setClaimId("126-1");
        claim.setPolicyId(126);
        claim.setEventId(56);
        claim.setPolicyInceptionDate(LocalDate.of(2015, 2, 1));
        claim.setLossDate(LocalDate.of(2015, 4, 15));
        claim.setReportDate(LocalDate.of(2016, 8, 25));
        claim.setPaid(new BigDecimal(45_000_000));
        claim.setReserved(new BigDecimal(10_000_000));
        claim.setIncurred(new BigDecimal(55_000_000));
        claim.setCurrency("USD");
        claim.setStatus("APPROVED");
        this.claims.add(claim);

        claim = new Claim();
        claim.setCedant("ACGS");
        claim.setLineOfBusiness("Aviation");
        claim.setTerritory("USA");
        claim.setPeril("All");
        claim.setClaimId("127-1");
        claim.setPolicyId(127);
        claim.setEventId(55);
        claim.setPolicyInceptionDate(LocalDate.of(2015, 2, 1));
        claim.setLossDate(LocalDate.of(2015, 3, 31));
        claim.setReportDate(LocalDate.of(2016, 8, 25));
        claim.setPaid(new BigDecimal(40_000_000));
        claim.setReserved(new BigDecimal(15_000_000));
        claim.setIncurred(new BigDecimal(55_000_000));
        claim.setCurrency("USD");
        claim.setStatus("APPROVED");
        this.claims.add(claim);

        claim = new Claim();
        claim.setCedant("ACGS");
        claim.setLineOfBusiness("Energy Onshore");
        claim.setTerritory("Canada");
        claim.setPeril("All");
        claim.setClaimId("358-1");
        claim.setPolicyId(358);
        claim.setEventId(59);
        claim.setPolicyInceptionDate(LocalDate.of(2015, 2, 1));
        claim.setLossDate(LocalDate.of(2015, 4, 15));
        claim.setReportDate(LocalDate.of(2016, 8, 25));
        claim.setPaid(new BigDecimal(45_000_000));
        claim.setReserved(new BigDecimal(1_000_000));
        claim.setIncurred(new BigDecimal(46_000_000));
        claim.setCurrency("CAD");
        claim.setStatus("APPROVED");
        this.claims.add(claim);

        claim = new Claim();
        claim.setCedant("ACGS");
        claim.setLineOfBusiness("Energy Onshore");
        claim.setTerritory("Canada");
        claim.setPeril("All");
        claim.setClaimId("359-1");
        claim.setPolicyId(359);
        claim.setEventId(60);
        claim.setPolicyInceptionDate(LocalDate.of(2015, 2, 1));
        claim.setLossDate(LocalDate.of(2015, 4, 30));
        claim.setReportDate(LocalDate.of(2016, 8, 25));
        claim.setPaid(new BigDecimal(40_000_000));
        claim.setReserved(new BigDecimal(20_000_000));
        claim.setIncurred(new BigDecimal(60_000_000));
        claim.setCurrency("CAD");
        claim.setStatus("APPROVED");
        this.claims.add(claim);

        claim = new Claim();
        claim.setCedant("ACGS");
        claim.setLineOfBusiness("Energy Onshore");
        claim.setTerritory("USA");
        claim.setPeril("All");
        claim.setClaimId("358-2");
        claim.setPolicyId(358);
        claim.setEventId(55);
        claim.setPolicyInceptionDate(LocalDate.of(2015, 2, 1));
        claim.setLossDate(LocalDate.of(2015, 3, 31));
        claim.setReportDate(LocalDate.of(2016, 8, 25));
        claim.setPaid(new BigDecimal(35_000_000));
        claim.setReserved(BigDecimal.ZERO);
        claim.setIncurred(new BigDecimal(35_000_000));
        claim.setCurrency("USD");
        claim.setStatus("APPROVED");
        this.claims.add(claim);

        claim = new Claim();
        claim.setCedant("ACGS");
        claim.setLineOfBusiness("Energy Onshore");
        claim.setTerritory("USA");
        claim.setPeril("All");
        claim.setClaimId("360-1");
        claim.setPolicyId(360);
        claim.setEventId(57);
        claim.setPolicyInceptionDate(LocalDate.of(2015, 2, 1));
        claim.setLossDate(LocalDate.of(2015, 4, 15));
        claim.setReportDate(LocalDate.of(2016, 8, 25));
        claim.setPaid(new BigDecimal(70_000_000));
        claim.setReserved(new BigDecimal(10_000_000));
        claim.setIncurred(new BigDecimal(80_000_000));
        claim.setCurrency("USD");
        claim.setStatus("APPROVED");
        this.claims.add(claim);

        claim = new Claim();
        claim.setCedant("ACGS");
        claim.setLineOfBusiness("Energy Onshore");
        claim.setTerritory("USA");
        claim.setPeril("All");
        claim.setClaimId("361-1");
        claim.setPolicyId(361);
        claim.setEventId(58);
        claim.setPolicyInceptionDate(LocalDate.of(2015, 2, 1));
        claim.setLossDate(LocalDate.of(2015, 4, 30));
        claim.setReportDate(LocalDate.of(2016, 8, 25));
        claim.setPaid(new BigDecimal(40_000_000));
        claim.setReserved(new BigDecimal(5_000_000));
        claim.setIncurred(new BigDecimal(45_000_000));
        claim.setCurrency("USD");
        claim.setStatus("APPROVED");
        this.claims.add(claim);
    }

    @Test
    public void testProcessEnergyOnshore() {
        ClaimsAccounting claimsAccounting = this.contract.process(this.claims);
        Assert.assertEquals(0, new BigDecimal(43_000_000).compareTo(claimsAccounting.getPaid()));
        Assert.assertEquals(0, new BigDecimal(64_800_000).compareTo(claimsAccounting.getIncurred()));
        Assert.assertEquals(this.claims.size(), claimsAccounting.getClaims().size());
    }
}
