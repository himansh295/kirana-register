package com.example.transactions.enums;

public enum Status {
    INACTIVE(0),
    ACTIVE(1);

    private final int value;

    Status(final int value) {this.value = value;}

    public int getValue() {
        return value;
    }
}
