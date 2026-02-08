package com.dws.trade_store.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TradeEventRepository
        extends MongoRepository<TradeEvent, String> {
}
