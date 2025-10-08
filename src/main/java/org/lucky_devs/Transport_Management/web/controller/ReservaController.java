package org.lucky_devs.Transport_Management.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.lucky_devs.Transport_Management.dominio.dto.ModReservaDto;
import org.lucky_devs.Transport_Management.dominio.dto.ReservaRequestDto;
import org.lucky_devs.Transport_Management.dominio.dto.ReservaResponseDto;
import org.lucky_devs.Transport_Management.dominio.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/Reservas")
@Tag(name = "Reservas", description = "Operaciones CRUD para la gestión de reservas de transporte.")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    @Operation(summary = "Listar todas las reservas", description = "Recupera todas las reservas registradas en el sistema.")
    public ResponseEntity<List<ReservaResponseDto>> listarReservas(){
        return ResponseEntity.ok(this.reservaService.listaReservas());
    }

    @GetMapping("{idReserva}")
    @Operation(
            summary = "Obtener reserva por su ID",
            description = "Retorna una reserva específica utilizando su identificador único.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Reserva encontrada"),
                    @ApiResponse(responseCode = "404", description = "Reserva no encontrada", content = @Content)
            })
    public ResponseEntity<ReservaResponseDto> buscarPorId(
            @Parameter(description = "Identificador de la reserva a recuperar", example = "105")
            @PathVariable Long idReserva){
        return ResponseEntity.ok(this.reservaService.buscarReservaPorId(idReserva));
    }

    @PostMapping
    @Operation(summary = "Crear nueva reserva", description = "Crea una nueva reserva de transporte en la base de datos.")
    public ResponseEntity<ReservaResponseDto> agregarNuevaReserva(@RequestBody @Valid ReservaRequestDto reservaRequestDto){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.reservaService.agregarNuevaReserva(reservaRequestDto));
    }

    @PutMapping("{idReserva}")
    @Operation(summary = "Modificar reserva existente", description = "Actualiza los datos de una reserva identificada por su ID.")
    public ResponseEntity<ReservaResponseDto> modificarReservaExistente(
            @PathVariable Long idReserva,
            @RequestBody @Valid ModReservaDto modReservaDto){
        return ResponseEntity
                .ok()
                .body(this.reservaService.modificarReservaExistente(idReserva, modReservaDto));
    }

    @DeleteMapping("{idReserva}")
    @Operation(summary = "Eliminar reserva", description = "Elimina permanentemente una reserva del sistema usando su ID.")
    public ResponseEntity<Void> eliminarReserva(@PathVariable Long idReserva){
        this.reservaService.eliminarReserva(idReserva);
        return ResponseEntity.ok().build();
    }
}
