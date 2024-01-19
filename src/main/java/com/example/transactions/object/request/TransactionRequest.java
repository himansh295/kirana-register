package com.example.transactions.object.request;

import com.example.transactions.enums.Currency;
import com.example.transactions.enums.TransactionType;
import com.example.transactions.object.response.ProductRO;
import lombok.Data;
import com.example.transactions.object.response.UserRO;

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
