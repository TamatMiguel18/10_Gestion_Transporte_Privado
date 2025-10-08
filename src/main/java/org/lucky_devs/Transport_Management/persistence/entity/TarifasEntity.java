package org.lucky_devs.Transport_Management.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.lucky_devs.Transport_Management.dominio.enums.Tipo_Unidad;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tarifas")
@Data
public class TarifasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tarifa")
    private Long id_tarifa;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_unidad", nullable = false)
    private Tipo_Unidad tipoUnidad;

    @Column(name = "tarifa_base", nullable = false)
    private Double tarifa_base;

    @Column(name = "recargo", nullable = false)
    private Double recargo;

    @Column(name = "hora_inicio_pico", nullable = false)
    private LocalDateTime hora_inicio_pico;
}
