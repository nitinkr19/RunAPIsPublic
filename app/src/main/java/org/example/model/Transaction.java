package org.example.model;

import java.math.BigDecimal;
import java.time.Instant;

public class Transaction {

    private final long fromAccountId;
    private final long toAccountId;
    private final BigDecimal amount;
    private final Instant timestamp;

    public Transaction(long fromAccountId, long toAccountId, BigDecimal amount, Instant timestamp) {
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public long getFromAccountId() {
        return fromAccountId;
    }

    public long getToAccountId() {
        return toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
