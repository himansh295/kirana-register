package object.request;

import enums.Role;
import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String name;
    private Role role;
    private StoreRequest store;
}
