package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Nikita Gorbachevski
 */
public class Contract {

    private String cedant;
    private String reinsurer;
    private BigDecimal share;
    private LocalDate inceptionDate;
    private LocalDate expiryDate;
    private String basis;
    private String lineOfBusiness;
    private String peril;
    private String territory;
    private String currency;
    private Financial riskFinancial;
    private Financial eventFinancial;
    private Financial periodFinancial;
    private BigDecimal minimum;
    private BigDecimal deposit;
    private BigDecimal adjustableRate;
    private BigDecimal subjectPremiumFinal;
    private LocalDate subjectPremiumFinalAdjustedDate;
    private BigDecimal reinstatementPremium;
    private BigDecimal reinstatementPremiumPercentage;
    private BigDecimal additionalPremium;
    private BigDecimal profitCommission;
    private BigDecimal noClaimsBonus;
    private List<PaymentSchedule> paymentSchedules;
    private String status;

    public ClaimsSummary processClaims(List<Claim> claims) {
        claims = filter(claims);
        BigDecimal totalPaid = calculateTotal(claims, Claim::getPaid);
        BigDecimal totalIncurred = calculateTotal(claims, Claim::getIncurred);
        return new ClaimsSummary(totalPaid, totalIncurred, claims);
    }

    // TODO apply share
    public ContractSummary processPremiums(ClaimsSummary claimsSummary, LocalDate evaluationDate) {
        PremiumsSummary premiumsSummaryPaid = calculatePremiums(claimsSummary.getPaid(),
                date -> evaluationDate.compareTo(date) >= 0);
        PremiumsSummary premiumsSummaryIncurred = calculatePremiums(claimsSummary.getIncurred(),
                date -> true);
        return new ContractSummary(premiumsSummaryPaid, premiumsSummaryIncurred, claimsSummary, evaluationDate);
    }

    public CumulativePositionsSummary processCumulativePositions(ContractSummary contractSummary) {
        PremiumsSummary premiumsSummaryIncurred = contractSummary.getPremiumsSummaryIncurred();

        BigDecimal totalClaimsIncurred = premiumsSummaryIncurred.getTotalClaims();
        CumulativePosition loss = calculateCumulativePosition(totalClaimsIncurred);

        BigDecimal written = premiumsSummaryIncurred.getReinstatementPremium()
                .add(premiumsSummaryIncurred.getAdditionalPremium())
                .add(premiumsSummaryIncurred.getDepositPremium())
                .add(premiumsSummaryIncurred.getAdjustmentPremium())
                .subtract(premiumsSummaryIncurred.getProfitCommission())
                .subtract(premiumsSummaryIncurred.getNoClaimsBonus());
        CumulativePosition profitWritten = calculateCumulativePosition(written);

        LocalDate evaluationDate = contractSummary.getEvaluationDate();

        BigDecimal lossOccurringDuringPrecise = subtract(min(max(evaluationDate, this.inceptionDate), this.expiryDate), this.inceptionDate)
                .divide(subtract(this.expiryDate, this.inceptionDate), 10, BigDecimal.ROUND_HALF_EVEN);

        BigDecimal lossOccurringDuring = lossOccurringDuringPrecise.setScale(3, BigDecimal.ROUND_HALF_EVEN);

        BigDecimal val = BigDecimal.ZERO;
        if (evaluationDate.compareTo(this.expiryDate) >= 0) {
            val = subtract(this.expiryDate.plusDays(365), min(evaluationDate, this.expiryDate.plusDays(365)))
                    .divide(subtract(this.expiryDate, this.inceptionDate), 10, BigDecimal.ROUND_HALF_EVEN)
                    .pow(2)
                    .divide(new BigDecimal(2), 10, BigDecimal.ROUND_HALF_EVEN);
        }

        BigDecimal riskAttachingPrecise = lossOccurringDuringPrecise
                .pow(2)
                .divide(new BigDecimal(2), 10, BigDecimal.ROUND_HALF_EVEN)
                .add(val);
        BigDecimal riskAttaching = riskAttachingPrecise.setScale(3, BigDecimal.ROUND_HALF_EVEN);

        BigDecimal tmp;
        if (this.basis.equals("RA")) {
            tmp = riskAttachingPrecise;
        } else {
            tmp = lossOccurringDuringPrecise;
        }

        BigDecimal earned = premiumsSummaryIncurred.getReinstatementPremium()
                .add(premiumsSummaryIncurred.getAdditionalPremium())
                .add(premiumsSummaryIncurred.getDepositPremium()
                        .add(premiumsSummaryIncurred.getAdjustmentPremium())
                        .subtract(premiumsSummaryIncurred.getProfitCommission())
                        .subtract(premiumsSummaryIncurred.getNoClaimsBonus())
                        .multiply(tmp))
                .setScale(0, BigDecimal.ROUND_HALF_EVEN);
        CumulativePosition profitEarned = calculateCumulativePosition(earned);

        return new CumulativePositionsSummary(loss, profitWritten, profitEarned, lossOccurringDuring, riskAttaching);
    }

    BigDecimal subtract(LocalDate d1, LocalDate d2) {
        return new BigDecimal(d1.toEpochDay() - d2.toEpochDay());
    }

    LocalDate max(LocalDate... dates) {
        LocalDate max = LocalDate.MIN;
        for (LocalDate date : dates) {
            if (date.compareTo(max) > 0) {
                max = date;
            }
        }
        return max;
    }

    LocalDate min(LocalDate... dates) {
        LocalDate min = LocalDate.MAX;
        for (LocalDate date : dates) {
            if (date.compareTo(min) < 0) {
                min = date;
            }
        }
        return min;
    }

    CumulativePosition calculateCumulativePosition(BigDecimal amount) {
        GrossCededNet cedant = new GrossCededNet(BigDecimal.ZERO, amount.negate(), amount.negate());
        GrossCededNet reinsurer = new GrossCededNet(amount, BigDecimal.ZERO, amount);
        return new CumulativePosition(cedant, reinsurer);
    }

    BigDecimal calculateTotal(List<Claim> claims, Function<Claim, BigDecimal> function) {
        List<ClaimCalculation> claimCalculations = transform(claims, function);

        claimCalculations = convertCurrency(claimCalculations);

        claimCalculations = group(claimCalculations, "risk");
        claimCalculations = applyLimits(claimCalculations, "risk");

        claimCalculations = group(claimCalculations, "event");
        claimCalculations = applyLimits(claimCalculations, "event");

        // before period limit
        return claimCalculations.stream()
                .map(ClaimCalculation::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(0, BigDecimal.ROUND_HALF_EVEN);
    }

    List<ClaimCalculation> convertCurrency(List<ClaimCalculation> ccs) {
        return ccs.stream()
                .map(cc -> new ClaimCalculation(cc.getClaim(), CurrencyConversion.getInstance().convert(
                        cc.getClaim().getCurrency(), this.currency, cc.getAmount())))
                .collect(Collectors.toList());
    }

    List<ClaimCalculation> transform(List<Claim> claims, Function<Claim, BigDecimal> function) {
        return claims.stream()
                .map(c -> new ClaimCalculation(c, function.apply(c)))
                .collect(Collectors.toList());
    }

    List<Claim> filter(List<Claim> claims) {
        return claims.stream()
                .filter(c -> c.getStatus().equals("APPROVED")
                        && this.cedant.equals(c.getCedant())
                        && dateInRange(c)
                        && (this.lineOfBusiness.equals("All") || this.lineOfBusiness.equals(c.getLineOfBusiness()))
                        && (this.peril.equals("All") || this.peril.equals(c.getPeril()))
                        && (this.territory.equals("Worldwide") || this.territory.equals(c.getTerritory())))
                .collect(Collectors.toList());
    }

    boolean dateInRange(Claim c) {
        LocalDate date;
        switch (this.basis) {
            case "RA":
                date = c.getPolicyInceptionDate();
                break;
            case "LOD":
                date = c.getLossDate();
                break;
            case "CM":
                date = c.getReportDate();
                break;
            default:
                throw new RuntimeException("Unknown basis=" + this.basis);
        }
        return this.inceptionDate.compareTo(date) <= 0 && this.expiryDate.compareTo(date) >= 0;
    }

    List<ClaimCalculation> group(List<ClaimCalculation> ccs, String type) {
        return ccs.stream()
                .collect(Collectors.groupingBy(cc -> {
                    switch (type) {
                        case "risk":
                            return cc.getClaim().getClaimId();
                        case "event":
                            return cc.getClaim().getEventId();
                        default:
                            throw new RuntimeException("Unknown group type=" + type);
                    }
                }))
                .entrySet().stream()
                .map(e -> e.getValue().stream().reduce(ClaimCalculation::new))
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    List<ClaimCalculation> applyLimits(List<ClaimCalculation> ccs, String type) {
        return ccs.stream()
                .map(cc -> new ClaimCalculation(cc.getClaim(), applyLimits(cc.getAmount(), type)))
                .collect(Collectors.toList());
    }

    BigDecimal applyLimits(BigDecimal amount, String type) {
        Financial financial;
        switch (type) {
            case "risk":
                financial = this.riskFinancial;
                break;
            case "event":
                financial = this.eventFinancial;
                break;
            case "period":
                financial = this.periodFinancial;
                break;
            default:
                throw new RuntimeException("Unknown apply limits type=" + type);
        }

        switch (financial.getPriorityType()) {
            case "Franch":
                // equal or greater
                if (amount.compareTo(financial.getPriority()) >= 0) {
                    return financial.getLimit().min(amount);
                } else {
                    return BigDecimal.ZERO;
                }
            case "Ded":
                // equal or greater
                if (amount.compareTo(financial.getPriority()) >= 0) {
                    return financial.getLimit().min(amount.subtract(financial.getPriority()));
                } else {
                    return BigDecimal.ZERO;
                }
            default:
                throw new RuntimeException("Unknown per " + type + " priority type=" + financial.getPriorityType());
        }
    }

    PremiumsSummary calculatePremiums(BigDecimal totalClaimsBeforePeriodLimits,
                                      Predicate<LocalDate> evaluationDatePredicate) {
        // total claims after period
        BigDecimal totalClaims = applyLimits(totalClaimsBeforePeriodLimits, "period");

        BigDecimal reinstatementPremium = calculateReinstatementPremium(totalClaims);

        BigDecimal additionalPremium = calculateAdditionalPremium(totalClaims);

        BigDecimal depositPremium = calculateDepositPremium(evaluationDatePredicate);

        BigDecimal adjustmentPremium = calculateAdjustmentPremium(evaluationDatePredicate);

        BigDecimal profitCommission = calculateProfitCommission(reinstatementPremium, additionalPremium,
                depositPremium, adjustmentPremium, totalClaims);

        BigDecimal noClaimsBonus = calculateNoClaimsBonus(totalClaims, depositPremium, additionalPremium);

        return new PremiumsSummary(totalClaims, reinstatementPremium, additionalPremium, depositPremium,
                adjustmentPremium, profitCommission, noClaimsBonus);
    }

    BigDecimal calculateReinstatementPremium(BigDecimal amount) {
        BigDecimal limit;
        // not unlimited
        if (riskFinancial.getLimit().compareTo(new BigDecimal(Long.MAX_VALUE)) < 0) {
            limit = riskFinancial.getLimit();
        } else if (eventFinancial.getLimit().compareTo(new BigDecimal(Long.MAX_VALUE)) < 0) {
            limit = eventFinancial.getLimit();
        } else {
            return BigDecimal.ZERO;
        }

        return reinstatementPremium.min(amount.divide(limit, 10, BigDecimal.ROUND_HALF_EVEN))
                .multiply(reinstatementPremiumPercentage)
                .multiply(deposit)
                // round to the closest integer
                .setScale(0, BigDecimal.ROUND_HALF_EVEN);
    }

    BigDecimal calculateAdditionalPremium(BigDecimal amount) {
        return amount.multiply(this.additionalPremium);
    }

    BigDecimal calculateDepositPremium(Predicate<LocalDate> predicate) {
        return paymentSchedules.stream()
                .filter(ps -> predicate.test(ps.getDate()))
                .map(PaymentSchedule::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    BigDecimal calculateAdjustmentPremium(Predicate<LocalDate> predicate) {
        if (predicate.test(this.subjectPremiumFinalAdjustedDate)) {
            return minimum.max(adjustableRate.multiply(subjectPremiumFinal)).subtract(deposit);
        }
        return BigDecimal.ZERO;
    }

    BigDecimal calculateProfitCommission(BigDecimal reinstatementPremium, BigDecimal additionalPremium,
                                         BigDecimal depositPremium, BigDecimal adjustmentPremium,
                                         BigDecimal totalClaims) {
        return this.profitCommission.multiply(
                BigDecimal.ZERO.max(reinstatementPremium
                        .add(additionalPremium)
                        .add(depositPremium)
                        .add(adjustmentPremium)
                        .subtract(totalClaims)));
    }

    BigDecimal calculateNoClaimsBonus(BigDecimal totalClaims, BigDecimal depositPremium, BigDecimal additionalPremium) {
        BigDecimal noClaimsBonus = BigDecimal.ZERO;
        if (totalClaims.compareTo(BigDecimal.ZERO) == 0) {
            noClaimsBonus = noClaimsBonus.multiply(depositPremium.add(additionalPremium));
        }
        return noClaimsBonus;
    }

    public String getCedant() {
        return cedant;
    }

    public void setCedant(String cedant) {
        this.cedant = cedant;
    }

    public String getReinsurer() {
        return reinsurer;
    }

    public void setReinsurer(String reinsurer) {
        this.reinsurer = reinsurer;
    }

    public BigDecimal getShare() {
        return share;
    }

    public void setShare(BigDecimal share) {
        this.share = share;
    }

    public LocalDate getInceptionDate() {
        return inceptionDate;
    }

    public void setInceptionDate(LocalDate inceptionDate) {
        this.inceptionDate = inceptionDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getBasis() {
        return basis;
    }

    public void setBasis(String basis) {
        this.basis = basis;
    }

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public String getPeril() {
        return peril;
    }

    public void setPeril(String peril) {
        this.peril = peril;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Financial getRiskFinancial() {
        return riskFinancial;
    }

    public void setRiskFinancial(Financial riskFinancial) {
        this.riskFinancial = riskFinancial;
    }

    public Financial getEventFinancial() {
        return eventFinancial;
    }

    public void setEventFinancial(Financial eventFinancial) {
        this.eventFinancial = eventFinancial;
    }

    public Financial getPeriodFinancial() {
        return periodFinancial;
    }

    public void setPeriodFinancial(Financial periodFinancial) {
        this.periodFinancial = periodFinancial;
    }

    public BigDecimal getMinimum() {
        return minimum;
    }

    public void setMinimum(BigDecimal minimum) {
        this.minimum = minimum;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getAdjustableRate() {
        return adjustableRate;
    }

    public void setAdjustableRate(BigDecimal adjustableRate) {
        this.adjustableRate = adjustableRate;
    }

    public BigDecimal getSubjectPremiumFinal() {
        return subjectPremiumFinal;
    }

    public void setSubjectPremiumFinal(BigDecimal subjectPremiumFinal) {
        this.subjectPremiumFinal = subjectPremiumFinal;
    }

    public LocalDate getSubjectPremiumFinalAdjustedDate() {
        return subjectPremiumFinalAdjustedDate;
    }

    public void setSubjectPremiumFinalAdjustedDate(LocalDate subjectPremiumFinalAdjustedDate) {
        this.subjectPremiumFinalAdjustedDate = subjectPremiumFinalAdjustedDate;
    }

    public BigDecimal getReinstatementPremium() {
        return reinstatementPremium;
    }

    public void setReinstatementPremium(BigDecimal reinstatementPremium) {
        this.reinstatementPremium = reinstatementPremium;
    }

    public BigDecimal getReinstatementPremiumPercentage() {
        return reinstatementPremiumPercentage;
    }

    public void setReinstatementPremiumPercentage(BigDecimal reinstatementPremiumPercentage) {
        this.reinstatementPremiumPercentage = reinstatementPremiumPercentage;
    }

    public BigDecimal getAdditionalPremium() {
        return additionalPremium;
    }

    public void setAdditionalPremium(BigDecimal additionalPremium) {
        this.additionalPremium = additionalPremium;
    }

    public BigDecimal getProfitCommission() {
        return profitCommission;
    }

    public void setProfitCommission(BigDecimal profitCommission) {
        this.profitCommission = profitCommission;
    }

    public BigDecimal getNoClaimsBonus() {
        return noClaimsBonus;
    }

    public void setNoClaimsBonus(BigDecimal noClaimsBonus) {
        this.noClaimsBonus = noClaimsBonus;
    }

    public List<PaymentSchedule> getPaymentSchedules() {
        return paymentSchedules;
    }

    public void setPaymentSchedules(List<PaymentSchedule> paymentSchedules) {
        this.paymentSchedules = paymentSchedules;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
