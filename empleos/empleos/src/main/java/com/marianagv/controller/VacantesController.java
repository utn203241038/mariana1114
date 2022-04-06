package com.marianagv.controller;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marianagv.model.Vacante;
import com.marianagv.utileria.Utileria;
import com.marianagvservice.IntServiceCategorias;
import com.marianagvservice.IntServiceVacantes;

@Controller
@RequestMapping("/vacantes")
public class VacantesController {
	
	@Autowired
	private IntServiceVacantes serviceVacantes;
	
	@Autowired
	private IntServiceCategorias serviceCategorias;
	
	@ModelAttribute
	public void setGenero(Model model) {
		model.addAttribute("categorias",serviceCategorias.obtenerCategorias());
		model.addAttribute("vacantes", serviceVacantes.obtenerTodas());	
	}
	
	@GetMapping("/consulta")
	public String consulta(@RequestParam("id") int idVacante, Model model) {
		Vacante vacante = serviceVacantes.buscarPorId(idVacante);
		model.addAttribute("categorias",serviceCategorias.obtenerCategorias());
		model.addAttribute("vacante",vacante);
		return "vacantes/formVacante";
	}
	
	@GetMapping("/mostrar")
	public String mostrar(@RequestParam("id") Integer id, Model model) {
		Vacante vac = new  Vacante();
		vac = serviceVacantes.buscarPorId(id);
		model.addAttribute("vacante", vac);
		return "vacantes/detalleVacantes";
	}
	
	@PostMapping("/guardar2")
	public String guardar2(Vacante vacante, BindingResult result, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {
		if (result.hasErrors()) {
			for (ObjectError error: result.getAllErrors()){
				System.out.println("Ocurrio un error: "+ error.getDefaultMessage());
				}
			return "vacantes/formVacante";
		}
		if (!multiPart.isEmpty()) {
			String ruta = "C:\\empleos\\img-vacantes\\"; // Windows
			String nombreImagen = Utileria.guardarArchivo(multiPart, ruta);
			if (nombreImagen != null){
			vacante.setImagen(nombreImagen);
			}}
		vacante.setCategoria((serviceCategorias.buscarPorId(vacante.getCategoria().getId())));
		System.out.println(vacante);
		attributes.addFlashAttribute("msg", "Registro Guardado");
		serviceVacantes.guardar(vacante);
		return "redirect:/vacantes/indexPaginate";
	}
	
	@GetMapping("/nueva")
	public String nuevaVacante(Vacante vacante, Model model) {
		model.addAttribute("categorias", serviceCategorias.obtenerCategorias());
		return "vacantes/formVacante";
	}
	
	@GetMapping("/eliminar")
	public String eliminar(@RequestParam("id") int idVacante) {
		serviceVacantes.eliminar(idVacante);
		return "redirect:/vacantes/indexPaginate";
	}
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Vacante> vacantes = new LinkedList<Vacante>();
		vacantes = serviceVacantes.obtenerTodas();
		for(Vacante v : vacantes) {
			System.out.println(v);
		}
		model.addAttribute("total", serviceVacantes.obtenerTodas().size());
		return "vacantes/listaVacantes";
	}
	
	@GetMapping(value = "/indexPaginate")
	public String mostrarIndexPaginado(Model model, Pageable page) {
		Page<Vacante> lista = serviceVacantes.buscarTodas(page);
		model.addAttribute("total", serviceVacantes.numeroRegistros());
		model.addAttribute("vacantes", lista);
		return "vacantes/listaVacantes";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException{
				setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern("dd-MM-yyyy")));
			}
			
			@Override
			public String getAsText() throws IllegalArgumentException {
				return DateTimeFormatter.ofPattern("dd-MM-yyyy").format((LocalDate) getValue());
			}
		});
	}
}
  