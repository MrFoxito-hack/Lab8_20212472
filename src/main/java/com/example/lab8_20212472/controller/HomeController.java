package com.example.lab8_20212472.controller;

import com.example.lab8_20212472.service.ExpedicionService;
import com.example.lab8_20212472.service.MiembroTripulacionService;
import com.example.lab8_20212472.service.PlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private PlanetaService planetaService;

    @Autowired
    private MiembroTripulacionService miembroTripulacionService;

    @Autowired
    private ExpedicionService expedicionService;

    @GetMapping("/")
    public String mostrarDashboard(Model model) {
        // Estadísticas para el dashboard
        model.addAttribute("totalPlanetas", planetaService.contarTodos());
        model.addAttribute("totalTripulacion", miembroTripulacionService.contarTodos());
        model.addAttribute("totalExpediciones", expedicionService.contarTodos());
        model.addAttribute("expedicionesActivas", expedicionService.contarPorEstado("En Curso"));
        
        return "dashboard";
    }
}