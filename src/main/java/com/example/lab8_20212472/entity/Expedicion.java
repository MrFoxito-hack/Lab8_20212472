package com.example.lab8_20212472.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//usamos loombock

@Entity
@Table(name = "expeditions")
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Expedicion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre de la mision dber ser obligatorio")



    @Column(nullable = false)
    private String nombreMision;
    @NotNull(message = "El planeta destino debe ser obligatorio")


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planet_id", nullable = false)
    private Planeta planetaDestino;
      @NotNull(message = "La fecha de lanzamiento es obligatoria")

    @Column(nullable = false)
    private LocalDateTime fechaLanzamiento;
    @NotBlank(message = "El estado es obligatoria")

    @Column(nullable = false)
    private String estado;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "expedition_crew",
        joinColumns = @JoinColumn(name = "expedition_id"),
        inverseJoinColumns = @JoinColumn(name = "crew_member_id")
    )
    private Set<MiembroTripulacion> tripulacion = new HashSet<>();

    @Lob
    @Column(columnDefinition = "TEXT")
    private String objetivos;
    
    @Lob
    @Column(columnDefinition = "TEXT")
    private String resultados;



    public Expedicion(String nombreMision, Planeta planetaDestino, LocalDateTime fechaLanzamiento, 
                     String estado, String objetivos) {        this.nombreMision = nombreMision;
        this.planetaDestino = planetaDestino;
        this.fechaLanzamiento = fechaLanzamiento;
        this.estado = estado;
        this.objetivos = objetivos;
    }
      public void agregarMiembroTripulacion(MiembroTripulacion miembroTripulacion) {
        tripulacion.add(miembroTripulacion);
        miembroTripulacion.getExpediciones().add(this);
    }
    
    public void removerMiembroTripulacion(MiembroTripulacion miembroTripulacion) {
        tripulacion.remove(miembroTripulacion);
        miembroTripulacion.getExpediciones().remove(this);
    }
}
