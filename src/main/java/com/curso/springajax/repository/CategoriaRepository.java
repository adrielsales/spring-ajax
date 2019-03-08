package com.curso.springajax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.curso.springajax.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
