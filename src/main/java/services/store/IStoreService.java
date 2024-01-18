package services.store;

import exception.ValidationException;
import object.request.StoreRequest;

public interface IStoreService {
    StoreRequest getStoreDetailsUsingId(Long storeId) throws ValidationException;
}
