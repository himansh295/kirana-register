package services.transaction.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.ITransactionRepository;
import entity.Transaction;
import enums.Currency;
import enums.TransactionStatus;
import exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import object.response.CurrencyRO;
import object.request.StoreRequest;
import object.request.TransactionRequest;
import object.response.ProductRO;
import object.response.TransactionRO;
import object.response.UserRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import services.product.IProductService;
import services.store.IStoreService;
import services.transaction.ITransactionService;
import services.user.IUserService;
import util.TransactionUtil;

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
            throw new ValidationException("Customer Name is required!");
        }

        if(null == (transactionRequest.getCustomerNumber())){
            throw new ValidationException("Customer Number is required!");
        }

        if(null == (transactionRequest.getStoreId())){
            throw new ValidationException("Store Id is required!");
        }

        if(null == (transactionRequest.getProductId())){
            throw new ValidationException("Product Id is required!");
        }

        if(null == (transactionRequest.getUserId())){
            throw new ValidationException("User Id is required!");
        }

        if(null == transactionRequest.getAmount() || transactionRequest.getAmount() <= 0.0) {
            throw new ValidationException("Enter valid amount value");
        }

        if(null == transactionRequest.getCurrency()) {
            throw new ValidationException("Amount currency is required");
        }

        StoreRequest storeRequest = storeService.getStoreDetailsUsingId(transactionRequest.getStoreId());
        ProductRO productRO = productService.getProductById(transactionRequest.getProductId());
        UserRO userRO = userService.getUserById(transactionRequest.getUserId());

        transactionRequest.setStore(storeRequest);
        transactionRequest.setProductRO(productRO);
        transactionRequest.setUser(userRO);
    }

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
                transaction.setAmount(transaction.getAmount()*currencyRO.getRates().get(Currency.INR.name()));
            } else {
                transaction.setAmount(transaction.getAmount()*(80.0));
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
