package com.dws.trade_store.exception;

public class LowerVersionTradeException extends RuntimeException {

    public LowerVersionTradeException(String message) {
        super(message);
    }
}