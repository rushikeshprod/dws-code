package com.dws.trade_store.service;

import com.dws.trade_store.mongo.TradeEvent;
import com.dws.trade_store.mongo.TradeEventRepository;
import com.dws.trade_store.model.Trade;
import com.dws.trade_store.repository.TradeRepository;
import com.dws.trade_store.validation.TradeValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeValidationService validationService;

    @Autowired
    private TradeEventRepository tradeEventRepository;   // ✅ inside class

    public Trade processTrade(Trade trade) {

        if (trade.getCreatedDate() == null) {
            trade.setCreatedDate(LocalDate.now());
        }

        Trade existing =
            tradeRepository
                .findTopByTradeIdOrderByVersionDesc(trade.getTradeId())
                .orElse(null);

        validationService.validateTrade(trade, existing);

        Trade saved = tradeRepository.save(trade);

        TradeEvent event = new TradeEvent();
        event.setTradeId(saved.getTradeId());
        event.setVersion(saved.getVersion());
        event.setCreatedDate(saved.getCreatedDate());
        event.setMaturityDate(saved.getMaturityDate());
        event.setExpired(saved.isExpired());

        tradeEventRepository.save(event);

        return saved;
    }
}
