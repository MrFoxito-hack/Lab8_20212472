package com.example.lab8_20212472.service;

import com.example.lab8_20212472.entity.MiembroTripulacion;
import com.example.lab8_20212472.repository.MiembroTripulacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiembroTripulacionService {
    
    @Autowired
    private MiembroTripulacionRepository miembroTripulacionRepository;
    
    public List<MiembroTripulacion> listar() {
        return miembroTripulacionRepository.findAll();
    }
    
    public Optional<MiembroTripulacion> buscarPorId(Long id) {
        return miembroTripulacionRepository.findById(id);
    }
    
    public MiembroTripulacion guardar(MiembroTripulacion miembro) {
        return miembroTripulacionRepository.save(miembro);
    }
    
    public void eliminar(Long id) {
        miembroTripulacionRepository.deleteById(id);
    }
    
    public List<MiembroTripulacion> buscarPorEspecialidad(String especialidad) {
        return miembroTripulacionRepository.findByEspecialidad(especialidad);
    }
    
    public long contarTodos() {
        return miembroTripulacionRepository.count();
    }
    
    public List<MiembroTripulacion> buscarActivos() {
        return miembroTripulacionRepository.findByActivoTrue();
    }
    
    public long contarActivosPorEspecialidad(String especialidad) {
        return miembroTripulacionRepository.contarActivosPorEspecialidad(especialidad);
    }
}
