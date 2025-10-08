package org.lucky_devs.Transport_Management.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.lucky_devs.Transport_Management.dominio.enums.Estado_Operativo;
import org.lucky_devs.Transport_Management.dominio.enums.Tipo_Unidad;

@Entity
@Table(name = "Unidades_Transporte")
@Data
public class UnidadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad")
    private Long id_unidad;
    @Column(name = "placa", length = 7, nullable = false, unique = true)
    private String placa;
    @Column(name = "modelo", length = 32, nullable = false)
    private String modelo;
    @Column(name = "capacidad", precision = 75, nullable = false)
    private Long capacidad;
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_unidad", nullable = false)
    private Tipo_Unidad tipo_unidad;
    @Enumerated(EnumType.STRING)
    @Column(name = "estado_operativo", nullable = false)
    private Estado_Operativo estado_operativo;
}
