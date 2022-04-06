package com.marianagvservice;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marianagv.model.Categoria;
import com.marianagv.model.Vacante;

@Service
public class VacantesServiceImp implements IntServiceVacantes {
	
	private List<Vacante> lista = null;
	
	public VacantesServiceImp() {
		lista = new LinkedList<Vacante>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try {
			Categoria c1 = new Categoria();
			Vacante v1 = new Vacante();
			c1.setId(1);
			c1.setNombre("Programadores");
			c1.setDescripcion("Desarollo de aplicaciones web y movil");
			v1.setCategoria(c1);
			v1.setId(1);
			v1.setNombre("Programador Web");
			v1.setDescripcion("Desarrollo de aplicaciones web");
			v1.setFecha(LocalDate.parse("12-01-2022", dtf));
			v1.setSalario(12000.0);
			v1.setDestacado(0);
			v1.setEstatus("Creada");
			v1.setDetalles("<h1>Prestaciones de ley</h1><ul><li>Capacitación</li><li>Transprote</li><li>Bono de productividad</li></ul>");
			/////////////////////////////////////////////////////////
			Vacante v2 = new Vacante();
			Categoria c2 = new Categoria();
			c2.setId(2);
			c2.setNombre("Ingenieros");
			c2.setDescripcion("Para realizar proyectos en diferentes ramas");
			v2.setCategoria(c2);
			v2.setId(2);
			v2.setNombre("Programador Web");
			v2.setDescripcion("Desarrollo de aplicaciones web");
			v2.setFecha(LocalDate.parse("12-01-2022", dtf));
			v2.setSalario(12000.0);
			v2.setDestacado(1);
			v2.setEstatus("Aprobada");
			v2.setImagen("logo10.png");
			v2.setDetalles("<h1>Prestaciones de ley</h1><ul><li>Capacitación</li><li>Transprote</li><li>Bono de productividad</li></ul>");
			lista.add(v1);
			lista.add(v2);
			////////////////////////////////////////
		}catch(DateTimeParseException ex) {
			System.out.println("Error: " + ex.getMessage());
		}
	}
	@Override
	public List<Vacante> obtenerTodas() {
		return lista;
	}

	@Override
	public void eliminar(Integer idVacante) {
		lista.remove(buscarPorId(idVacante));

	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		int i = 0;
		while(i < lista.size()) {
			Vacante v = lista.get(i);
			if(v.getId() == idVacante) {
				return lista.get(i);
			}
			i++;
		}
		return null;
	}

	@Override
	public int numeroRegistros() {
		return lista.size();
	}
	@Override
	public void guardar(Vacante vacante) {
		lista.add(vacante);
	}
	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}
}