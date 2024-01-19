package com.example.transactions.services.transaction.impl;

import com.example.transactions.services.product.IProductService;
import com.example.transactions.services.transaction.ITransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.transactions.dao.ITransactionRepository;
import com.example.transactions.entity.Transaction;
import com.example.transactions.enums.Currency;
import com.example.transactions.enums.TransactionStatus;
import com.example.transactions.exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import com.example.transactions.object.response.CurrencyRO;
import com.example.transactions.object.request.StoreRequest;
import com.example.transactions.object.request.TransactionRequest;
import com.example.transactions.object.response.ProductRO;
import com.example.transactions.object.response.TransactionRO;
import com.example.transactions.object.response.UserRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import com.example.transactions.services.store.IStoreService;
import com.example.transactions.services.user.IUserService;
import com.example.transactions.util.TransactionUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Service
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private IStoreService storeService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IUserService userService;

    @Autowired
    private ITransactionRepository transactionRepository;

    private static final String CURRENCY_CONVERSION_FILE_PATH = "src/main/resources/static/currency.json";

    private void checkAndUpdateRequest(TransactionRequest transactionRequest) throws ValidationException {
        if(StringUtils.isEmpty(transactionRequest.getCustomerName())){
            throw new ValidationException("Customer Name is required!","400");
        }

        if(null == (transactionRequest.getCustomerNumber())){
            throw new ValidationException("Customer Number is required!","400");
        }

        if(null == (transactionRequest.getStoreId())){
            throw new ValidationException("Store Id is required!","400");
        }

        if(null == (transactionRequest.getProductId())){
            throw new ValidationException("Product Id is required!","400");
        }

        if(null == (transactionRequest.getUserId())){
            throw new ValidationException("User Id is required!","400");
        }

        if(null == transactionRequest.getAmount() || transactionRequest.getAmount() <= 0.0) {
            throw new ValidationException("Enter valid amount value","400");
        }

        if(null == transactionRequest.getCurrency()) {
            throw new ValidationException("Amount currency is required","400");
        }

        StoreRequest storeRequest = storeService.getStoreDetailsUsingId(transactionRequest.getStoreId());
        ProductRO productRO = productService.getProductById(transactionRequest.getProductId());
        UserRO userRO = userService.getUserById(transactionRequest.getUserId());

        if(transactionRequest.getStoreId() != userRO.getStore().getId()) {
            throw new ValidationException("Employee storeId and transactionId store are different","400");
        }

        transactionRequest.setStore(storeRequest);
        transactionRequest.setProductRO(productRO);
        transactionRequest.setUser(userRO);
    }

    @Transactional
    @Override
    public TransactionRO addNewTransaction(TransactionRequest transactionRequest) throws ValidationException, IOException {
        checkAndUpdateRequest(transactionRequest);

        Transaction transaction = new Transaction();
        transaction.setCustomerName(transactionRequest.getCustomerName());
        transaction.setCustomerNumber(transactionRequest.getCustomerNumber());
        transaction.setStoreId(transactionRequest.getStoreId());
        transaction.setProductId(transactionRequest.getProductId());
        transaction.setUserId(transactionRequest.getUserId());
        transaction.setTransactionType(transactionRequest.getTransactionType());

        if(transactionRequest.getCurrency() == Currency.INR) {
            transaction.setAmount(transaction.getAmount());
        } else {
            String json = new String(Files.readAllBytes(Paths.get(CURRENCY_CONVERSION_FILE_PATH)));
            ObjectMapper mapper = new ObjectMapper();
            CurrencyRO currencyRO = mapper.readValue(json,CurrencyRO.class);
            if(currencyRO.getRates().containsKey(Currency.INR.name())) {
                transaction.setAmount(transactionRequest.getAmount()*currencyRO.getRates().get(Currency.INR.name()));
            } else {
                transaction.setAmount(transactionRequest.getAmount()*(80.0));
            }
        }

        transaction.setStatus(TransactionStatus.SUCCESS);
        transactionRepository.save(transaction);

        TransactionRO transactionRO = TransactionUtil.getTransactionROUsingTransaction(transaction);
        transactionRO.setStore(transactionRequest.getStore());
        transactionRO.setProduct(transactionRequest.getProductRO());
        transactionRO.setUser(transactionRequest.getUser());
        return transactionRO;
    }


}
