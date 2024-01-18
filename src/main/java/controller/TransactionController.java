package controller;

import exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import object.request.TransactionRequest;
import object.response.TransactionRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.transaction.ITransactionService;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/v1/transaction")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping(value = "/newTransaction")
    public TransactionRO addNewTransaction(@RequestBody TransactionRequest transactionRequest) throws IOException, ValidationException {
        return transactionService.addNewTransaction(transactionRequest);
    }
}
