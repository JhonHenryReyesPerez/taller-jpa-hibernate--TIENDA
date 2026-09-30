package tallerJPA.JPA.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(
        Long id,
        LocalDateTime fecha,
        Long clienteId,
        String clienteNombre,
        List<DetallePedidoResponse> detalles
) {
}
