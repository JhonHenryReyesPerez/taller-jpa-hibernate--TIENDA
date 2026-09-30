package tallerJPA.JPA.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tallerJPA.JPA.dto.request.DetallePedidoRequest;
import tallerJPA.JPA.dto.response.DetallePedidoResponse;
import tallerJPA.JPA.mapper.DetallePedidoMapper;
import tallerJPA.JPA.model.DetallePedido;
import tallerJPA.JPA.model.Pedido;
import tallerJPA.JPA.model.Producto;
import tallerJPA.JPA.repository.DetallePedidoRepository;
import tallerJPA.JPA.repository.PedidoRepository;
import tallerJPA.JPA.repository.ProductoRepository;
import tallerJPA.JPA.service.DetallePedidoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    @Override
    public List<DetallePedidoResponse> listarTodos() {
        return detallePedidoRepository.findAll().stream()
                .map(DetallePedidoMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<DetallePedidoResponse> buscarPorId(Long id) {
        return detallePedidoRepository.findById(id).map(DetallePedidoMapper::toResponse);
    }

    @Override
    public DetallePedidoResponse crear(Long pedidoId, DetallePedidoRequest request) {
        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new IllegalArgumentException("No existe el pedido con id " + pedidoId));
        Producto producto = productoRepository.findById(request.productoId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe el producto con id " + request.productoId()));

        DetallePedido detalle = DetallePedidoMapper.toEntity(request);
        detalle.setPedido(pedido);
        detalle.setProducto(producto);

        return DetallePedidoMapper.toResponse(detallePedidoRepository.save(detalle));
    }

    @Override
    public void eliminar(Long id) {
        detallePedidoRepository.deleteById(id);
    }
}
