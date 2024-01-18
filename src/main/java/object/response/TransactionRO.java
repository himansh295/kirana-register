package object.response;

import enums.Currency;
import enums.TransactionStatus;
import enums.TransactionType;
import lombok.Data;
import object.request.StoreRequest;

@Data
public class TransactionRO {
    private Long transactionId;
    private String customerName;
    private Long customerNumber;
    private Long storeId;
    private Long userId;
    private Long productId;
    private TransactionType transactionType;
    private Double amount;
    private Currency currency;
    private StoreRequest store;
    private UserRO user;
    private ProductRO product;
    private TransactionStatus status;
}
