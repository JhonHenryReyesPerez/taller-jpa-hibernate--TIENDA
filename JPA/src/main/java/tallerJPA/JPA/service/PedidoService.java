package tallerJPA.JPA.service;

import tallerJPA.JPA.dto.request.PedidoRequest;
import tallerJPA.JPA.dto.response.PedidoResponse;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    List<PedidoResponse> listarTodos();

    Optional<PedidoResponse> buscarPorId(Long id);

    PedidoResponse crear(PedidoRequest request);

    void eliminar(Long id);
}
