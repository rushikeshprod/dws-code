package com.dws.trade_store.repository;

import com.dws.trade_store.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TradeRepository
        extends JpaRepository<Trade, String> {

    Optional<Trade>
    findTopByTradeIdOrderByVersionDesc(String tradeId);
}
	