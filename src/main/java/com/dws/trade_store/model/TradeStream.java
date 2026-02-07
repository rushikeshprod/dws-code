package com.dws.trade_store.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "trade-stream")
public class TradeStream {

    @Id
    private String id;

    private String tradeId;
    private Integer version;
    private String bookId;
    private String counterPartyId;
    private LocalDateTime receivedTs;

    // Getters & Setters
}
