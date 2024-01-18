package services.user.impl;

import dao.IStoreRepository;
import entity.Store;
import exception.ValidationException;
import lombok.extern.slf4j.Slf4j;
import object.request.UserRequest;
import object.response.UserRO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import services.user.IUserService;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IStoreRepository storeRepository;

    @Autowired
    private IUserService userService;

    private void checkAndUpdateRequest(UserRequest userRequest) throws ValidationException {
        if(StringUtils.isEmpty(userRequest.getName())) {
            throw new ValidationException("User name is required");
        }

        if(null == userRequest.getRole()) {
            throw new ValidationException("User role is required");
        }

        if(null == userRequest.getStore()) {
            throw new ValidationException("User Store details are required");
        }

        if(null == userRequest.getStore().getId()) {
            throw new ValidationException("User Store id is required");
        }

        Optional<Store> storeOptional = storeRepository.findById(userRequest.getStore().getId());
        if(!storeOptional.isPresent()) {
            throw new ValidationException("Invalid store");
        }

        userRequest.getStore().setId(storeOptional.get().getId());
        userRequest.getStore().setName(storeOptional.get().getName());
        userRequest.getStore().setLocation(storeOptional.get().getLocation());
    }

    @Override
    public UserRO addNewUser(UserRequest userRequest) throws ValidationException {
        checkAndUpdateRequest(userRequest);
        UserRO userRO = new UserRO();

        return userRO;
    }
}
