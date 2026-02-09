package com.dws.trade_store.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "trade")
public class Trade {

    @Id
    private String tradeId;

    private Integer version;

    // Technical version for Optimistic Locking (prevents race conditions)
    @Version
    private Integer internalVersion;

    private String counterPartyId;

    private String bookId;

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

    public Integer getInternalVersion() {
        return internalVersion;
    }

    public void setInternalVersion(Integer internalVersion) {
        this.internalVersion = internalVersion;
    }

    public String getCounterPartyId() {
        return counterPartyId;
    }

    public void setCounterPartyId(String counterPartyId) {
        this.counterPartyId = counterPartyId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
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