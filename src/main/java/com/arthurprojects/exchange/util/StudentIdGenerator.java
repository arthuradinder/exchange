package com.arthurprojects.exchange.util;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StudentIdGenerator {
    private static final String ID_PREFIX = "STU";
    private static final String ID_FORMAT = "STU%03d";
    private final AtomicInteger sequence = new AtomicInteger(0);

    @PostConstruct
    public void init() {
        // Initialize the sequence
        sequence.set(0);
    }
    public String generateStudentId() {
        int nextValue = sequence.incrementAndGet();
        return String.format(ID_FORMAT, nextValue);
    }
}
