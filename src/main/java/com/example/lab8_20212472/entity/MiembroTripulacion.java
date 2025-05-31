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
    
    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;
    
    @NotBlank(message = "La especialidad es obligatoria")
    @Column(nullable = false)
    private String especialidad;
    
    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad mínima es 18 años")
    @Max(value = 65, message = "La edad máxima es 65 años")
    @Column(nullable = false)
    private Integer edad;
    
    @NotNull(message = "Los años de experiencia son obligatorios")
    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
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
