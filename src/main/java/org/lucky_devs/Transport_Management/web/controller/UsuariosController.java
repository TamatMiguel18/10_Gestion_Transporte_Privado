package org.lucky_devs.Transport_Management.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.lucky_devs.Transport_Management.dominio.dto.UsuarioDto;
import org.lucky_devs.Transport_Management.dominio.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Usuarios")
@Tag(name = "Usuarios", description = "Operaciones CRUD para la gestión de usuarios del sistema.")
public class UsuariosController {

    private final UsuarioService usuarioService;

    public UsuariosController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    @Operation(summary = "Listar todos los usuarios")
    public ResponseEntity<List<UsuarioDto>> listarUsuarios() {
        List<UsuarioDto> lista = this.usuarioService.listarUsuarios();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{correo}")
    @Operation(
            summary = "Buscar usuario por correo",
            description = "Obtiene un usuario usando su correo electrónico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuario encontrado"),
                    @ApiResponse(responseCode = "404", description = "Usuario no encontrado", content = @Content)
            })
    public ResponseEntity<UsuarioDto> buscarPorCorreo(
            @Parameter(description = "Correo del usuario", example = "usuario@empresa.com")
            @PathVariable String correo) {
        UsuarioDto usuario = this.usuarioService.buscarPorCorreo(correo);
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    @Operation(summary = "Registrar un nuevo usuario")
    public ResponseEntity<UsuarioDto> registrarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
        UsuarioDto nuevo = this.usuarioService.agregarUsuario(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @PutMapping("/{correo}")
    @Operation(summary = "Modificar usuario existente")
    public ResponseEntity<UsuarioDto> modificarUsuario(
            @PathVariable String correo,
            @RequestBody UsuarioDto usuarioModificado) {
        UsuarioDto actualizado = this.usuarioService.modificarUsuario(correo, usuarioModificado);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{correo}")
    @Operation(summary = "Eliminar un usuario")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable String correo) {
        this.usuarioService.eliminarUsuario(correo);
        return ResponseEntity.ok().build();
    }
}
