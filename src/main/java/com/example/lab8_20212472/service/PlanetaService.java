package com.example.lab8_20212472.service;

import com.example.lab8_20212472.entity.Planeta;
import com.example.lab8_20212472.repository.PlanetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanetaService {
    
    @Autowired
    private PlanetaRepository planetaRepository;
    
    public List<Planeta> listar() {
        return planetaRepository.findAll();
    }
    
    public Optional<Planeta> buscarPorId(Long id) {
        return planetaRepository.findById(id);
    }
    
    public Planeta guardar(Planeta planeta) {
        return planetaRepository.save(planeta);
    }
    
    public void eliminar(Long id) {
        planetaRepository.deleteById(id);
    }
    
    public long contarTodos() {
        return planetaRepository.count();
    }
    
    public List<Planeta> buscarPorTipo(String tipoPlaneta) {
        return planetaRepository.findByTipoPlaneta(tipoPlaneta);
    }
    
    public List<Planeta> buscarHabitables() {
        return planetaRepository.findByHabitableTrue();
    }
}