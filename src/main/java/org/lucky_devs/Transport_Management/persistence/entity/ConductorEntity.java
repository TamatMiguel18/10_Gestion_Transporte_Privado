package org.lucky_devs.Transport_Management.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.lucky_devs.Transport_Management.dominio.enums.Estado;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Conductores")
@Data
public class ConductorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_conductor")
    private Long id_conductor;

    @Column (name = "licencia", length = 100, nullable = false)
    private String licencia;

    @Column (name = "fecha_nacimiento", nullable = false)
    private Date fecha_nacimineto;

    @Column (name = "estado",nullable = false)
    private Estado estado;

    @Column (name = "antiguedad", nullable = false)
    private Long antiguedad;

}
