package com.example.lab8_20212472.repository;

import com.example.lab8_20212472.entity.Planeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetaRepository extends JpaRepository<Planeta, Long> {
    
    // Buscar planetas por tipo
    List<Planeta> findByTipoPlaneta(String tipoPlaneta);
    
    // Buscar planetas habitables
    List<Planeta> findByHabitableTrue();
    
    // Buscar planetas no habitables
    List<Planeta> findByHabitableFalse();
    
    // Buscar por nombre que contenga texto
    List<Planeta> findByNombreContainingIgnoreCase(String nombre);
    
    // Buscar planetas con gravedad mayor a un valor
    @Query("SELECT p FROM Planeta p WHERE p.gravedadRelativa > :gravedad")
    List<Planeta> encontrarPorGravedadMayorA(@Param("gravedad") Double gravedad);
}