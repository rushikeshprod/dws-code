package com.dws.trade_store.repository;

import com.dws.trade_store.model.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

public interface TradeRepository extends JpaRepository<Trade, String> {

    Optional<Trade> findTopByTradeIdOrderByVersionDesc(String tradeId);

    // This is the missing method the compiler is looking for
    @Modifying
    @Transactional
    @Query("UPDATE Trade t SET t.expired = true WHERE t.maturityDate < :today AND t.expired = false")
    void updateExpiredTrades(@Param("today") LocalDate today);
}