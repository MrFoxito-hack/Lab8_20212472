package com.example.lab8_20212472.controller;

import com.example.lab8_20212472.entity.MiembroTripulacion;
import com.example.lab8_20212472.service.MiembroTripulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/tripulacion")
public class MiembroTripulacionController {
    
    @Autowired
    private MiembroTripulacionService miembroTripulacionService;
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("miembros", miembroTripulacionService.listar());
        return "tripulacion/lista";
    }
    
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("miembro", new MiembroTripulacion());
        model.addAttribute("especialidades", new String[]{"Piloto", "Científico", "Ingeniero", "Médico"});
        return "tripulacion/formulario";
    }
    
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        return miembroTripulacionService.buscarPorId(id)
            .map(miembro -> {
                model.addAttribute("miembro", miembro);
                model.addAttribute("especialidades", new String[]{"Piloto", "Científico", "Ingeniero", "Médico"});
                return "tripulacion/formulario";
            })
            .orElse("redirect:/tripulacion");
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute MiembroTripulacion miembro, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("especialidades", new String[]{"Piloto", "Científico", "Ingeniero", "Médico"});
            return "tripulacion/formulario";
        }
        miembroTripulacionService.guardar(miembro);
        return "redirect:/tripulacion";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        miembroTripulacionService.eliminar(id);
        return "redirect:/tripulacion";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        return miembroTripulacionService.buscarPorId(id)
            .map(miembro -> {
                model.addAttribute("miembro", miembro);
                return "tripulacion/detalle";
            })
            .orElse("redirect:/tripulacion");
    }
}
