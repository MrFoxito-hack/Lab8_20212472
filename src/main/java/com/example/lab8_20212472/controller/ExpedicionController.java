package com.example.lab8_20212472.controller;

import com.example.lab8_20212472.entity.Expedicion;
import com.example.lab8_20212472.entity.Planeta;
import com.example.lab8_20212472.entity.MiembroTripulacion;
import com.example.lab8_20212472.service.ExpedicionService;
import com.example.lab8_20212472.service.PlanetaService;
import com.example.lab8_20212472.service.MiembroTripulacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/expediciones")
public class ExpedicionController {
    
    @Autowired
    private ExpedicionService expedicionService;
    
    @Autowired
    private PlanetaService planetaService;
    
    @Autowired
    private MiembroTripulacionService miembroTripulacionService;
    
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("expediciones", expedicionService.listar());
        return "expediciones/lista";
    }
    
    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("expedicion", new Expedicion());
        model.addAttribute("planetas", planetaService.listar());
        model.addAttribute("miembros", miembroTripulacionService.listar());
        model.addAttribute("estados", new String[]{"Planificada", "En Curso", "Completada", "Cancelada"});
        return "expediciones/formulario";
    }
    
    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        return expedicionService.buscarPorId(id)
            .map(expedicion -> {
                model.addAttribute("expedicion", expedicion);
                model.addAttribute("planetas", planetaService.listar());
                model.addAttribute("miembros", miembroTripulacionService.listar());
                model.addAttribute("estados", new String[]{"Planificada", "En Curso", "Completada", "Cancelada"});
                return "expediciones/formulario";
            })
            .orElse("redirect:/expediciones");
    }
    
    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute Expedicion expedicion, BindingResult result, 
                         Model model, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("planetas", planetaService.listar());
            model.addAttribute("miembros", miembroTripulacionService.listar());
            model.addAttribute("estados", new String[]{"Planificada", "En Curso", "Completada", "Cancelada"});
            return "expediciones/formulario";
        }

        
        if (!expedicionService.validarExpedicion(expedicion)) {
            redirectAttributes.addFlashAttribute("error", 
                "La expedición debe tener al menos un Piloto y un Científico en la tripulación");
            return "redirect:/expediciones/nuevo";
        }
        
        expedicionService.guardar(expedicion);
        redirectAttributes.addFlashAttribute("success", "Expedición guardada exitosamente");
        return "redirect:/expediciones";
    }
    
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        expedicionService.eliminar(id);
        return "redirect:/expediciones";
    }
    
    @GetMapping("/detalle/{id}")
    public String detalle(@PathVariable Long id, Model model) {
        return expedicionService.buscarPorId(id)
            .map(expedicion -> {
                model.addAttribute("expedicion", expedicion);
                return "expediciones/detalle";
            })
            .orElse("redirect:/expediciones");
    }
}

