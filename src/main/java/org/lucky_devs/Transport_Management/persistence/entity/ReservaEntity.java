package org.lucky_devs.Transport_Management.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.lucky_devs.Transport_Management.dominio.enums.EstadoReserva;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Reservas")
@Data
public class ReservaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reserva")
    private Long idReserva;

    @Column(name = "id_tarifa", nullable = false)
    private Long idTarifa;

    @Column(name = "id_cliente", nullable = false)
    private Long idCliente;

    @Column(name = "id_ruta", nullable = false)
    private Long idRuta;

    @Column(name = "fecha_hora_inicio", nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(name = "fecha_hora_fin", nullable = false)
    private LocalDateTime fechaHoraFin;

    @Column(name = "direccion_origen", length = 255, nullable = false)
    private String direccionOrigen;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_viaje", nullable = false)
    private EstadoReserva estadoViaje;

    @Column(name = "precio_final", nullable = false)
    private BigDecimal precioFinal;
}
