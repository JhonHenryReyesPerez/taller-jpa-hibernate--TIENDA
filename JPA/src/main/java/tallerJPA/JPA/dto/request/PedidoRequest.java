package tallerJPA.JPA.dto.request;

import java.util.List;

public record PedidoRequest(
        Long clienteId,
        List<DetallePedidoRequest> detalles
) {
}
