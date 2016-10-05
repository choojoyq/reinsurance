package com.altoros.blockchain.reinsurance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Nikita Gorbachevski
 */
@RunWith(MockitoJUnitRunner.class)
public class RetrocessionContractTest {

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
        Assert.assertEquals(0, new BigDecimal(117_500_000).compareTo(claimsSummary.getPaid()));
        Assert.assertEquals(0, new BigDecimal(153_409_091).compareTo(claimsSummary.getIncurred()));
        Assert.assertEquals(2, claimsSummary.getClaims().size());
    }

    @Test
    public void testProcessPremiums() {
        ClaimsSummary claimsSummary = new ClaimsSummary(
                new BigDecimal(117_500_000), new BigDecimal(153_409_091), this.claims);
        LocalDate evaluationDate = LocalDate.of(2015, 8, 30);
        ContractSummary contractSummary = this.contract.processPremiums(claimsSummary, evaluationDate);

        Assert.assertEquals(0, new BigDecimal(5_000_000).compareTo(contractSummary.getPremiumsSummaryPaid().getTotalClaims()));
        Assert.assertEquals(0, new BigDecimal(40_909_091).compareTo(contractSummary.getPremiumsSummaryIncurred().getTotalClaims()));

        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryPaid().getReinstatementPremium()));
        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryIncurred().getReinstatementPremium()));

        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryPaid().getAdditionalPremium()));
        Assert.assertEquals(0, BigDecimal.ZERO.compareTo(contractSummary.getPremiumsSummaryIncurred().getAdditionalPremium()));

        Assert.assertEquals(0, new BigDecimal(16_875_000).compareTo(contractSummary.getPremiumsSummaryPaid().getDepositPremium()));
        Assert.assertEquals(0, new BigDecimal(16_875_000).compareTo(contractSummary.getPremiumsSummaryIncurred().getDepositPremium()));

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
    public void testProcessCumulativePositions() {
        ContractSummary contractSummary = new ContractSummary(
                new PremiumsSummary(new BigDecimal(5_000_000), BigDecimal.ZERO,
                        BigDecimal.ZERO, new BigDecimal(16_875_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                new PremiumsSummary(new BigDecimal(40_909_091), BigDecimal.ZERO,
                        BigDecimal.ZERO, new BigDecimal(16_875_000), BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO),
                null, LocalDate.of(2015, 8, 30));
        CumulativePositionsSummary cumulativePositionsSummary = this.contract.processCumulativePositions(contractSummary);

        Assert.assertEquals(BigDecimal.ZERO, cumulativePositionsSummary.getLoss().getCedant().getGross());
        Assert.assertEquals(new BigDecimal(-40_909_091), cumulativePositionsSummary.getLoss().getCedant().getCeded());
        Assert.assertEquals(new BigDecimal(-40_909_091), cumulativePositionsSummary.getLoss().getCedant().getNet());

        Assert.assertEquals(new BigDecimal(40_909_091), cumulativePositionsSummary.getLoss().getReinsurer().getGross());
        Assert.assertEquals(BigDecimal.ZERO, cumulativePositionsSummary.getLoss().getReinsurer().getCeded());
        Assert.assertEquals(new BigDecimal(40_909_091), cumulativePositionsSummary.getLoss().getReinsurer().getNet());

        Assert.assertEquals(BigDecimal.ZERO, cumulativePositionsSummary.getPremiumWritten().getCedant().getGross());
        Assert.assertEquals(new BigDecimal(-16_875_000), cumulativePositionsSummary.getPremiumWritten().getCedant().getCeded());
        Assert.assertEquals(new BigDecimal(-16_875_000), cumulativePositionsSummary.getPremiumWritten().getCedant().getNet());

        Assert.assertEquals(new BigDecimal(16_875_000), cumulativePositionsSummary.getPremiumWritten().getReinsurer().getGross());
        Assert.assertEquals(BigDecimal.ZERO, cumulativePositionsSummary.getPremiumWritten().getReinsurer().getCeded());
        Assert.assertEquals(new BigDecimal(16_875_000), cumulativePositionsSummary.getPremiumWritten().getReinsurer().getNet());

        Assert.assertEquals(BigDecimal.ZERO, cumulativePositionsSummary.getPremiumEarned().getCedant().getGross());
        Assert.assertEquals(new BigDecimal(-11_172_734), cumulativePositionsSummary.getPremiumEarned().getCedant().getCeded());
        Assert.assertEquals(new BigDecimal(-11_172_734), cumulativePositionsSummary.getPremiumEarned().getCedant().getNet());

        Assert.assertEquals(new BigDecimal(11_172_734), cumulativePositionsSummary.getPremiumEarned().getReinsurer().getGross());
        Assert.assertEquals(BigDecimal.ZERO, cumulativePositionsSummary.getPremiumEarned().getReinsurer().getCeded());
        Assert.assertEquals(new BigDecimal(11_172_734), cumulativePositionsSummary.getPremiumEarned().getReinsurer().getNet());

        Assert.assertEquals(0, BigDecimal.valueOf(0.662).compareTo(cumulativePositionsSummary.getLossOccurringDuring()));
        Assert.assertEquals(0, BigDecimal.valueOf(0.219).compareTo(cumulativePositionsSummary.getRisksAttaching()));
    }
}
