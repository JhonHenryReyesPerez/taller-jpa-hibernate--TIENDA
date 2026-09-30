package tallerJPA.JPA.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tallerJPA.JPA.dto.request.DetallePedidoRequest;
import tallerJPA.JPA.dto.response.DetallePedidoResponse;
import tallerJPA.JPA.service.DetallePedidoService;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedido")
@RequiredArgsConstructor
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    @GetMapping
    public List<DetallePedidoResponse> listar() {
        return detallePedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoResponse> buscarPorId(@PathVariable Long id) {
        return detallePedidoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Un detalle siempre pertenece a un pedido existente, por eso el pedidoId va en la URL
    @PostMapping("/pedido/{pedidoId}")
    @ResponseStatus(HttpStatus.CREATED)
    public DetallePedidoResponse crear(@PathVariable Long pedidoId, @RequestBody DetallePedidoRequest request) {
        return detallePedidoService.crear(pedidoId, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        return detallePedidoService.buscarPorId(id)
                .map(existente -> {
                    detallePedidoService.eliminar(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
