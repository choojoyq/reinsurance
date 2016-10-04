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

    private Contract contractEnergyOnshore;
    private Contract contractAviation;
    private List<Claim> claims = new ArrayList<>();

    @Before
    public void setUp() {
        this.contractEnergyOnshore = new Contract();
        contractEnergyOnshore.setCedant("ACGS");
        contractEnergyOnshore.setReinsurer("ART");
        contractEnergyOnshore.setShare(BigDecimal.ONE);
        contractEnergyOnshore.setInceptionDate(LocalDate.of(2015, 1, 1));
        contractEnergyOnshore.setExpiryDate(LocalDate.of(2015, 12, 31));
        contractEnergyOnshore.setBasis("LOD");
        contractEnergyOnshore.setLineOfBusiness("Energy Onshore");
        contractEnergyOnshore.setPeril("All");
        contractEnergyOnshore.setTerritory("Worldwide");
        contractEnergyOnshore.setCurrency("USD");
        contractEnergyOnshore.setRiskFinancial(new Financial(new BigDecimal(20_000_000), new BigDecimal(30_000_000), "Ded"));
        contractEnergyOnshore.setEventFinancial(new Financial(new BigDecimal(40_000_000), BigDecimal.ZERO, "Ded"));
        contractEnergyOnshore.setPeriodFinancial(new Financial(new BigDecimal(60_000_000), BigDecimal.ZERO, "Ded"));
        contractEnergyOnshore.setMinimum(new BigDecimal(8_400_000));
        contractEnergyOnshore.setDeposit(new BigDecimal(8_400_000));
        contractEnergyOnshore.setAdjustableRate(BigDecimal.valueOf(0.04599));
        contractEnergyOnshore.setSubjectPremiumFinal(new BigDecimal(182_643_997));
        contractEnergyOnshore.setSubjectPremiumFinalAdjustedDate(LocalDate.of(2016, 1, 1));
        contractEnergyOnshore.setReinstatementPremium(new BigDecimal(2));
        contractEnergyOnshore.setReinstatementPremiumPercentage(BigDecimal.ONE);
        contractEnergyOnshore.setAdditionalPremium(BigDecimal.ZERO);
        contractEnergyOnshore.setProfitCommission(BigDecimal.ZERO);
        contractEnergyOnshore.setNoClaimsBonus(BigDecimal.ZERO);
        contractEnergyOnshore.setPaymentSchedules(Arrays.asList(
                new PaymentSchedule(LocalDate.of(2015, 1, 1), new BigDecimal(4_200_000)),
                new PaymentSchedule(LocalDate.of(2015, 6, 1), new BigDecimal(4_200_000))));
        contractEnergyOnshore.setStatus("APPROVED");

        contractAviation = new Contract();
        contractAviation.setCedant("ACGS");
        contractAviation.setReinsurer("ART");
        contractAviation.setShare(BigDecimal.valueOf(0.75));
        contractAviation.setInceptionDate(LocalDate.of(2015, 1, 1));
        contractAviation.setExpiryDate(LocalDate.of(2015, 12, 31));
        contractAviation.setBasis("RA");
        contractAviation.setLineOfBusiness("Aviation");
        contractAviation.setPeril("All");
        contractAviation.setTerritory("Worldwide");
        contractAviation.setCurrency("USD");
        contractAviation.setRiskFinancial(new Financial(new BigDecimal(Long.MAX_VALUE), BigDecimal.ZERO, "Ded"));
        contractAviation.setEventFinancial(new Financial(new BigDecimal(75_000_000), new BigDecimal(25_000_000), "Ded"));
        contractAviation.setPeriodFinancial(new Financial(new BigDecimal(300_000_000), BigDecimal.ZERO, "Ded"));
        contractAviation.setMinimum(new BigDecimal(15_375_000));
        contractAviation.setDeposit(new BigDecimal(15_375_000));
        contractAviation.setAdjustableRate(BigDecimal.valueOf(0.02727));
        contractAviation.setSubjectPremiumFinal(new BigDecimal(563_804_995));
        contractAviation.setSubjectPremiumFinalAdjustedDate(LocalDate.of(2016, 1, 1));
        contractAviation.setReinstatementPremium(new BigDecimal(3));
        contractAviation.setReinstatementPremiumPercentage(BigDecimal.valueOf(1.1));
        contractAviation.setAdditionalPremium(BigDecimal.ZERO);
        contractAviation.setProfitCommission(BigDecimal.ZERO);
        contractAviation.setNoClaimsBonus(BigDecimal.ZERO);
        contractAviation.setPaymentSchedules(Arrays.asList(
                new PaymentSchedule(LocalDate.of(2015, 1, 1), new BigDecimal(7_687_500)),
                new PaymentSchedule(LocalDate.of(2015, 6, 1), new BigDecimal(7_687_500))));
        contractAviation.setStatus("APPROVED");


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
    public void testProcessClaimsAviation() {
        ClaimsSummary claimsSummary = this.contractAviation.processClaims(this.claims);
        Assert.assertEquals(0, new BigDecimal(115_000_000).compareTo(claimsSummary.getPaid()));
        Assert.assertEquals(0, new BigDecimal(145_000_000).compareTo(claimsSummary.getIncurred()));
        Assert.assertEquals(5, claimsSummary.getClaims().size());
    }

    @Test
    public void testProcessClaimsEnergyOnshore() {
        ClaimsSummary claimsSummary = this.contractEnergyOnshore.processClaims(this.claims);
        Assert.assertEquals(0, new BigDecimal(43_000_000).compareTo(claimsSummary.getPaid()));
        Assert.assertEquals(0, new BigDecimal(64_800_000).compareTo(claimsSummary.getIncurred()));
        Assert.assertEquals(5, claimsSummary.getClaims().size());
    }

    @Test
    public void testProcessPremiumsAviation() {
        ClaimsSummary claimsSummary = new ClaimsSummary(
                new BigDecimal(115_000_000), new BigDecimal(145_000_000), this.claims);
        LocalDate evaluationDate = LocalDate.of(2015, 8, 30);
        ContractSummary contractSummary = this.contractAviation.processPremiums(claimsSummary, evaluationDate);

        Assert.assertEquals(0, new BigDecimal(115_000_000).compareTo(contractSummary.getPremiumsSummaryPaid().getTotalClaims()));
        Assert.assertEquals(0, new BigDecimal(145_000_000).compareTo(contractSummary.getPremiumsSummaryIncurred().getTotalClaims()));

        Assert.assertEquals(0, new BigDecimal(25_932_500).compareTo(contractSummary.getPremiumsSummaryPaid().getReinstatementPremium()));
        Assert.assertEquals(0, new BigDecimal(32_697_500).compareTo(contractSummary.getPremiumsSummaryIncurred().getReinstatementPremium()));

        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryPaid().getAdditionalPremium()));
        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryIncurred().getAdditionalPremium()));

        Assert.assertEquals(0, new BigDecimal(15_375_000).compareTo(contractSummary.getPremiumsSummaryPaid().getDepositPremium()));
        Assert.assertEquals(0, new BigDecimal(15_375_000).compareTo(contractSummary.getPremiumsSummaryIncurred().getDepositPremium()));

        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryPaid().getAdjustmentPremium()));
        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryIncurred().getAdjustmentPremium()));

        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryPaid().getProfitCommission()));
        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryIncurred().getProfitCommission()));

        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryPaid().getNoClaimsBonus()));
        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryIncurred().getNoClaimsBonus()));

        Assert.assertEquals(claimsSummary, contractSummary.getClaimsSummary());
        Assert.assertEquals(evaluationDate, contractSummary.getEvaluationDate());
    }

    @Test
    public void testProcessPremiumsEnergyOnshore() {
        ClaimsSummary claimsSummary = new ClaimsSummary(
                new BigDecimal(43_000_000), new BigDecimal(64_000_000), this.claims);
        LocalDate evaluationDate = LocalDate.of(2015, 8, 30);
        ContractSummary contractSummary = this.contractEnergyOnshore.processPremiums(claimsSummary, evaluationDate);

        Assert.assertEquals(0, new BigDecimal(43_000_000).compareTo(contractSummary.getPremiumsSummaryPaid().getTotalClaims()));
        Assert.assertEquals(0, new BigDecimal(60_000_000).compareTo(contractSummary.getPremiumsSummaryIncurred().getTotalClaims()));

        Assert.assertEquals(0, new BigDecimal(16_800_000).compareTo(contractSummary.getPremiumsSummaryPaid().getReinstatementPremium()));
        Assert.assertEquals(0, new BigDecimal(16_800_000).compareTo(contractSummary.getPremiumsSummaryIncurred().getReinstatementPremium()));

        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryPaid().getAdditionalPremium()));
        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryIncurred().getAdditionalPremium()));

        Assert.assertEquals(0, new BigDecimal(8_400_000).compareTo(contractSummary.getPremiumsSummaryPaid().getDepositPremium()));
        Assert.assertEquals(0, new BigDecimal(8_400_000).compareTo(contractSummary.getPremiumsSummaryIncurred().getDepositPremium()));

        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryPaid().getAdjustmentPremium()));
        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryIncurred().getAdjustmentPremium()));

        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryPaid().getProfitCommission()));
        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryIncurred().getProfitCommission()));

        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryPaid().getNoClaimsBonus()));
        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryIncurred().getNoClaimsBonus()));

        Assert.assertEquals(claimsSummary, contractSummary.getClaimsSummary());
        Assert.assertEquals(evaluationDate, contractSummary.getEvaluationDate());
    }
}
