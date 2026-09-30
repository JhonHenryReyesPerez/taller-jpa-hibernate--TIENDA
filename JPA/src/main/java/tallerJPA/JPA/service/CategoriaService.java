package tallerJPA.JPA.service;

import tallerJPA.JPA.dto.request.CategoriaRequest;
import tallerJPA.JPA.dto.response.CategoriaResponse;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {

    List<CategoriaResponse> listarTodos();

    Optional<CategoriaResponse> buscarPorId(Long id);

    CategoriaResponse crear(CategoriaRequest request);

    CategoriaResponse actualizar(Long id, CategoriaRequest request);

    void eliminar(Long id);
}
