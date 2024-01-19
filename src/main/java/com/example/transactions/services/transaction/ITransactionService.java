package com.example.transactions.services.transaction;

import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.request.TransactionRequest;
import com.example.transactions.object.response.TransactionRO;

import java.io.IOException;

public interface ITransactionService {
    TransactionRO addNewTransaction(TransactionRequest transactionRequest) throws ValidationException, IOException;
}
