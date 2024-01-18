package object.response;

import lombok.Data;

import java.util.Map;

@Data
public class CurrencyRO {
    private String base;
    private Map<String,Long> rates;
}
