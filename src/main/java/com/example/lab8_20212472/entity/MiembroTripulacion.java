package com.example.lab8_20212472.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
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
    
    @NotBlank(message = "El nombre debe ser obligatorio")
    @Column(nullable = false)
    private String nombre;
    
    @NotBlank(message = "La especialidad dbe ser obligatoria")
    @Column(nullable = false)
    private String especialidad;
    
    @NotNull(message = "La edad debe ser obligatoria")
    @Min(value = 18, message = " edad mínima  18 años")
    @Max(value = 65, message = " edad maaxima  65 años")
    @Column(nullable = false)
    private Integer edad;
    
    @NotNull(message = "Debe tener años de experiencia")
    @Min(value = 0, message = "invalidoo")
    @Column(nullable = false)
    private Integer anosExperiencia;
    
    @Column(nullable = false)
    private Boolean disponible = true;
    
    @ManyToMany(mappedBy = "tripulacion")
    private Set<Expedicion> expediciones = new HashSet<>();
    
    public MiembroTripulacion(String nombre, String especialidad, Integer edad, Integer anosExperiencia, Boolean disponible) {
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.edad = edad;
        this.anosExperiencia = anosExperiencia;
        this.disponible = disponible;
    }
    
    public Set<Expedicion> getExpediciones() {
        if (expediciones == null) {
            expediciones = new HashSet<>();
        }
        return expediciones;
    }
}
