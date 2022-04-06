package com.marianagv.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marianagv.model.Categoria;


//public interface CategoriasRepository extends CrudRepository<Categoria, Integer> 
public interface CategoriasRepository extends JpaRepository<Categoria, Integer>{

}