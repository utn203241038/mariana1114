package com.marianagv.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marianagv.model.Categoria;
import com.marianagv.repository.CategoriasRepository;
import com.marianagvservice.IntServiceCategorias;

@Service
@Primary
public class CategoriasServiceCrud implements IntServiceCategorias{

	@Autowired
	private CategoriasRepository repoCategorias;
	
	@Override
	public List<Categoria> obtenerCategorias() {
		// TODO Auto-generated method stub
		return (List<Categoria>) repoCategorias.findAll();
	}

	@Override
	public void guardar(Categoria categoria) {
		repoCategorias.save(categoria);
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		Optional<Categoria> optional = repoCategorias.findById(idCategoria);
			if(optional.isPresent()) {
				return optional.get();
			}
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		repoCategorias.deleteById(idCategoria);	
	}

	@Override
	public Integer numeroCategorias() {
		// TODO Auto-generated method stub
		return (int)repoCategorias.count();
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return (Page<Categoria>) repoCategorias.findAll(page);
	}

}
