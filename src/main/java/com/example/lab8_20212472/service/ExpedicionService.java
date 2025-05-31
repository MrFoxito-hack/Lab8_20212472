package com.example.lab8_20212472.service;

import com.example.lab8_20212472.entity.Expedicion;
import com.example.lab8_20212472.entity.MiembroTripulacion;
import com.example.lab8_20212472.repository.ExpedicionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpedicionService {
    
    @Autowired
    private ExpedicionRepository expedicionRepository;
    
    public List<Expedicion> listar() {
        return expedicionRepository.findAll();
    }
    
    public Optional<Expedicion> buscarPorId(Long id) {
        return expedicionRepository.findById(id);
    }
    
    public Expedicion guardar(Expedicion expedicion) {
        return expedicionRepository.save(expedicion);
    }
    
    public void eliminar(Long id) {
        expedicionRepository.deleteById(id);
    }
    
    public boolean validarExpedicion(Expedicion expedicion) {
        if (expedicion.getTripulacion().size() < 2) {
            return false;
        }
        
        boolean tienePiloto = expedicion.getTripulacion().stream()
            .anyMatch(m -> "Piloto".equals(m.getEspecialidad()));
        boolean tieneCientifico = expedicion.getTripulacion().stream()
            .anyMatch(m -> "Científico".equals(m.getEspecialidad()));
        
        return tienePiloto && tieneCientifico;
    }
    
    public long contarTodos() {
        return expedicionRepository.count();
    }
    
    public long contarPorEstado(String estado) {
        return expedicionRepository.contarPorEstado(estado);
    }
    
    public List<Expedicion> buscarPorEstado(String estado) {
        return expedicionRepository.findByEstado(estado);
    }
    
    public List<Expedicion> buscarFuturas() {
        return expedicionRepository.encontrarExpedicionesFuturas();
    }
}
