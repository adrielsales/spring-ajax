package com.curso.springajax.web.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.curso.springajax.domain.Categoria;
import com.curso.springajax.domain.Promocao;
import com.curso.springajax.repository.CategoriaRepository;
import com.curso.springajax.repository.PromocaoRepository;

@Controller
@RequestMapping("/promocao")
public class PromocaoController {
	
	private static Logger log = org.slf4j.LoggerFactory.getLogger(PromocaoController.class);
	
	@Autowired
	private PromocaoRepository promocaoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@PostMapping("/save")
	public ResponseEntity<Promocao> salvar(Promocao promocao){
		
		log.info("Promocao {}", promocao.toString());
		promocao.setDtCadastro(LocalDateTime.now());
		promocaoRepository.save(promocao);
		return ResponseEntity.ok().build();
	}
	
	@ModelAttribute("categorias")
	private List<Categoria> getCategorias(){
		
		return categoriaRepository.findAll();
	}
	
	@GetMapping("/add")
	public String abrirCadastro() {
		
		return "promo-add";
	}

}
