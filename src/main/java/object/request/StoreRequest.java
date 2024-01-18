package object.request;

import lombok.Data;

@Data
public class StoreRequest {
    private Long id;
    private String location;
    private String name;
    private Long ownerId;
    private String ownerName;
}
