package object.response;

import entity.Product;
import enums.Status;
import lombok.Data;

import java.util.List;

@Data
public class CategoryRO {
    private Long id;
    private String name;
    private Status status;
}
