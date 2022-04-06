package com.marianagv.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.marianagv.model.Vacante;
import com.marianagvservice.IntServiceVacantes;

@Controller
public class HomeController {
	
	@Autowired
	private IntServiceVacantes serviceVacantes;
	
	@GetMapping("/acerca")
	public String acerca() {
		return "acerca";
	}
	
	@GetMapping("/")
	public String home(Model model) {
		List<Vacante> vacantes = new LinkedList<Vacante>();
		vacantes = serviceVacantes.obtenerTodas();
		for(Vacante v : vacantes) {
			System.out.println(v);
		}
		model.addAttribute("vacantes", vacantes);
		return "home";
	}
	
	@GetMapping("/mostrar")
	public String mostrar(@RequestParam("id") Integer id, Model model) {
		Vacante vac = new  Vacante();
		vac = serviceVacantes.buscarPorId(id);
		model.addAttribute("vacante", vac);
		return "vacantes/detalleVacantes";
	}
}
