package tallerJPA.JPA.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tallerJPA.JPA.dto.request.ClienteRequest;
import tallerJPA.JPA.dto.response.ClienteResponse;
import tallerJPA.JPA.mapper.ClienteMapper;
import tallerJPA.JPA.model.Cliente;
import tallerJPA.JPA.repository.ClienteRepository;
import tallerJPA.JPA.service.ClienteService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Override
    public List<ClienteResponse> listarTodos() {
        return clienteRepository.findAll().stream()
                .map(ClienteMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<ClienteResponse> buscarPorId(Long id) {
        return clienteRepository.findById(id).map(ClienteMapper::toResponse);
    }

    @Override
    public ClienteResponse crear(ClienteRequest request) {
        Cliente cliente = ClienteMapper.toEntity(request);
        return ClienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Override
    public ClienteResponse actualizar(Long id, ClienteRequest request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe el cliente con id " + id));
        cliente.setNombre(request.nombre());
        cliente.setEmail(request.email());
        return ClienteMapper.toResponse(clienteRepository.save(cliente));
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
