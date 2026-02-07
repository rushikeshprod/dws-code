package com.dws.trade_store.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trade")
public class Trade {

    @Id
    private String tradeId;

    private Integer version;

    private LocalDate createdDate;

    private LocalDate maturityDate;

    private boolean expired;

    // ===== Getters & Setters =====

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(LocalDate maturityDate) {
        this.maturityDate = maturityDate;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}
