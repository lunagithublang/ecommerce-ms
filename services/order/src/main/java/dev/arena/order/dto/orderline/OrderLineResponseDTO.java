package dev.arena.order.dto.orderline;

import dev.arena.common.dto.BaseResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineResponseDTO extends BaseResponseDTO {

    private UUID orderId;
    private int quality;
    private UUID productId;
}
