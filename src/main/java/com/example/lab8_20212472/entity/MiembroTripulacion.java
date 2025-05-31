package com.example.lab8_20212472.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//usamos loombok

@Entity
@Table(name = "crew_members")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MiembroTripulacion {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre completo es obligatorio")
    @Column(nullable = false)
    private String nombreCompleto;
    
    @NotBlank(message = "La especialidad es obligatoria")
    @Column(nullable = false)
    private String especialidad;
    
    private String rango;
    
    @NotNull(message = "La fecha de contratación es obligatoria")
    @Column(nullable = false)
    private LocalDate fechaContratacion;
    
    @ManyToMany(mappedBy = "tripulacion")
    private Set<Expedicion> expediciones = new HashSet<>();
    
    public MiembroTripulacion(String nombreCompleto, String especialidad, String rango, LocalDate fechaContratacion) {
        this.nombreCompleto = nombreCompleto;
        this.especialidad = especialidad;
        this.rango = rango;
        this.fechaContratacion = fechaContratacion;
    }
}
