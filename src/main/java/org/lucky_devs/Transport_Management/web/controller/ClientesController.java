package org.lucky_devs.Transport_Management.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.lucky_devs.Transport_Management.dominio.dto.ClientesDto;
import org.lucky_devs.Transport_Management.dominio.service.ClientesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Clientes")
@Tag(name = "Clientes", description = "Operaciones CRUD para los clientes del sistema.")
public class ClientesController {

    private final ClientesService clientesService;

    public ClientesController(ClientesService clientesService) {
        this.clientesService = clientesService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los clientes")
    public ResponseEntity<List<ClientesDto>> listarClientes() {
        List<ClientesDto> lista = this.clientesService.listarClientes();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{correo}")
    @Operation(
            summary = "Obtener un cliente por correo electrónico",
            description = "Busca un cliente usando su dirección de correo.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)
            })
    public ResponseEntity<ClientesDto> buscarPorCorreo(
            @Parameter(description = "Correo electrónico del cliente", example = "cliente@gmail.com")
            @PathVariable String correo) {
        ClientesDto cliente = this.clientesService.buscarPorCorreo(correo);
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo cliente")
    public ResponseEntity<ClientesDto> agregarCliente(@RequestBody @Valid ClientesDto clienteDto) {
        ClientesDto nuevo = this.clientesService.agregarNuevoCliente(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{correo}")
    @Operation(summary = "Modificar los datos de un cliente existente")
    public ResponseEntity<ClientesDto> modificarCliente(
            @PathVariable String correo,
            @RequestBody ClientesDto clienteModificado) {
        ClientesDto actualizado = this.clientesService.modificarCliente(correo, clienteModificado);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{correo}")
    @Operation(summary = "Eliminar un cliente")
    public ResponseEntity<Void> eliminarCliente(@PathVariable String correo) {
        this.clientesService.eliminarCliente(correo);
        return ResponseEntity.ok().build();
    }
}
