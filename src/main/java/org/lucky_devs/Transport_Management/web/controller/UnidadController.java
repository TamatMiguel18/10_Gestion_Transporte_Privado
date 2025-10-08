package org.lucky_devs.Transport_Management.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.lucky_devs.Transport_Management.dominio.dto.ModUnidadDto;
import org.lucky_devs.Transport_Management.dominio.dto.UnidadRequestDto;
import org.lucky_devs.Transport_Management.dominio.dto.UnidadResponseDto;
import org.lucky_devs.Transport_Management.dominio.service.UnidadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Unidades")
@Tag(name = "Unidades de Transporte", description = "Operaciones CRUD para las unidades de transporte del sistema.")
public class UnidadController {

    private final UnidadService unidadService;

    public UnidadController(UnidadService unidadService) {
        this.unidadService = unidadService;
    }

    @GetMapping
    public ResponseEntity<List<UnidadResponseDto>> listaUnidades(){
        return ResponseEntity.ok(this.unidadService.listaUnidades());
    }

    @GetMapping("{placa}")
    @Operation(
            summary = "Obtener una unidad por su placa",
            description = "Retorna una unidad por su numero placa.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Unidad encontrada"),
                    @ApiResponse(responseCode = "404", description = "Unidad no encontrada", content = @Content)
            })
    public ResponseEntity<UnidadResponseDto> buscarPorPlaca(
            @Parameter(description = "Identificador de la unidad a recuperar", example = "P507GCL")
            @PathVariable String placa){
        return ResponseEntity.ok(this.unidadService.buscarPorPlaca(placa));
    }

    @PostMapping
    public ResponseEntity<UnidadResponseDto> agregarNuevaUnidad(@RequestBody @Valid UnidadRequestDto unidadRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.unidadService.agregarNuevaUnidad(unidadRequestDto));
    }

    @PutMapping("{placa}")
    public ResponseEntity<UnidadResponseDto> modificarUnidadExistente(@PathVariable String placa, @RequestBody ModUnidadDto modUnidadDto){
        return ResponseEntity.ok().body(this.unidadService.modificarUnidadExistente(placa, modUnidadDto));
    }

    @DeleteMapping("{placa}")
    public ResponseEntity<Void> eliminarUnidad(@PathVariable String placa){
        this.unidadService.eliminarUnidad(placa);
        return ResponseEntity.ok().build();
    }
}
