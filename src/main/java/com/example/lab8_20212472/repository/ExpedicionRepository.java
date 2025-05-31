package com.example.lab8_20212472.repository;

import com.example.lab8_20212472.entity.Expedicion;
import com.example.lab8_20212472.entity.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpedicionRepository extends JpaRepository<Expedicion, Long> {
    
    // Buscar por estado
    List<Expedicion> findByEstado(String estado);
    
    // Buscar por planeta destino
    List<Expedicion> findByPlanetaDestino(Planeta planeta);
    
    // Buscar por nombre de misión que contenga texto
    List<Expedicion> findByNombreMisionContainingIgnoreCase(String nombre);
    
    // Buscar expediciones entre fechas
    List<Expedicion> findByFechaLanzamientoBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    // Buscar expediciones futuras
    @Query("SELECT e FROM Expedicion e WHERE e.fechaLanzamiento > CURRENT_TIMESTAMP ORDER BY e.fechaLanzamiento ASC")
    List<Expedicion> encontrarExpedicionesFuturas();
    
    // Contar expediciones por estado
    @Query("SELECT COUNT(e) FROM Expedicion e WHERE e.estado = :estado")
    Long contarPorEstado(@Param("estado") String estado);
    
    // Buscar expediciones con tripulación específica
    @Query("SELECT e FROM Expedicion e JOIN e.tripulacion t WHERE t.id = :miembroId")
    List<Expedicion> encontrarPorMiembroTripulacion(@Param("miembroId") Long miembroId);
}