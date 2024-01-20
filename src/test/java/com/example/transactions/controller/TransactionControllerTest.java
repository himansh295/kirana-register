package com.example.transactions.controller;

import com.example.transactions.enums.Currency;
import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.ControllerBaseResponse;
import com.example.transactions.object.request.TransactionRequest;
import com.example.transactions.object.response.TransactionRO;
import com.example.transactions.services.transaction.ITransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private ITransactionService transactionService;

    @Test
    public void testAddNewTransactionSuccess() throws IOException, ValidationException {
        // Mocking data
        TransactionRequest mockRequest = new TransactionRequest();
        mockRequest.setCustomerName("Alex");
        mockRequest.setCustomerNumber(48L);
        mockRequest.setStoreId(1L);
        mockRequest.setProductId(1L);
        mockRequest.setAmount(400.0);
        mockRequest.setCurrency(Currency.INR);

        TransactionRO mockTransaction = new TransactionRO();
        mockTransaction.setCustomerName(mockRequest.getCustomerName());
        mockTransaction.setCustomerNumber(mockTransaction.getCustomerNumber());
        mockTransaction.setStoreId(1L);
        mockTransaction.setProductId(1L);
        mockTransaction.setAmount(400.0);

        when(transactionService.addNewTransaction(mockRequest)).thenReturn(mockTransaction);
        ResponseEntity<ControllerBaseResponse> responseEntity = transactionController.addNewTransaction(mockRequest);
        assertEquals(200, responseEntity.getStatusCodeValue());

        TransactionRO responseTransaction = (TransactionRO) responseEntity.getBody().getData();
        assertEquals(mockTransaction, responseTransaction);
    }

}
