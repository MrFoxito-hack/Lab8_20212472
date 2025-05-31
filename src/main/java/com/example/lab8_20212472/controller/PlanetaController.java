package com.example.lab8_20212472.controller;

import com.example.lab8_20212472.entity.Planeta;
import com.example.lab8_20212472.service.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/planetas")
public class PlanetaController {
    
    @Autowired
    private PlanetaService planetaService;
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("planetas", planetaService.listar());
        return "planetas/lista";
    }
    
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("planeta", new Planeta());
        return "planetas/formulario";
    }
    
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        return planetaService.buscarPorId(id)
            .map(planeta -> {
                model.addAttribute("planeta", planeta);
                return "planetas/formulario";
            })
            .orElse("redirect:/planetas");
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Planeta planeta, BindingResult result) {
        if (result.hasErrors()) {
            return "planetas/formulario";
        }
        planetaService.guardar(planeta);
        return "redirect:/planetas";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        planetaService.eliminar(id);
        return "redirect:/planetas";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        return planetaService.buscarPorId(id)
            .map(planeta -> {
                model.addAttribute("planeta", planeta);
                return "planetas/detalle";
            })
            .orElse("redirect:/planetas");
    }
}
