package com.example.transactions.controller;

import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.ControllerBaseResponse;
import com.example.transactions.util.ControllerResponseUtil;
import lombok.extern.slf4j.Slf4j;
import com.example.transactions.object.request.TransactionRequest;
import com.example.transactions.object.response.TransactionRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.transactions.services.transaction.ITransactionService;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/v1/transaction")
public class TransactionController {

    @Autowired
    private ITransactionService transactionService;

    @PostMapping(value = "/newTransaction")
    public ResponseEntity<ControllerBaseResponse> addNewTransaction(@RequestBody TransactionRequest transactionRequest) throws IOException, ValidationException {
        log.info("Api: /v1/transaction/newTransaction , transactionRequest : {}",transactionRequest);
        return ResponseEntity.ok(ControllerResponseUtil.getSuccessResponse(transactionService.addNewTransaction(transactionRequest)));
    }
}
