package com.dws.trade_store.scheduler;

import com.dws.trade_store.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class TradeExpiryScheduler {

    @Autowired
    private TradeRepository tradeRepository;

    // Runs daily at midnight to update all expired trades at once
    @Scheduled(cron = "0 0 0 * * ?")
    public void updateExpiredTrades() {
        tradeRepository.updateExpiredTrades(LocalDate.now());
    }
}