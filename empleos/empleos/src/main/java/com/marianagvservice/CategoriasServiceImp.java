package com.marianagvservice;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.marianagv.model.Categoria;

@Service
public class CategoriasServiceImp implements IntServiceCategorias {
	
	private List<Categoria> lista = null;
	
	public CategoriasServiceImp() {
		lista = new LinkedList<Categoria>();
		Categoria c1 = new Categoria();
		c1.setId(1);
		c1.setNombre("Programadores");
		c1.setDescripcion("Desarollo de aplicaciones web y movil");
		lista.add(c1);
		////////////////////////////////////////////////////////////////
		Categoria c2 = new Categoria();
		c2.setId(2);
		c2.setNombre("Ingenieros");
		c2.setDescripcion("Para realizar proyectos en diferentes ramas");
		lista.add(c2);
	}

	@Override
	public List<Categoria> obtenerCategorias() {
		return lista;
	}

	@Override
	public void guardar(Categoria categoria) {
		lista.add(categoria);
	}

	@Override
	public Categoria buscarPorId(Integer idCategoria) {
		for(Categoria cat : lista) {
			if(cat.getId() == idCategoria) {
				return cat;
			}
		}
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		lista.remove(buscarPorId(idCategoria));
		
	}

	@Override
	public Integer numeroCategorias() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Categoria> buscarTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

}
