package com.dws.trade_store.scheduler;

import com.dws.trade_store.model.Trade;
import com.dws.trade_store.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
@EnableScheduling
@Component
public class TradeExpiryScheduler {

    @Autowired
    private TradeRepository tradeRepository;

    // Runs daily at midnight
//   @Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(fixedRate = 10000)
    public void updateExpiredTrades() {

        List<Trade> trades = tradeRepository.findAll();

        for (Trade trade : trades) {
            if (trade.getMaturityDate().isBefore(LocalDate.now())) {
                trade.setExpired(true);
                tradeRepository.save(trade);
            }
        }
    }
}
