package com.dws.trade_store.controller;

import com.dws.trade_store.model.Trade;
import com.dws.trade_store.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    // Create / Process Trade
    @PostMapping
    public Trade processTrade(@RequestBody Trade trade) {
        return tradeService.processTrade(trade);
    }

    // Simple health check
    @GetMapping("/ping")
    public String ping() {
        return "Trade Store Running";
    }
}
