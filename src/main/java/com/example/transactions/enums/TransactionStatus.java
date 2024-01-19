package com.example.transactions.enums;

public enum TransactionStatus {
    FAILED(0),
    PENDING(1),
    SUCCESS(2);

    private final int value;

    TransactionStatus(final int value) {this.value = value;}

    public int getValue() {
        return value;
    }
}
