package org.lucky_devs.Transport_Management.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.lucky_devs.Transport_Management.dominio.dto.TarifasDto;
import org.lucky_devs.Transport_Management.dominio.enums.Tipo_Unidad;
import org.lucky_devs.Transport_Management.dominio.service.TarifasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Tarifas")
@Tag(name = "Tarifas", description = "Operaciones CRUD para la gestión de tarifas de transporte.")
public class TarifasController {

    private final TarifasService tarifasService;

    public TarifasController(TarifasService tarifasService) {
        this.tarifasService = tarifasService;
    }

    @GetMapping
    @Operation(summary = "Listar todas las tarifas")
    public ResponseEntity<List<TarifasDto>> listarTarifas() {
        List<TarifasDto> lista = this.tarifasService.listarTarifas();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{tipo_Unidad}")
    @Operation(
            summary = "Buscar una tarifa por ID",
            description = "Obtiene los detalles de una tarifa usando su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tarifa encontrada"),
                    @ApiResponse(responseCode = "404", description = "Tarifa no encontrada", content = @Content)
            })
    public ResponseEntity<TarifasDto> buscarPorId(
            @Parameter(description = "ID de la tarifa", example = "1")
            @PathVariable Long id_tarifa) {
        TarifasDto tarifa = this.tarifasService.buscarPorTipoUnidad(Tipo_Unidad.BUS);
        return ResponseEntity.ok(tarifa);
    }

    @PostMapping
    @Operation(summary = "Registrar una nueva tarifa")
    public ResponseEntity<TarifasDto> registrarTarifa(@RequestBody @Valid TarifasDto tarifaDto) {
        TarifasDto nueva = this.tarifasService.agregarTarifa(tarifaDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id_tarifa}")
    @Operation(summary = "Modificar una tarifa existente")
    public ResponseEntity<TarifasDto> modificarTarifa(
            @PathVariable Long id_tarifa,
            @RequestBody TarifasDto tarifaModificada) {
        TarifasDto actualizada = this.tarifasService.modificarTarifa(id_tarifa, tarifaModificada);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id_tarifa}")
    @Operation(summary = "Eliminar una tarifa")
    public ResponseEntity<Void> eliminarTarifa(@PathVariable Long id_tarifa) {
        this.tarifasService.eliminarTarifa(id_tarifa);
        return ResponseEntity.ok().build();
    }
}
