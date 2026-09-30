package tallerJPA.JPA.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tallerJPA.JPA.dto.request.DetallePedidoRequest;
import tallerJPA.JPA.dto.request.PedidoRequest;
import tallerJPA.JPA.dto.response.PedidoResponse;
import tallerJPA.JPA.mapper.PedidoMapper;
import tallerJPA.JPA.model.Cliente;
import tallerJPA.JPA.model.DetallePedido;
import tallerJPA.JPA.model.Pedido;
import tallerJPA.JPA.model.Producto;
import tallerJPA.JPA.repository.ClienteRepository;
import tallerJPA.JPA.repository.PedidoRepository;
import tallerJPA.JPA.repository.ProductoRepository;
import tallerJPA.JPA.service.PedidoService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProductoRepository productoRepository;

    @Override
    public List<PedidoResponse> listarTodos() {
        return pedidoRepository.findAll().stream()
                .map(PedidoMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<PedidoResponse> buscarPorId(Long id) {
        return pedidoRepository.findById(id).map(PedidoMapper::toResponse);
    }

    @Override
    public PedidoResponse crear(PedidoRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe el cliente con id " + request.clienteId()));

        Pedido pedido = new Pedido();
        pedido.setFecha(LocalDateTime.now());
        pedido.setCliente(cliente);

        List<DetallePedido> detalles = request.detalles() == null
                ? List.of()
                : request.detalles().stream()
                    .map(detalleRequest -> crearDetalle(detalleRequest, pedido))
                    .toList();
        pedido.setDetalles(detalles);

        return PedidoMapper.toResponse(pedidoRepository.save(pedido));
    }

    private DetallePedido crearDetalle(DetallePedidoRequest request, Pedido pedido) {
        Producto producto = productoRepository.findById(request.productoId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe el producto con id " + request.productoId()));

        DetallePedido detalle = new DetallePedido();
        detalle.setPedido(pedido);
        detalle.setProducto(producto);
        detalle.setCantidad(request.cantidad());
        detalle.setPrecioUnitario(request.precioUnitario());
        return detalle;
    }

    @Override
    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }
}
