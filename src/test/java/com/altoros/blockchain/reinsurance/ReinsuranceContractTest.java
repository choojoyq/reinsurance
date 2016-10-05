package com.altoros.blockchain.reinsurance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Nikita Gorbachevski
 */
public class ReinsuranceContractTest extends BaseContractTest {

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
        Assert.assertEquals(new BigDecimal(115_000_000), claimsSummary.getPaid());
        Assert.assertEquals(new BigDecimal(145_000_000), claimsSummary.getIncurred());
        Assert.assertEquals(5, claimsSummary.getClaims().size());
    }

    @Test
    public void testProcessClaimsEnergyOnshore() {
        ClaimsSummary claimsSummary = this.contractEnergyOnshore.processClaims(this.claims);
        Assert.assertEquals(new BigDecimal(43_000_000), claimsSummary.getPaid());
        Assert.assertEquals(new BigDecimal(64_800_000), claimsSummary.getIncurred());
        Assert.assertEquals(5, claimsSummary.getClaims().size());
    }

    @Test
    public void testProcessPremiumsAviation() {
        ClaimsSummary claimsSummary = new ClaimsSummary(
                new BigDecimal(115_000_000), new BigDecimal(145_000_000), this.claims);
        LocalDate evaluationDate = LocalDate.of(2015, 8, 30);
        ContractSummary contractSummary = this.contractAviation.processPremiums(claimsSummary, evaluationDate);

        // full
        PremiumsSummary premiumsSummaryFull = contractSummary.getPremiumsSummaryFull();
        Premiums premiumsFullPaid = premiumsSummaryFull.getPremiumsPaid();
        Premiums premiumsFullIncurred = premiumsSummaryFull.getPremiumsIncurred();

        comparePremiums(premiumsFullPaid, new Premiums(new BigDecimal(115_000_000), new BigDecimal(25_932_500), BigDecimal.ZERO,
                new BigDecimal(15_375_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

        comparePremiums(premiumsFullIncurred, new Premiums(new BigDecimal(145_000_000), new BigDecimal(32_697_500), BigDecimal.ZERO,
                new BigDecimal(15_375_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

        // shared
        PremiumsSummary premiumsSummaryShared = contractSummary.getPremiumsSummaryShared();
        Premiums premiumsSharedPaid = premiumsSummaryShared.getPremiumsPaid();
        Premiums premiumsSharedIncurred = premiumsSummaryShared.getPremiumsIncurred();

        comparePremiums(premiumsSharedPaid, new Premiums(new BigDecimal(86_250_000), new BigDecimal(19_449_375), BigDecimal.ZERO,
                new BigDecimal(11_531_250), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

        comparePremiums(premiumsSharedIncurred, new Premiums(new BigDecimal(108_750_000), new BigDecimal(24_523_125), BigDecimal.ZERO,
                new BigDecimal(11_531_250), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

        Assert.assertEquals(claimsSummary, contractSummary.getClaimsSummary());
        Assert.assertEquals(evaluationDate, contractSummary.getEvaluationDate());
    }

    @Test
    public void testProcessPremiumsEnergyOnshore() {
        ClaimsSummary claimsSummary = new ClaimsSummary(
                new BigDecimal(43_000_000), new BigDecimal(64_000_000), null);
        LocalDate evaluationDate = LocalDate.of(2015, 8, 30);
        ContractSummary contractSummary = this.contractEnergyOnshore.processPremiums(claimsSummary, evaluationDate);

        // full
        PremiumsSummary premiumsSummaryFull = contractSummary.getPremiumsSummaryFull();
        Premiums premiumsFullPaid = premiumsSummaryFull.getPremiumsPaid();
        Premiums premiumsFullIncurred = premiumsSummaryFull.getPremiumsIncurred();

        comparePremiums(premiumsFullPaid, new Premiums(new BigDecimal(43_000_000), new BigDecimal(16_800_000), BigDecimal.ZERO,
                new BigDecimal(8_400_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

        comparePremiums(premiumsFullIncurred, new Premiums(new BigDecimal(60_000_000), new BigDecimal(16_800_000), BigDecimal.ZERO,
                new BigDecimal(8_400_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

        // shared
        PremiumsSummary premiumsSummaryShared = contractSummary.getPremiumsSummaryShared();
        Premiums premiumsSharedPaid = premiumsSummaryShared.getPremiumsPaid();
        Premiums premiumsSharedIncurred = premiumsSummaryShared.getPremiumsIncurred();

        comparePremiums(premiumsSharedPaid, new Premiums(new BigDecimal(43_000_000), new BigDecimal(16_800_000), BigDecimal.ZERO,
                new BigDecimal(8_400_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

        comparePremiums(premiumsSharedIncurred, new Premiums(new BigDecimal(60_000_000), new BigDecimal(16_800_000), BigDecimal.ZERO,
                new BigDecimal(8_400_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

        Assert.assertEquals(claimsSummary, contractSummary.getClaimsSummary());
        Assert.assertEquals(evaluationDate, contractSummary.getEvaluationDate());
    }

    @Test
    public void testProcessCumulativePositionsAviation() {
        ContractSummary contractSummary = new ContractSummary(
                new PremiumsSummary(
                        new Premiums(new BigDecimal(115_000_000), new BigDecimal(25_932_500),
                                BigDecimal.ZERO, new BigDecimal(15_375_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                        new Premiums(new BigDecimal(145_000_000), new BigDecimal(32_697_500),
                                BigDecimal.ZERO, new BigDecimal(15_375_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)),
                new PremiumsSummary(
                        new Premiums(new BigDecimal(86_250_000), new BigDecimal(19_449_375),
                                BigDecimal.ZERO, new BigDecimal(11_531_250), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                        new Premiums(new BigDecimal(108_750_000), new BigDecimal(24_523_125),
                                BigDecimal.ZERO, new BigDecimal(11_531_250), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)),
                null, LocalDate.of(2015, 8, 30));

        LossesAndProfitsSummary lossesAndProfitsSummary = this.contractAviation.processCumulativePositions(contractSummary);

        // full
        CumulativePositionsSummary cumulativePositionsSummaryFull = lossesAndProfitsSummary.getCumulativePositionsSummaryFull();
        CumulativePosition lossFull = cumulativePositionsSummaryFull.getLoss();
        CumulativePosition writtenFull = cumulativePositionsSummaryFull.getPremiumWritten();
        CumulativePosition earnedFull = cumulativePositionsSummaryFull.getPremiumEarned();

        compareGrossCededNet(lossFull.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-145_000_000), new BigDecimal(-145_000_000)));
        compareGrossCededNet(lossFull.getReinsurer(), new GrossCededNet(new BigDecimal(145_000_000), BigDecimal.ZERO, new BigDecimal(145_000_000)));

        compareGrossCededNet(writtenFull.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-48_072_500), new BigDecimal(-48_072_500)));
        compareGrossCededNet(writtenFull.getReinsurer(), new GrossCededNet(new BigDecimal(48_072_500), BigDecimal.ZERO, new BigDecimal(48_072_500)));

        compareGrossCededNet(earnedFull.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-36_067_396), new BigDecimal(-36_067_396)));
        compareGrossCededNet(earnedFull.getReinsurer(), new GrossCededNet(new BigDecimal(36_067_396), BigDecimal.ZERO, new BigDecimal(36_067_396)));

        // shared
        CumulativePositionsSummary cumulativePositionsSummaryShared = lossesAndProfitsSummary.getCumulativePositionsSummaryShared();
        CumulativePosition lossShared = cumulativePositionsSummaryShared.getLoss();
        CumulativePosition writtenShared = cumulativePositionsSummaryShared.getPremiumWritten();
        CumulativePosition earnedShared = cumulativePositionsSummaryShared.getPremiumEarned();

        compareGrossCededNet(lossShared.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-108_750_000), new BigDecimal(-108_750_000)));
        compareGrossCededNet(lossShared.getReinsurer(), new GrossCededNet(new BigDecimal(108_750_000), BigDecimal.ZERO, new BigDecimal(108_750_000)));

        compareGrossCededNet(writtenShared.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-36_054_375), new BigDecimal(-36_054_375)));
        compareGrossCededNet(writtenShared.getReinsurer(), new GrossCededNet(new BigDecimal(36_054_375), BigDecimal.ZERO, new BigDecimal(36_054_375)));

        compareGrossCededNet(earnedShared.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-27_050_547), new BigDecimal(-27_050_547)));
        compareGrossCededNet(earnedShared.getReinsurer(), new GrossCededNet(new BigDecimal(27_050_547), BigDecimal.ZERO, new BigDecimal(27_050_547)));

        Assert.assertEquals(BigDecimal.valueOf(0.662), lossesAndProfitsSummary.getLossOccurringDuring());
        Assert.assertEquals(BigDecimal.valueOf(0.219), lossesAndProfitsSummary.getRisksAttaching());
    }

    @Test
    public void testProcessCumulativePositionsEnergyOnshore() {
        ContractSummary contractSummary = new ContractSummary(
                new PremiumsSummary(
                        new Premiums(new BigDecimal(43_000_000), new BigDecimal(16_800_000),
                                BigDecimal.ZERO, new BigDecimal(8_400_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                        new Premiums(new BigDecimal(60_000_000), new BigDecimal(16_800_000),
                                BigDecimal.ZERO, new BigDecimal(8_400_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)),
                new PremiumsSummary(
                        new Premiums(new BigDecimal(43_000_000), new BigDecimal(16_800_000),
                                BigDecimal.ZERO, new BigDecimal(8_400_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                        new Premiums(new BigDecimal(60_000_000), new BigDecimal(16_800_000),
                                BigDecimal.ZERO, new BigDecimal(8_400_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)),
                null, LocalDate.of(2015, 8, 30));

        LossesAndProfitsSummary lossesAndProfitsSummary = this.contractEnergyOnshore.processCumulativePositions(contractSummary);

        // full
        CumulativePositionsSummary cumulativePositionsSummaryFull = lossesAndProfitsSummary.getCumulativePositionsSummaryFull();
        CumulativePosition lossFull = cumulativePositionsSummaryFull.getLoss();
        CumulativePosition writtenFull = cumulativePositionsSummaryFull.getPremiumWritten();
        CumulativePosition earnedFull = cumulativePositionsSummaryFull.getPremiumEarned();

        compareGrossCededNet(lossFull.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-60_000_000), new BigDecimal(-60_000_000)));
        compareGrossCededNet(lossFull.getReinsurer(), new GrossCededNet(new BigDecimal(60_000_000), BigDecimal.ZERO, new BigDecimal(60_000_000)));

        compareGrossCededNet(writtenFull.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-25_200_000), new BigDecimal(-25_200_000)));
        compareGrossCededNet(writtenFull.getReinsurer(), new GrossCededNet(new BigDecimal(25_200_000), BigDecimal.ZERO, new BigDecimal(25_200_000)));

        compareGrossCededNet(earnedFull.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-22_361_538), new BigDecimal(-22_361_538)));
        compareGrossCededNet(earnedFull.getReinsurer(), new GrossCededNet(new BigDecimal(22_361_538), BigDecimal.ZERO, new BigDecimal(22_361_538)));

        // shared
        CumulativePositionsSummary cumulativePositionsSummaryShared = lossesAndProfitsSummary.getCumulativePositionsSummaryShared();
        CumulativePosition lossShared = cumulativePositionsSummaryShared.getLoss();
        CumulativePosition writtenShared = cumulativePositionsSummaryShared.getPremiumWritten();
        CumulativePosition earnedShared = cumulativePositionsSummaryShared.getPremiumEarned();

        compareGrossCededNet(lossShared.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-60_000_000), new BigDecimal(-60_000_000)));
        compareGrossCededNet(lossShared.getReinsurer(), new GrossCededNet(new BigDecimal(60_000_000), BigDecimal.ZERO, new BigDecimal(60_000_000)));

        compareGrossCededNet(writtenShared.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-25_200_000), new BigDecimal(-25_200_000)));
        compareGrossCededNet(writtenShared.getReinsurer(), new GrossCededNet(new BigDecimal(25_200_000), BigDecimal.ZERO, new BigDecimal(25_200_000)));

        compareGrossCededNet(earnedShared.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-22_361_538), new BigDecimal(-22_361_538)));
        compareGrossCededNet(earnedShared.getReinsurer(), new GrossCededNet(new BigDecimal(22_361_538), BigDecimal.ZERO, new BigDecimal(22_361_538)));

        Assert.assertEquals(BigDecimal.valueOf(0.662), lossesAndProfitsSummary.getLossOccurringDuring());
        Assert.assertEquals(BigDecimal.valueOf(0.219), lossesAndProfitsSummary.getRisksAttaching());
    }
}
