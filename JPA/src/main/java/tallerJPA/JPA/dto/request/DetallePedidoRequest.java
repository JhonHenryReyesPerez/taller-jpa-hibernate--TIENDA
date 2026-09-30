package tallerJPA.JPA.dto.request;

import java.math.BigDecimal;

public record DetallePedidoRequest(
        Long productoId,
        Integer cantidad,
        BigDecimal precioUnitario
) {
}
