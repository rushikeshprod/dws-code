package com.dws.trade_store.validation;

import com.dws.trade_store.exception.InvalidMaturityDateException;
import com.dws.trade_store.exception.LowerVersionTradeException;
import com.dws.trade_store.model.Trade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class TradeValidationService {

    public void validateTrade(Trade incoming, Trade existing) {

        // Rule 2 — Reject maturity date < today
        if (incoming.getMaturityDate() == null ||
            incoming.getMaturityDate().isBefore(LocalDate.now())) {

            throw new InvalidMaturityDateException(
                "Trade maturity date is before today"
            );
        }

        // Rule 1 — Reject lower version
        if (existing != null &&
            incoming.getVersion() != null &&
            existing.getVersion() != null &&
            incoming.getVersion() < existing.getVersion()) {

            throw new LowerVersionTradeException(
                "Incoming trade version is lower than existing"
            );
        }
    }
}
