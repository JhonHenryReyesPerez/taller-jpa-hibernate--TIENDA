package tallerJPA.JPA.mapper;

import tallerJPA.JPA.dto.request.ClienteRequest;
import tallerJPA.JPA.dto.response.ClienteResponse;
import tallerJPA.JPA.model.Cliente;

public class ClienteMapper {

    private ClienteMapper() {
    }

    public static ClienteResponse toResponse(Cliente cliente) {
        if (cliente == null) return null;
        return new ClienteResponse(cliente.getId(), cliente.getNombre(), cliente.getEmail());
    }

    public static Cliente toEntity(ClienteRequest request) {
        if (request == null) return null;
        Cliente cliente = new Cliente();
        cliente.setNombre(request.nombre());
        cliente.setEmail(request.email());
        return cliente;
    }
}
