package com.example.lab8_20212472.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


//usamos loombok
@Entity
@Table(name = "planets")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Planeta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre debe ser obligatorio")
    @Column(nullable = false, unique = true)
    private String nombre;
    
    @NotBlank(message = "El tipo de planeta debe serr obligatorio")
    @Column(nullable = false)
    private String tipoPlaneta;
    
    @NotNull(message = "La habitabilidad de be ser obligtoria")
    @Column(nullable = false)
    private Boolean habitable;
    
    @NotNull(message = "La gravedad  es obligatoria")
    @Column(nullable = false)
    private Double gravedadRelativa;
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String descripcion;
    
    public Planeta(String nombre, String tipoPlaneta, Boolean habitable, Double gravedadRelativa, String descripcion) {
        this.nombre = nombre;
        this.tipoPlaneta = tipoPlaneta;
        this.habitable = habitable;
        this.gravedadRelativa = gravedadRelativa;
        this.descripcion = descripcion;
    }
}

