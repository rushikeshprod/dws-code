package com.dws.trade_store.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupLogger {

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        String port = "8082"; // Matches your application.properties
        System.out.println("\n" + "=".repeat(50));
        System.out.println("TRADE STORE APPLICATION IS READY");
        System.out.println("=".repeat(50));
        System.out.println("REST API Endpoints:");
        System.out.println("- Health Check:  http://localhost:" + port + "/trades/ping");
        System.out.println("- Trade Ingest:  http://localhost:" + port + "/trades (POST)");
        System.out.println("\nDatabase Consoles:");
        System.out.println("- H2 (SQL):      http://localhost:" + port + "/h2");
        System.out.println("  (JDBC URL: jdbc:h2:mem:trade-db)");
        System.out.println("\nStreaming Configuration:");
        System.out.println("- Kafka Broker:  localhost:9092");
        System.out.println("- Mongo Audit:   localhost:27017");
        System.out.println("=".repeat(50) + "\n");
    }
}