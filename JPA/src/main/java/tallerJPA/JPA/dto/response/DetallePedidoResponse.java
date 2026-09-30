package tallerJPA.JPA.dto.response;

import java.math.BigDecimal;

public record DetallePedidoResponse(
        Long id,
        Long pedidoId,
        Long productoId,
        String productoNombre,
        Integer cantidad,
        BigDecimal precioUnitario
) {
}
