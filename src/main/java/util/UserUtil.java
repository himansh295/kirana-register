package util;

import entity.User;
import enums.Role;
import enums.Status;
import object.request.UserRequest;
import object.response.UserRO;

public class UserUtil {

    private UserUtil() {

    }

    public static User getUserUsingUserRequest(UserRequest userRequest) {
        User user = new User();
        user.setName(user.getName());
        user.setRole(Role.EMPLOYEE);
        user.setStatus(Status.ACTIVE);
        return user;
    }

    public static UserRO getUserROUsingUser(User user) {
        UserRO userRO = new UserRO();
        userRO.setId(user.getId());
        userRO.setName(user.getName());
        userRO.setRole(user.getRole());
        return userRO;
    }
}
