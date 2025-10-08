package org.lucky_devs.Transport_Management.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.lucky_devs.Transport_Management.dominio.dto.*;
import org.lucky_devs.Transport_Management.dominio.service.RutasService;
import org.lucky_devs.Transport_Management.dominio.service.UnidadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Rutas")
@Tag(name = "Rutas", description = "Operaciones CRUD para las rutas del sistema.")
public class RutaController {

    private final RutasService rutasService;

    public RutaController(RutasService rutasService) {
        this.rutasService = rutasService;
    }

    @GetMapping
    public ResponseEntity<List<RutasResponseDto>> listaRutas(){
        return ResponseEntity.ok(this.rutasService.listarRutas());
    }

    @GetMapping("{id_ruta}")
    @Operation(
            summary = "Obtener rutas por su id",
            description = "Retorna una ruta por su numero de id.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ruta encontrada"),
                    @ApiResponse(responseCode = "404", description = "Ruta no encontrada", content = @Content)
            })
    public ResponseEntity<RutasResponseDto> buscarPorId(
            @Parameter(description = "Identificador de la ruta a recuperar", example = "1")
            @PathVariable Long id_ruta){
        return ResponseEntity.ok(this.rutasService.buscarRutaPorId(id_ruta));
    }

    @PostMapping
    public ResponseEntity<RutasResponseDto> agregarNuevaRuta(@RequestBody @Valid RutasRequestDto rutasRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.rutasService.agregarNuevaRuta(rutasRequestDto));
    }

    @PutMapping("{id_ruta}")
    public ResponseEntity<RutasResponseDto> modificarRutaExistente(@PathVariable Long id_ruta, @RequestBody RutasRequestDto rutasRequestDto){
        return ResponseEntity.ok().body(this.rutasService.modificarRutaExistente(id_ruta, rutasRequestDto));
    }

    @DeleteMapping("{id_ruta}")
    public ResponseEntity<Void> eliminarRuta(@PathVariable Long id_ruta){
        this.rutasService.elminarRuta(id_ruta);
        return ResponseEntity.ok().build();
    }
}
