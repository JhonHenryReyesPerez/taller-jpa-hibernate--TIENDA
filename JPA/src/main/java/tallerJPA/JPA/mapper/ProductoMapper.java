package tallerJPA.JPA.mapper;

import tallerJPA.JPA.dto.request.ProductoRequest;
import tallerJPA.JPA.dto.response.ProductoResponse;
import tallerJPA.JPA.model.Categoria;
import tallerJPA.JPA.model.Producto;

public class ProductoMapper {

    private ProductoMapper() {
    }

    public static ProductoResponse toResponse(Producto producto) {
        if (producto == null) return null;
        Categoria categoria = producto.getCategoria();
        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                categoria != null ? categoria.getId() : null,
                categoria != null ? categoria.getNombre() : null
        );
    }

    // La Categoria completa se asigna despues en el service (requiere consultarla en su repositorio)
    public static Producto toEntity(ProductoRequest request) {
        if (request == null) return null;
        Producto producto = new Producto();
        producto.setNombre(request.nombre());
        producto.setPrecio(request.precio());
        producto.setStock(request.stock());
        return producto;
    }
}
