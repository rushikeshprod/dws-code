package com.dws.trade_store.service;

import com.dws.trade_store.model.Trade;
import com.dws.trade_store.repository.TradeRepository;
import com.dws.trade_store.validation.TradeValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class TradeService {

    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeValidationService validationService;

    public Trade processTrade(Trade trade) {

        if (trade.getCreatedDate() == null) {
            trade.setCreatedDate(LocalDate.now());
        }

        Trade existing =
            tradeRepository
                .findTopByTradeIdOrderByVersionDesc(trade.getTradeId())
                .orElse(null);

        validationService.validateTrade(trade, existing);

        return tradeRepository.save(trade);
    }
}
