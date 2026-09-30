package tallerJPA.JPA.dto.request;

import java.math.BigDecimal;

public record ProductoRequest(
        String nombre,
        BigDecimal precio,
        Integer stock,
        Long categoriaId
) {
}
