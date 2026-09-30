package tallerJPA.JPA.dto.response;

public record ClienteResponse(
        Long id,
        String nombre,
        String email
) {
}
