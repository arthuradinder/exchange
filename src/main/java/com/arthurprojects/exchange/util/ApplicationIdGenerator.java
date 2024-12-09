package com.arthurprojects.exchange.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ApplicationIdGenerator {
    private static final String ID_FORMAT = "APP%03d";
    private final AtomicInteger sequence = new AtomicInteger(0);

    public String generateApplicationId() {
        int nextValue = sequence.incrementAndGet();
        return String.format(ID_FORMAT, nextValue);
    }
}
