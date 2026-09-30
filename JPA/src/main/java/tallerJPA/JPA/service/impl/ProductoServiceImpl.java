package tallerJPA.JPA.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tallerJPA.JPA.dto.request.ProductoRequest;
import tallerJPA.JPA.dto.response.ProductoResponse;
import tallerJPA.JPA.mapper.ProductoMapper;
import tallerJPA.JPA.model.Categoria;
import tallerJPA.JPA.model.Producto;
import tallerJPA.JPA.repository.CategoriaRepository;
import tallerJPA.JPA.repository.ProductoRepository;
import tallerJPA.JPA.service.ProductoService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    public List<ProductoResponse> listarTodos() {
        return productoRepository.findAll().stream()
                .map(ProductoMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<ProductoResponse> buscarPorId(Long id) {
        return productoRepository.findById(id).map(ProductoMapper::toResponse);
    }

    @Override
    public ProductoResponse crear(ProductoRequest request) {
        Producto producto = ProductoMapper.toEntity(request);
        producto.setCategoria(obtenerCategoria(request.categoriaId()));
        return ProductoMapper.toResponse(productoRepository.save(producto));
    }

    @Override
    public ProductoResponse actualizar(Long id, ProductoRequest request) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe el producto con id " + id));
        producto.setNombre(request.nombre());
        producto.setPrecio(request.precio());
        producto.setStock(request.stock());
        producto.setCategoria(obtenerCategoria(request.categoriaId()));
        return ProductoMapper.toResponse(productoRepository.save(producto));
    }

    private Categoria obtenerCategoria(Long categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No existe la categoria con id " + categoriaId));
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
