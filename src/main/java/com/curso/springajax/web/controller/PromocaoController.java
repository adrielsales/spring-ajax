package com.curso.springajax.web.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/list")
	public String listarOfertas(ModelMap model) {
		
		Sort sort = new Sort(Sort.Direction.DESC, "dtCadastro");
		PageRequest pageRequest = PageRequest.of(0, 8, sort);
		model.addAttribute("promocoes", promocaoRepository.findAll(pageRequest));
		return "promo-list";
	}
	
	@GetMapping("/list/ajax")
	public String listarCards(@RequestParam(name = "page", defaultValue = "1") int page, ModelMap model) {
		
		Sort sort = new Sort(Sort.Direction.DESC, "dtCadastro");
		PageRequest pageRequest = PageRequest.of(page, 8, sort);
		model.addAttribute("promocoes", promocaoRepository.findAll(pageRequest));
		return "promo-card";
	}
	
	
	
	@PostMapping("/save")
	public ResponseEntity<?> salvar(@Valid Promocao promocao, BindingResult result){
		
		if (result.hasErrors()) {
			
			Map<String, String> errors = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errors.put(error.getField(), error.getDefaultMessage());
			}
			
			return ResponseEntity.unprocessableEntity().body(errors);
		}
		
		log.info("Promocao {}", promocao.toString());
		promocao.setDtCadastro(LocalDateTime.now());
		promocao.setPreco(new BigDecimal(promocao.getPreco().toBigIntegerExact()));
		
		
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
