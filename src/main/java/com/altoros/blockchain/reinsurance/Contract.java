package com.altoros.blockchain.reinsurance;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nikita Gorbachevski
 */
public class Contract {
    private String cedant;
    private String reinsurer;
    private double share;
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
    private String priorityType;
    private long minimum;
    private long deposit;
    private double adjustableRate;
    private long subjectPremiumFinal;
    private long reinstatementPremium;
    private double reinstatementPremiumPercentage;
    private double additionalPremium;
    private double profitCommission;
    private double noClaimsBonus;
    private List<PaymentSchedule> paymentSchedules;
    private String status;

    public Object process(List<Claim> claims) {
        claims = filter(claims);
        return null;
    }

    List<Claim> filter(List<Claim> claims) {
        return claims.stream()
                .filter(c -> this.cedant.equals(c.getCedant())
                        && checkDate(c)
                        && (this.lineOfBusiness.equals("All") || this.lineOfBusiness.equals(c.getLineOfBusiness()))
                        && (this.peril.equals("All") || this.peril.equals(c.getPeril()))
                        && (this.territory.equals("Worldwide") || this.territory.equals(c.getTerritory())))
                .collect(Collectors.toList());
    }

    boolean checkDate(Claim c) {
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
        return (this.inceptionDate.isEqual(date) || this.inceptionDate.isBefore(date))
                && (this.expiryDate.isEqual(date) || this.expiryDate.isAfter(date));
    }

    // TODO replace with Builder
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

    public double getShare() {
        return share;
    }

    public void setShare(double share) {
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

    public String getPriorityType() {
        return priorityType;
    }

    public void setPriorityType(String priorityType) {
        this.priorityType = priorityType;
    }

    public long getMinimum() {
        return minimum;
    }

    public void setMinimum(long minimum) {
        this.minimum = minimum;
    }

    public long getDeposit() {
        return deposit;
    }

    public void setDeposit(long deposit) {
        this.deposit = deposit;
    }

    public double getAdjustableRate() {
        return adjustableRate;
    }

    public void setAdjustableRate(double adjustableRate) {
        this.adjustableRate = adjustableRate;
    }

    public long getSubjectPremiumFinal() {
        return subjectPremiumFinal;
    }

    public void setSubjectPremiumFinal(long subjectPremiumFinal) {
        this.subjectPremiumFinal = subjectPremiumFinal;
    }

    public long getReinstatementPremium() {
        return reinstatementPremium;
    }

    public void setReinstatementPremium(long reinstatementPremium) {
        this.reinstatementPremium = reinstatementPremium;
    }

    public double getReinstatementPremiumPercentage() {
        return reinstatementPremiumPercentage;
    }

    public void setReinstatementPremiumPercentage(double reinstatementPremiumPercentage) {
        this.reinstatementPremiumPercentage = reinstatementPremiumPercentage;
    }

    public double getAdditionalPremium() {
        return additionalPremium;
    }

    public void setAdditionalPremium(double additionalPremium) {
        this.additionalPremium = additionalPremium;
    }

    public double getProfitCommission() {
        return profitCommission;
    }

    public void setProfitCommission(double profitCommission) {
        this.profitCommission = profitCommission;
    }

    public double getNoClaimsBonus() {
        return noClaimsBonus;
    }

    public void setNoClaimsBonus(double noClaimsBonus) {
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
