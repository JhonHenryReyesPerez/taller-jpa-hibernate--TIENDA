package tallerJPA.JPA.service;

import tallerJPA.JPA.dto.request.ProductoRequest;
import tallerJPA.JPA.dto.response.ProductoResponse;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    List<ProductoResponse> listarTodos();

    Optional<ProductoResponse> buscarPorId(Long id);

    ProductoResponse crear(ProductoRequest request);

    ProductoResponse actualizar(Long id, ProductoRequest request);

    void eliminar(Long id);
}
