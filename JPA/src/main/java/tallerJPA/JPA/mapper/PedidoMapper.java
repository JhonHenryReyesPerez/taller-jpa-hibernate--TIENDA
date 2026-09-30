package tallerJPA.JPA.mapper;

import tallerJPA.JPA.dto.response.DetallePedidoResponse;
import tallerJPA.JPA.dto.response.PedidoResponse;
import tallerJPA.JPA.model.Cliente;
import tallerJPA.JPA.model.Pedido;

import java.util.Collections;
import java.util.List;

public class PedidoMapper {

    private PedidoMapper() {
    }

    public static PedidoResponse toResponse(Pedido pedido) {
        if (pedido == null) return null;
        Cliente cliente = pedido.getCliente();
        List<DetallePedidoResponse> detalles = pedido.getDetalles() == null
                ? Collections.emptyList()
                : pedido.getDetalles().stream()
                    .map(DetallePedidoMapper::toResponse)
                    .toList();

        return new PedidoResponse(
                pedido.getId(),
                pedido.getFecha(),
                cliente != null ? cliente.getId() : null,
                cliente != null ? cliente.getNombre() : null,
                detalles
        );
    }
}
