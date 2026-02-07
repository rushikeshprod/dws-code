package com.dws.trade_store.service;

import com.dws.trade_store.model.Trade;
import com.dws.trade_store.mongo.TradeEventRepository;
import com.dws.trade_store.repository.TradeRepository;
import com.dws.trade_store.validation.TradeValidationService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TradeServiceTest {

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private TradeValidationService validationService;

    // ⭐ ADD THIS
    @Mock
    private TradeEventRepository tradeEventRepository;

    @InjectMocks
    private TradeService tradeService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldSaveValidTrade() {

        Trade trade = new Trade();
        trade.setTradeId("T1");
        trade.setVersion(1);
        trade.setMaturityDate(LocalDate.now().plusDays(10));

        when(tradeRepository
            .findTopByTradeIdOrderByVersionDesc("T1"))
            .thenReturn(Optional.empty());

        when(tradeRepository.save(any()))
            .thenReturn(trade);

        Trade saved = tradeService.processTrade(trade);

        assertNotNull(saved);

        verify(tradeRepository, times(1)).save(trade);

        // ⭐ verify Mongo also called
        verify(tradeEventRepository, times(1)).save(any());
    }
}
