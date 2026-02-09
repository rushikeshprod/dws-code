package com.dws.trade_store.service;

import com.dws.trade_store.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TradeConsumer {

    @Autowired
    private TradeService tradeService;

    // Listens to a Kafka topic - demonstrates Streaming capability
    @KafkaListener(topics = "trade_ingestion_topic", groupId = "trade_store_group")
    public void consumeTrade(Trade trade) {
        tradeService.processTrade(trade);
    }
}