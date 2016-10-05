package com.altoros.blockchain.reinsurance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Nikita Gorbachevski
 */
public class RetrocessionContractTest extends BaseContractTest {

    private Contract contract;
    private List<Claim> claims = new ArrayList<>();

    @Before
    public void setUp() {
        this.contract = new Contract();
        contract.setCedant("ART");
        contract.setReinsurer("Az Re");
        contract.setShare(BigDecimal.valueOf(0.5));
        contract.setInceptionDate(LocalDate.of(2015, 1, 1));
        contract.setExpiryDate(LocalDate.of(2015, 12, 31));
        contract.setBasis("LOD");
        contract.setLineOfBusiness("All");
        contract.setPeril("All");
        contract.setTerritory("Worldwide");
        contract.setCurrency("EUR");
        contract.setRiskFinancial(new Financial(new BigDecimal(Long.MAX_VALUE), BigDecimal.ZERO, "Ded"));
        contract.setEventFinancial(new Financial(new BigDecimal(Long.MAX_VALUE), BigDecimal.ZERO, "Ded"));
        contract.setPeriodFinancial(new Financial(new BigDecimal(112_500_000), new BigDecimal(112_500_000), "Ded"));
        contract.setMinimum(new BigDecimal(16_875_000));
        contract.setDeposit(new BigDecimal(16_875_000));
        contract.setAdjustableRate(BigDecimal.ZERO);
        contract.setSubjectPremiumFinal(BigDecimal.ZERO);
        contract.setSubjectPremiumFinalAdjustedDate(LocalDate.of(2016, 1, 1));
        contract.setReinstatementPremium(BigDecimal.ZERO);
        contract.setReinstatementPremiumPercentage(BigDecimal.ONE);
        contract.setAdditionalPremium(BigDecimal.ZERO);
        contract.setProfitCommission(BigDecimal.ZERO);
        contract.setNoClaimsBonus(BigDecimal.ZERO);
        contract.setPaymentSchedules(Collections.singletonList(
                new PaymentSchedule(LocalDate.of(2015, 2, 15), new BigDecimal(16_875_000))));
        contract.setStatus("APPROVED");

        Claim claim = new Claim();
        claim.setCedant("ART");
        claim.setLineOfBusiness("Aviation");
        claim.setTerritory("");
        claim.setPeril("All");
        claim.setClaimId("123-1");
        claim.setPolicyId(1234);
        claim.setEventId(999);
        claim.setPolicyInceptionDate(LocalDate.of(2015, 2, 1));
        claim.setLossDate(LocalDate.of(2015, 3, 31));
        claim.setReportDate(LocalDate.of(2016, 8, 25));
        claim.setPaid(new BigDecimal(86_250_000));
        claim.setReserved(new BigDecimal(22_500_000));
        claim.setIncurred(new BigDecimal(108_750_000));
        claim.setCurrency("USD");
        claim.setStatus("APPROVED");
        claims.add(claim);

        claim = new Claim();
        claim.setCedant("ART");
        claim.setLineOfBusiness("Energy Onshore");
        claim.setTerritory("");
        claim.setPeril("All");
        claim.setClaimId("123-2");
        claim.setPolicyId(5678);
        claim.setEventId(888);
        claim.setPolicyInceptionDate(LocalDate.of(2015, 2, 1));
        claim.setLossDate(LocalDate.of(2015, 4, 15));
        claim.setReportDate(LocalDate.of(2016, 8, 25));
        claim.setPaid(new BigDecimal(43_000_000));
        claim.setReserved(new BigDecimal(17_000_000));
        claim.setIncurred(new BigDecimal(60_000_000));
        claim.setCurrency("USD");
        claim.setStatus("APPROVED");
        claims.add(claim);
    }

    @Test
    public void testProcessClaims() {
        ClaimsSummary claimsSummary = this.contract.processClaims(this.claims);
        Assert.assertEquals(new BigDecimal(117_500_000), claimsSummary.getPaid());
        Assert.assertEquals(new BigDecimal(153_409_091), (claimsSummary.getIncurred()));
        Assert.assertEquals(2, claimsSummary.getClaims().size());
    }

    @Test
    public void testProcessPremiums() {
        ClaimsSummary claimsSummary = new ClaimsSummary(
                new BigDecimal(117_500_000), new BigDecimal(153_409_091), null);
        LocalDate evaluationDate = LocalDate.of(2015, 8, 30);
        ContractSummary contractSummary = this.contract.processPremiums(claimsSummary, evaluationDate);

        // full
        PremiumsSummary premiumsSummaryFull = contractSummary.getPremiumsSummaryFull();
        Premiums premiumsFullPaid = premiumsSummaryFull.getPremiumsPaid();
        Premiums premiumsFullIncurred = premiumsSummaryFull.getPremiumsIncurred();

        comparePremiums(premiumsFullPaid, new Premiums(new BigDecimal(5_000_000), BigDecimal.ZERO, BigDecimal.ZERO,
                new BigDecimal(16_875_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

        comparePremiums(premiumsFullIncurred, new Premiums(new BigDecimal(40_909_091), BigDecimal.ZERO, BigDecimal.ZERO,
                new BigDecimal(16_875_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

        // shared
        PremiumsSummary premiumsSummaryShared = contractSummary.getPremiumsSummaryShared();
        Premiums premiumsSharedPaid = premiumsSummaryShared.getPremiumsPaid();
        Premiums premiumsSharedIncurred = premiumsSummaryShared.getPremiumsIncurred();

        comparePremiums(premiumsSharedPaid, new Premiums(new BigDecimal(2_500_000), BigDecimal.ZERO, BigDecimal.ZERO,
                new BigDecimal(8_437_500), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

        comparePremiums(premiumsSharedIncurred, new Premiums(new BigDecimal(20_454_545), BigDecimal.ZERO, BigDecimal.ZERO,
                new BigDecimal(8_437_500), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO));

        Assert.assertEquals(claimsSummary, contractSummary.getClaimsSummary());
        Assert.assertEquals(evaluationDate, contractSummary.getEvaluationDate());
    }

    @Test
    public void testProcessCumulativePositions() {
        ContractSummary contractSummary = new ContractSummary(
                new PremiumsSummary(
                        new Premiums(new BigDecimal(5_000_000), BigDecimal.ZERO,
                                BigDecimal.ZERO, new BigDecimal(16_875_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                        new Premiums(new BigDecimal(40_909_091), BigDecimal.ZERO,
                                BigDecimal.ZERO, new BigDecimal(16_875_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)),
                new PremiumsSummary(
                        new Premiums(new BigDecimal(2_500_000), BigDecimal.ZERO,
                                BigDecimal.ZERO, new BigDecimal(8_437_500), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                        new Premiums(new BigDecimal(20_454_545), BigDecimal.ZERO,
                                BigDecimal.ZERO, new BigDecimal(8_437_500), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO)),
                null, LocalDate.of(2015, 8, 30));

        LossesAndProfitsSummary lossesAndProfitsSummary = this.contract.processCumulativePositions(contractSummary);

        // full
        CumulativePositionsSummary cumulativePositionsSummaryFull = lossesAndProfitsSummary.getCumulativePositionsSummaryFull();
        CumulativePosition lossFull = cumulativePositionsSummaryFull.getLoss();
        CumulativePosition writtenFull = cumulativePositionsSummaryFull.getPremiumWritten();
        CumulativePosition earnedFull = cumulativePositionsSummaryFull.getPremiumEarned();

        compareGrossCededNet(lossFull.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-40_909_091), new BigDecimal(-40_909_091)));
        compareGrossCededNet(lossFull.getReinsurer(), new GrossCededNet(new BigDecimal(40_909_091), BigDecimal.ZERO, new BigDecimal(40_909_091)));

        compareGrossCededNet(writtenFull.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-16_875_000), new BigDecimal(-16_875_000)));
        compareGrossCededNet(writtenFull.getReinsurer(), new GrossCededNet(new BigDecimal(16_875_000), BigDecimal.ZERO, new BigDecimal(16_875_000)));

        compareGrossCededNet(earnedFull.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-11_172_734), new BigDecimal(-11_172_734)));
        compareGrossCededNet(earnedFull.getReinsurer(), new GrossCededNet(new BigDecimal(11_172_734), BigDecimal.ZERO, new BigDecimal(11_172_734)));

        // shared
        CumulativePositionsSummary cumulativePositionsSummaryShared = lossesAndProfitsSummary.getCumulativePositionsSummaryShared();
        CumulativePosition lossShared = cumulativePositionsSummaryShared.getLoss();
        CumulativePosition writtenShared = cumulativePositionsSummaryShared.getPremiumWritten();
        CumulativePosition earnedShared = cumulativePositionsSummaryShared.getPremiumEarned();

        compareGrossCededNet(lossShared.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-20_454_545), new BigDecimal(-20_454_545)));
        compareGrossCededNet(lossShared.getReinsurer(), new GrossCededNet(new BigDecimal(20_454_545), BigDecimal.ZERO, new BigDecimal(20_454_545)));

        compareGrossCededNet(writtenShared.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-8_437_500), new BigDecimal(-8_437_500)));
        compareGrossCededNet(writtenShared.getReinsurer(), new GrossCededNet(new BigDecimal(8_437_500), BigDecimal.ZERO, new BigDecimal(8_437_500)));

        compareGrossCededNet(earnedShared.getCedant(), new GrossCededNet(BigDecimal.ZERO, new BigDecimal(-5_586_367), new BigDecimal(-5_586_367)));
        compareGrossCededNet(earnedShared.getReinsurer(), new GrossCededNet(new BigDecimal(5_586_367), BigDecimal.ZERO, new BigDecimal(5_586_367)));

        Assert.assertEquals(BigDecimal.valueOf(0.662), lossesAndProfitsSummary.getLossOccurringDuring());
        Assert.assertEquals(BigDecimal.valueOf(0.219), lossesAndProfitsSummary.getRisksAttaching());
    }
}
