package util;

import entity.Transaction;
import object.response.TransactionRO;

public class TransactionUtil {

    private TransactionUtil() {

    }

    public static TransactionRO getTransactionROUsingTransaction(Transaction transaction) {
        TransactionRO transactionRO = new TransactionRO();
        transactionRO.setTransactionId(transaction.getId());
        transactionRO.setCustomerName(transaction.getCustomerName());
        transactionRO.setCustomerNumber(transaction.getCustomerNumber());
        transactionRO.setStoreId(transaction.getStoreId());
        transactionRO.setUserId(transaction.getUserId());
        transactionRO.setProductId(transaction.getProductId());
        transactionRO.setAmount(transaction.getAmount());
        transactionRO.setTransactionType(transaction.getTransactionType());
        transactionRO.setStatus(transaction.getStatus());
        return transactionRO;
    }
}
