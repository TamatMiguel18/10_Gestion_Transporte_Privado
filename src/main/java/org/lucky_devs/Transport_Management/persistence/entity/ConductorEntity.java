package org.lucky_devs.Transport_Management.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.lucky_devs.Transport_Management.dominio.enums.Estado;
import java.util.Date;

@Entity
@Table(name = "Conductores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConductorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conductor")
    private Long id_conductor;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "licencia", length = 100, nullable = false, unique = true)
    private String licencia;

    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fecha_nacimiento;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private Estado estado;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    public Long getId_conductor() {
        return id_conductor;
    }

    public void setId_conductor(Long id_conductor) {
        this.id_conductor = id_conductor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLicencia() {
        return licencia;
    }

    public void setLicencia(String licencia) {
        this.licencia = licencia;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
