package com.example.transactions.services.transaction;

import com.example.transactions.dao.ITransactionRepository;
import com.example.transactions.enums.*;
import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.request.StoreRequest;
import com.example.transactions.object.request.TransactionRequest;
import com.example.transactions.object.response.CategoryRO;
import com.example.transactions.object.response.ProductRO;
import com.example.transactions.object.response.TransactionRO;
import com.example.transactions.object.response.UserRO;
import com.example.transactions.services.product.IProductService;
import com.example.transactions.services.store.IStoreService;
import com.example.transactions.services.transaction.impl.TransactionServiceImpl;
import com.example.transactions.services.user.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockIgnore("javax.management.*")
public class TransactionServiceTest {

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Mock
    private IStoreService storeService;

    @Mock
    private IProductService productService;

    @Mock
    private IUserService userService;

    @Mock
    private ITransactionRepository transactionRepository;

    @Test
    public void testAddNewTransactionINRSuccess() throws ValidationException, IOException {
        TransactionRequest mockRequest = new TransactionRequest();
        mockRequest.setCustomerName("John Doe");
        mockRequest.setCustomerNumber(123456789L);
        mockRequest.setStoreId(1L);
        mockRequest.setUserId(1L);
        mockRequest.setProductId(1L);
        mockRequest.setTransactionType(TransactionType.CREDIT);
        mockRequest.setAmount(100.0);
        mockRequest.setCurrency(Currency.INR);

        TransactionRO mockTransactionRO = new TransactionRO();
        mockTransactionRO.setTransactionId(123L);
        mockTransactionRO.setCustomerName(mockRequest.getCustomerName());
        mockTransactionRO.setCustomerNumber(mockRequest.getCustomerNumber());
        mockTransactionRO.setStoreId(mockRequest.getStoreId());
        mockTransactionRO.setUserId(mockRequest.getUserId());
        mockTransactionRO.setProductId(mockRequest.getProductId());
        mockTransactionRO.setTransactionType(mockRequest.getTransactionType());
        mockTransactionRO.setAmount(mockRequest.getAmount());

        StoreRequest sampleStoreRequest = new StoreRequest();
        sampleStoreRequest.setId(1L);
        sampleStoreRequest.setLocation("Sample Location");
        sampleStoreRequest.setName("Sample Store");
        sampleStoreRequest.setOwnerId(101L);
        sampleStoreRequest.setOwnerName("John Owner");

        CategoryRO sampleCategoryRO = new CategoryRO();
        sampleCategoryRO.setId(1L);
        sampleCategoryRO.setName("Sample Category");
        sampleCategoryRO.setStatus(Status.ACTIVE);

        ProductRO sampleProductRO = new ProductRO();
        sampleProductRO.setId(1L);
        sampleProductRO.setName("Sample Product");
        sampleProductRO.setCategoryRO(sampleCategoryRO);
        sampleProductRO.setStatus(Status.ACTIVE);
        sampleProductRO.setPrice(50.0);
        sampleProductRO.setCurrency(Currency.INR);

        UserRO sampleUserRO = new UserRO();
        sampleUserRO.setId(1L);
        sampleUserRO.setName("Sample User");
        sampleUserRO.setStore(sampleStoreRequest);
        sampleUserRO.setRole(Role.EMPLOYEE);

        when(storeService.getStoreDetailsUsingId(mockRequest.getStoreId())).thenReturn(sampleStoreRequest);
        when(productService.getProductById(mockRequest.getProductId())).thenReturn(sampleProductRO);
        when(userService.getUserById(mockRequest.getUserId())).thenReturn(sampleUserRO);

        String currencyFileContent = "{\"rates\": {\"INR\": 75.0}}";
        when(Files.readAllBytes(any())).thenReturn(currencyFileContent.getBytes());

        TransactionRO result = transactionService.addNewTransaction(mockRequest);
        assertNotNull(result);
        assertEquals(TransactionStatus.SUCCESS, result.getStatus());
    }

    @Test
    public void testAddNewTransactionUSDSuccess() throws ValidationException, IOException {
        TransactionRequest mockRequest = new TransactionRequest();
        mockRequest.setCustomerName("John Doe");
        mockRequest.setCustomerNumber(123456789L);
        mockRequest.setStoreId(1L);
        mockRequest.setUserId(1L);
        mockRequest.setProductId(1L);
        mockRequest.setTransactionType(TransactionType.CREDIT);
        mockRequest.setAmount(100.0);
        mockRequest.setCurrency(Currency.USD);

        TransactionRO mockTransactionRO = new TransactionRO();
        mockTransactionRO.setTransactionId(123L);
        mockTransactionRO.setCustomerName(mockRequest.getCustomerName());
        mockTransactionRO.setCustomerNumber(mockRequest.getCustomerNumber());
        mockTransactionRO.setStoreId(mockRequest.getStoreId());
        mockTransactionRO.setUserId(mockRequest.getUserId());
        mockTransactionRO.setProductId(mockRequest.getProductId());
        mockTransactionRO.setTransactionType(mockRequest.getTransactionType());
        mockTransactionRO.setAmount(mockRequest.getAmount());

        StoreRequest sampleStoreRequest = new StoreRequest();
        sampleStoreRequest.setId(1L);
        sampleStoreRequest.setLocation("Sample Location");
        sampleStoreRequest.setName("Sample Store");
        sampleStoreRequest.setOwnerId(101L);
        sampleStoreRequest.setOwnerName("John Owner");

        CategoryRO sampleCategoryRO = new CategoryRO();
        sampleCategoryRO.setId(1L);
        sampleCategoryRO.setName("Sample Category");
        sampleCategoryRO.setStatus(Status.ACTIVE);

        ProductRO sampleProductRO = new ProductRO();
        sampleProductRO.setId(1L);
        sampleProductRO.setName("Sample Product");
        sampleProductRO.setCategoryRO(sampleCategoryRO);
        sampleProductRO.setStatus(Status.ACTIVE);
        sampleProductRO.setPrice(50.0);
        sampleProductRO.setCurrency(Currency.INR);

        UserRO sampleUserRO = new UserRO();
        sampleUserRO.setId(1L);
        sampleUserRO.setName("Sample User");
        sampleUserRO.setStore(sampleStoreRequest);
        sampleUserRO.setRole(Role.EMPLOYEE);

        when(storeService.getStoreDetailsUsingId(mockRequest.getStoreId())).thenReturn(sampleStoreRequest);
        when(productService.getProductById(mockRequest.getProductId())).thenReturn(sampleProductRO);
        when(userService.getUserById(mockRequest.getUserId())).thenReturn(sampleUserRO);

        String currencyFileContent = "{\"rates\": {\"INR\": 75.0}}";
        when(Files.readAllBytes(any())).thenReturn(currencyFileContent.getBytes());

        TransactionRO result = transactionService.addNewTransaction(mockRequest);

        assertNotNull(result);
        assertEquals(TransactionStatus.SUCCESS, result.getStatus());
    }

    @Test(expected = ValidationException.class)
    public void testAddNewTransactionCustomerNameException() throws ValidationException, IOException {
        TransactionRequest mockRequest = new TransactionRequest();
        mockRequest.setCustomerNumber(123456789L);
        mockRequest.setStoreId(1L);
        mockRequest.setUserId(1L);
        mockRequest.setProductId(1L);
        mockRequest.setTransactionType(TransactionType.CREDIT);
        mockRequest.setAmount(100.0);
        mockRequest.setCurrency(Currency.USD);
        TransactionRO result = transactionService.addNewTransaction(mockRequest);
    }

    @Test(expected = ValidationException.class)
    public void testAddNewTransactionCustomerNumberException() throws ValidationException, IOException {
        TransactionRequest mockRequest = new TransactionRequest();
        mockRequest.setCustomerName("John Doe");
        mockRequest.setStoreId(1L);
        mockRequest.setUserId(1L);
        mockRequest.setProductId(1L);
        mockRequest.setTransactionType(TransactionType.CREDIT);
        mockRequest.setAmount(100.0);
        mockRequest.setCurrency(Currency.USD);

        TransactionRO result = transactionService.addNewTransaction(mockRequest);
    }

    @Test(expected = ValidationException.class)
    public void testAddNewTransactionStoreIdException() throws ValidationException, IOException {
        TransactionRequest mockRequest = new TransactionRequest();
        mockRequest.setCustomerName("John Doe");
        mockRequest.setCustomerNumber(123456789L);
        mockRequest.setUserId(2L);
        mockRequest.setProductId(3L);
        mockRequest.setTransactionType(TransactionType.CREDIT);
        mockRequest.setAmount(100.0);
        mockRequest.setCurrency(Currency.USD);

        TransactionRO result = transactionService.addNewTransaction(mockRequest);
    }

    @Test(expected = ValidationException.class)
    public void testAddNewTransactionProductIdException() throws ValidationException, IOException {
        TransactionRequest mockRequest = new TransactionRequest();
        mockRequest.setCustomerName("John Doe");
        mockRequest.setCustomerNumber(123456789L);
        mockRequest.setStoreId(1L);
        mockRequest.setUserId(2L);
        mockRequest.setTransactionType(TransactionType.CREDIT);
        mockRequest.setAmount(100.0);
        mockRequest.setCurrency(Currency.USD);

        TransactionRO result = transactionService.addNewTransaction(mockRequest);
    }

    @Test(expected = ValidationException.class)
    public void testAddNewTransactionUserIdException() throws ValidationException, IOException {
        TransactionRequest mockRequest = new TransactionRequest();
        mockRequest.setCustomerName("John Doe");
        mockRequest.setCustomerNumber(123456789L);
        mockRequest.setStoreId(1L);
        mockRequest.setProductId(3L);
        mockRequest.setTransactionType(TransactionType.CREDIT);
        mockRequest.setAmount(100.0);
        mockRequest.setCurrency(Currency.USD);

        TransactionRO result = transactionService.addNewTransaction(mockRequest);
    }

    @Test(expected = ValidationException.class)
    public void testAddNewTransactionAmountException() throws ValidationException, IOException {
        TransactionRequest mockRequest = new TransactionRequest();
        mockRequest.setCustomerName("John Doe");
        mockRequest.setCustomerNumber(123456789L);
        mockRequest.setStoreId(1L);
        mockRequest.setUserId(2L);
        mockRequest.setProductId(3L);
        mockRequest.setTransactionType(TransactionType.CREDIT);
        mockRequest.setCurrency(Currency.USD);

        TransactionRO result = transactionService.addNewTransaction(mockRequest);
    }

    @Test(expected = ValidationException.class)
    public void testAddNewTransactionCurrencyException() throws ValidationException, IOException {
        TransactionRequest mockRequest = new TransactionRequest();
        mockRequest.setCustomerName("John Doe");
        mockRequest.setCustomerNumber(123456789L);
        mockRequest.setStoreId(1L);
        mockRequest.setUserId(2L);
        mockRequest.setProductId(3L);
        mockRequest.setTransactionType(TransactionType.CREDIT);
        mockRequest.setAmount(100.0);

        TransactionRO result = transactionService.addNewTransaction(mockRequest);
    }

    @Test(expected = ValidationException.class)
    public void testAddNewTransactionDiffStoreIdException() throws ValidationException, IOException {
        TransactionRequest mockRequest = new TransactionRequest();
        mockRequest.setCustomerName("John Doe");
        mockRequest.setCustomerNumber(123456789L);
        mockRequest.setStoreId(1L);
        mockRequest.setUserId(1L);
        mockRequest.setProductId(1L);
        mockRequest.setTransactionType(TransactionType.CREDIT);
        mockRequest.setAmount(100.0);
        mockRequest.setCurrency(Currency.USD);

        StoreRequest sampleStoreRequest = new StoreRequest();
        sampleStoreRequest.setId(2L);
        sampleStoreRequest.setLocation("Sample Location");
        sampleStoreRequest.setName("Sample Store");
        sampleStoreRequest.setOwnerId(101L);
        sampleStoreRequest.setOwnerName("John Owner");


        when(storeService.getStoreDetailsUsingId(mockRequest.getStoreId())).thenReturn(sampleStoreRequest);

        TransactionRO result = transactionService.addNewTransaction(mockRequest);
    }
}
