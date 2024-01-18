package services.user;

import exception.ValidationException;
import object.request.UserRequest;
import object.response.UserRO;

public interface IUserService {
    UserRO addNewUser (UserRequest userRequest) throws ValidationException;
}
