package tallerJPA.JPA.service;

import tallerJPA.JPA.dto.request.DetallePedidoRequest;
import tallerJPA.JPA.dto.response.DetallePedidoResponse;

import java.util.List;
import java.util.Optional;

public interface DetallePedidoService {

    List<DetallePedidoResponse> listarTodos();

    Optional<DetallePedidoResponse> buscarPorId(Long id);

    DetallePedidoResponse crear(Long pedidoId, DetallePedidoRequest request);

    void eliminar(Long id);
}
