package tallerJPA.JPA.mapper;

import tallerJPA.JPA.dto.request.CategoriaRequest;
import tallerJPA.JPA.dto.response.CategoriaResponse;
import tallerJPA.JPA.model.Categoria;

public class CategoriaMapper {

    private CategoriaMapper() {
    }

    public static CategoriaResponse toResponse(Categoria categoria) {
        if (categoria == null) return null;
        return new CategoriaResponse(categoria.getId(), categoria.getNombre());
    }

    public static Categoria toEntity(CategoriaRequest request) {
        if (request == null) return null;
        Categoria categoria = new Categoria();
        categoria.setNombre(request.nombre());
        return categoria;
    }
}
