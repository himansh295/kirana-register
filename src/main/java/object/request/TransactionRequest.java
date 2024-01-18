package object.request;

import entity.User;
import enums.Currency;
import enums.TransactionType;
import lombok.Data;
import object.response.ProductRO;
import object.response.UserRO;

import java.util.function.DoubleUnaryOperator;

@Data
public class TransactionRequest {
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
    private ProductRO productRO;
}
