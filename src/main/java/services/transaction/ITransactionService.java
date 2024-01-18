package services.transaction;

import exception.ValidationException;
import object.request.TransactionRequest;
import object.response.TransactionRO;

import java.io.IOException;

public interface ITransactionService {
    TransactionRO addNewTransaction(TransactionRequest transactionRequest) throws ValidationException, IOException;
}
