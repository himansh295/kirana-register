package object.response;

import enums.Role;
import lombok.Data;
import object.request.StoreRequest;

@Data
public class UserRO {
    private Long id;
    private String name;
    private StoreRequest store;
    private Role role;
}
