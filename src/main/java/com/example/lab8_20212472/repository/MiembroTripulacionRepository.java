package com.example.lab8_20212472.repository;

import com.example.lab8_20212472.entity.MiembroTripulacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MiembroTripulacionRepository extends JpaRepository<MiembroTripulacion, Long> {
    
    // Buscar por especialidad
    List<MiembroTripulacion> findByEspecialidad(String especialidad);
    
    // Buscar miembros activos
    List<MiembroTripulacion> findByActivoTrue();
    
    // Buscar miembros inactivos
    List<MiembroTripulacion> findByActivoFalse();
    
    // Buscar por nombre que contenga texto
    List<MiembroTripulacion> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar miembros con experiencia mayor a un valor
    @Query("SELECT m FROM MiembroTripulacion m WHERE m.anosExperiencia >= :experiencia")
    List<MiembroTripulacion> encontrarPorExperienciaMayorA(@Param("experiencia") Integer experiencia);
    
    // Contar miembros activos por especialidad
    @Query("SELECT COUNT(m) FROM MiembroTripulacion m WHERE m.especialidad = :especialidad AND m.activo = true")
    Long contarActivosPorEspecialidad(@Param("especialidad") String especialidad);
}