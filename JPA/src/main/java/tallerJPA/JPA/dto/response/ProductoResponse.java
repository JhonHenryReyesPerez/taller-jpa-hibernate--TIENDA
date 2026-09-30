package tallerJPA.JPA.dto.response;

import java.math.BigDecimal;

public record ProductoResponse(
        Long id,
        String nombre,
        BigDecimal precio,
        Integer stock,
        Long categoriaId,
        String categoriaNombre
) {
}
