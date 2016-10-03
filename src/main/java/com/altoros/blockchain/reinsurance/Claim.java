package com.altoros.blockchain.reinsurance;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Nikita Gorbachevski
 */
public class Claim {

    private String cedant;
    private String lineOfBusiness;
    private String territory;
    private String peril;
    private String claimId;
    private long policyId;
    private long eventId;
    private LocalDate policyInceptionDate;
    private LocalDate lossDate;
    private LocalDate reportDate;
    private BigDecimal paid;
    private BigDecimal reserved;
    private BigDecimal incurred;
    private String currency;
    private String status;

    public String getCedant() {
        return cedant;
    }

    public void setCedant(String cedant) {
        this.cedant = cedant;
    }

    public String getLineOfBusiness() {
        return lineOfBusiness;
    }

    public void setLineOfBusiness(String lineOfBusiness) {
        this.lineOfBusiness = lineOfBusiness;
    }

    public String getTerritory() {
        return territory;
    }

    public void setTerritory(String territory) {
        this.territory = territory;
    }

    public String getPeril() {
        return peril;
    }

    public void setPeril(String peril) {
        this.peril = peril;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(long policyId) {
        this.policyId = policyId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public LocalDate getPolicyInceptionDate() {
        return policyInceptionDate;
    }

    public void setPolicyInceptionDate(LocalDate policyInceptionDate) {
        this.policyInceptionDate = policyInceptionDate;
    }

    public LocalDate getLossDate() {
        return lossDate;
    }

    public void setLossDate(LocalDate lossDate) {
        this.lossDate = lossDate;
    }

    public LocalDate getReportDate() {
        return reportDate;
    }

    public void setReportDate(LocalDate reportDate) {
        this.reportDate = reportDate;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public BigDecimal getReserved() {
        return reserved;
    }

    public void setReserved(BigDecimal reserved) {
        this.reserved = reserved;
    }

    public BigDecimal getIncurred() {
        return incurred;
    }

    public void setIncurred(BigDecimal incurred) {
        this.incurred = incurred;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
