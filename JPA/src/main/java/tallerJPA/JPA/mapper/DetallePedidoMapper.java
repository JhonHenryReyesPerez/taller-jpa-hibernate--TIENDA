package tallerJPA.JPA.mapper;

import tallerJPA.JPA.dto.request.DetallePedidoRequest;
import tallerJPA.JPA.dto.response.DetallePedidoResponse;
import tallerJPA.JPA.model.DetallePedido;
import tallerJPA.JPA.model.Pedido;
import tallerJPA.JPA.model.Producto;

public class DetallePedidoMapper {

    private DetallePedidoMapper() {
    }

    public static DetallePedidoResponse toResponse(DetallePedido detalle) {
        if (detalle == null) return null;
        Pedido pedido = detalle.getPedido();
        Producto producto = detalle.getProducto();
        return new DetallePedidoResponse(
                detalle.getId(),
                pedido != null ? pedido.getId() : null,
                producto != null ? producto.getId() : null,
                producto != null ? producto.getNombre() : null,
                detalle.getCantidad(),
                detalle.getPrecioUnitario()
        );
    }

    // Pedido y Producto completos se asignan despues en el service (requieren consultarse en su repositorio)
    public static DetallePedido toEntity(DetallePedidoRequest request) {
        if (request == null) return null;
        DetallePedido detalle = new DetallePedido();
        detalle.setCantidad(request.cantidad());
        detalle.setPrecioUnitario(request.precioUnitario());
        return detalle;
    }
}
