package tallerJPA.JPA.service;

import tallerJPA.JPA.dto.request.ClienteRequest;
import tallerJPA.JPA.dto.response.ClienteResponse;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    List<ClienteResponse> listarTodos();

    Optional<ClienteResponse> buscarPorId(Long id);

    ClienteResponse crear(ClienteRequest request);

    ClienteResponse actualizar(Long id, ClienteRequest request);

    void eliminar(Long id);
}
