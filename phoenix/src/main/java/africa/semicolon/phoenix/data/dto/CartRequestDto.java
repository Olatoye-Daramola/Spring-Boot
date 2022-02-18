package africa.semicolon.phoenix.data.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class CartRequestDto {
    private Long userId;
    private Long productId;
    private Integer quantity;
}
