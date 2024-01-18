package object.response;

import enums.Status;
import lombok.Data;

@Data
public class ProductRO {
    private Long id;
    private String name;
    private CategoryRO categoryRO;
    private Status status;
    private Double price;
}
