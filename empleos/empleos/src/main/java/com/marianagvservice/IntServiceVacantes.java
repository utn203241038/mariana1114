package com.marianagvservice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.marianagv.model.Vacante;



public interface IntServiceVacantes {
	public List<Vacante> obtenerTodas();
	public void eliminar(Integer idVacante);
	public Vacante buscarPorId(Integer idVacante);
	public int numeroRegistros();
	public void guardar(Vacante vacante);
	public Page<Vacante> buscarTodas(Pageable page);
}
