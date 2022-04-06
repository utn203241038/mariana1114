package com.marianagv.service.db;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marianagv.model.Vacante;
import com.marianagv.repository.VacantesRepository;
import com.marianagvservice.IntServiceVacantes;

@Service
@Primary
public class VacantesServiceCrud implements IntServiceVacantes{

	@Autowired
	private VacantesRepository repoVacantes;

	@Override
	public List<Vacante> obtenerTodas() {
		// TODO Auto-generated method stub
		return (List<Vacante>) repoVacantes.findAll();
	}

	@Override
	public Vacante buscarPorId(Integer idVacante) {
		Optional<Vacante> optional = repoVacantes.findById(idVacante);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public int numeroRegistros() {
		// TODO Auto-generated method stub
		return (int)repoVacantes.count();
	}

	@Override
	public void guardar(Vacante vacante) {
		repoVacantes.save(vacante);
	}

	@Override
	public void eliminar(Integer idVacante) {
		repoVacantes.deleteById(idVacante);
	}

	@Override
	public Page<Vacante> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return (Page<Vacante>) repoVacantes.findAll(page);
	}
}

