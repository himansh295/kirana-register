package enums;

public enum TransactionStatus {
    SUCCESS(2),
    PENDING(1),
    FAILED(0);

    private final int value;

    TransactionStatus(final int value) {this.value = value;}

    public int getValue() {
        return value;
    }
}
