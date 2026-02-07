package com.dws.trade_store.service;


import com.dws.trade_store.model.Trade;
import com.dws.trade_store.repository.TradeRepository;
import com.dws.trade_store.validation.TradeValidationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private TradeValidationService validationService;

    @InjectMocks
    private TradeService tradeService;

    private Trade trade;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        trade = new Trade();
        trade.setTradeId("T1");
        trade.setVersion(1);
        trade.setMaturityDate(LocalDate.now().plusDays(10));
    }

    // ✅ Case 1 — Valid trade saves
    @Test
    void shouldSaveValidTrade() {

        when(tradeRepository.save(any()))
                .thenReturn(trade);

        Trade saved = tradeService.processTrade(trade);

        assertNotNull(saved);
        verify(tradeRepository, times(1)).save(trade);
    }

    // ❌ Case 2 — Lower version rejected
    @Test
    void shouldRejectLowerVersionTrade() {

        Trade existing = new Trade();
        existing.setTradeId("T1");
        existing.setVersion(2);

        when(tradeRepository
                .findTopByTradeIdOrderByVersionDesc("T1"))
                .thenReturn(Optional.of(existing));

        doThrow(new RuntimeException("Lower version"))
                .when(validationService)
                .validateTrade(any(), any());

        assertThrows(RuntimeException.class,
                () -> tradeService.processTrade(trade));
    }

    // ❌ Case 3 — Past maturity rejected
    @Test
    void shouldRejectPastMaturityTrade() {

        trade.setMaturityDate(LocalDate.now().minusDays(1));

        doThrow(new RuntimeException("Past maturity"))
                .when(validationService)
                .validateTrade(any(), any());

        assertThrows(RuntimeException.class,
                () -> tradeService.processTrade(trade));
    }
}
