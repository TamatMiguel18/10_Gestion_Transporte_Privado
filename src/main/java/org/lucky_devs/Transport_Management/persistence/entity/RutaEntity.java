package org.lucky_devs.Transport_Management.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Rutas")
@Data
public class RutaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ruta")
    private Long id_ruta;
    @ManyToOne
    @JoinColumn(name = "id_conductor")
    private ConductorEntity  conductor;
    @ManyToOne
    @JoinColumn(name = "id_unidad")
    private UnidadEntity unidad;
    @Column(name = "direccion_destino",length = 255, nullable = false)
    private String direccion_destino;
}
