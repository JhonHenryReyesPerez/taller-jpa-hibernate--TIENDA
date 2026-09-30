package tallerJPA.JPA.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tallerJPA.JPA.dto.request.CategoriaRequest;
import tallerJPA.JPA.dto.response.CategoriaResponse;
import tallerJPA.JPA.mapper.CategoriaMapper;
import tallerJPA.JPA.model.Categoria;
import tallerJPA.JPA.repository.CategoriaRepository;
import tallerJPA.JPA.service.CategoriaService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaResponse> listarTodos() {
        return categoriaRepository.findAll().stream()
                .map(CategoriaMapper::toResponse)
                .toList();
    }

    @Override
    public Optional<CategoriaResponse> buscarPorId(Long id) {
        return categoriaRepository.findById(id).map(CategoriaMapper::toResponse);
    }

    @Override
    public CategoriaResponse crear(CategoriaRequest request) {
        Categoria categoria = CategoriaMapper.toEntity(request);
        return CategoriaMapper.toResponse(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaResponse actualizar(Long id, CategoriaRequest request) {
        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No existe la categoria con id " + id));
        categoria.setNombre(request.nombre());
        return CategoriaMapper.toResponse(categoriaRepository.save(categoria));
    }

    @Override
    public void eliminar(Long id) {
        categoriaRepository.deleteById(id);
    }
}
