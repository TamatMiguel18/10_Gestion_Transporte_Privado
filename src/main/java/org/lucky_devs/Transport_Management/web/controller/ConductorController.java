package org.lucky_devs.Transport_Management.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.lucky_devs.Transport_Management.dominio.dto.ConductorDto;
import org.lucky_devs.Transport_Management.dominio.dto.ModConductor;
import org.lucky_devs.Transport_Management.dominio.service.ConductorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Conductores")
@Tag(name = "Conductores", description = "Operaciones CRUD para los conductores del sistema.")
public class ConductorController {

    private final ConductorService conductorService;

    public ConductorController(ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    // ==========================
    // GET - Listar todos los conductores
    // ==========================
    @GetMapping
    @Operation(
            summary = "Listar todos los conductores",
            description = "Obtiene una lista con todos los conductores registrados.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente"),
                    @ApiResponse(responseCode = "204", description = "No hay conductores registrados", content = @Content)
            })
    public ResponseEntity<List<ConductorDto>> listarConductores() {
        List<ConductorDto> lista = this.conductorService.listarConductores();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    // ==========================
    // GET - Buscar conductor por licencia
    // ==========================
    @GetMapping("/{licencia}")
    @Operation(
            summary = "Obtener un conductor por su licencia",
            description = "Busca un conductor a partir de su número de licencia.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Conductor encontrado"),
                    @ApiResponse(responseCode = "404", description = "Conductor no encontrado", content = @Content)
            })
    public ResponseEntity<ConductorDto> buscarPorLicencia(
            @Parameter(description = "Número de licencia del conductor", example = "LIC-12345")
            @PathVariable String licencia) {

        ConductorDto conductor = this.conductorService.buscarPorLicencia(licencia);
        return ResponseEntity.ok(conductor);
    }

    // ==========================
    // POST - Registrar nuevo conductor
    // ==========================
    @PostMapping
    @Operation(
            summary = "Registrar un nuevo conductor",
            description = "Crea un nuevo registro de conductor en el sistema.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Conductor creado correctamente"),
                    @ApiResponse(responseCode = "400", description = "Datos inválidos", content = @Content)
            })
    public ResponseEntity<ConductorDto> registrarConductor(
            @RequestBody @Valid ConductorDto conductorDto) {
        ConductorDto nuevoConductor = this.conductorService.agregarConductor(conductorDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoConductor);
    }

    // ==========================
    // PUT - Modificar conductor existente
    // ==========================
    @PutMapping("/{licencia}")
    @Operation(
            summary = "Modificar un conductor existente",
            description = "Actualiza los datos de un conductor registrado usando su número de licencia.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Conductor actualizado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Conductor no encontrado", content = @Content)
            })
    public ResponseEntity<ConductorDto> modificarConductor(
            @Parameter(description = "Número de licencia del conductor", example = "LIC-12345")
            @PathVariable String licencia,
            @RequestBody ModConductor modConductor) {

        ConductorDto actualizado = this.conductorService.modificarConductor(licencia, modConductor);
        return ResponseEntity.ok(actualizado);
    }

    // ==========================
    // DELETE - Eliminar conductor
    // ==========================
    @DeleteMapping("/{licencia}")
    @Operation(
            summary = "Eliminar un conductor por su licencia",
            description = "Borra un conductor del sistema según su número de licencia.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Conductor eliminado correctamente"),
                    @ApiResponse(responseCode = "404", description = "Conductor no encontrado", content = @Content)
            })
    public ResponseEntity<Void> eliminarConductor(
            @Parameter(description = "Número de licencia del conductor", example = "LIC-12345")
            @PathVariable String licencia) {

        this.conductorService.eliminarConductor(licencia);
        return ResponseEntity.ok().build();
    }
}
