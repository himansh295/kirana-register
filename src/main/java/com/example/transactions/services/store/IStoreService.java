package com.example.transactions.services.store;

import com.example.transactions.exception.ValidationException;
import com.example.transactions.object.request.StoreRequest;

public interface IStoreService {
    StoreRequest getStoreDetailsUsingId(Long storeId) throws ValidationException;
}
