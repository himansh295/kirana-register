package com.example.transactions.object.response;

import com.example.transactions.enums.Currency;
import com.example.transactions.enums.TransactionStatus;
import com.example.transactions.enums.TransactionType;
import com.example.transactions.object.request.StoreRequest;
import lombok.Data;

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
