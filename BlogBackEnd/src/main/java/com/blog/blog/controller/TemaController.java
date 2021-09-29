package com.blog.blog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.model.TemaModel;
import com.blog.blog.model.UsuarioModel;
import com.blog.blog.repository.TemaRepository;

@RestController
@RequestMapping("/tema")
public class TemaController {
	private @Autowired TemaRepository repository;
	
	@GetMapping("/todosTemas")
	public ResponseEntity<List<TemaModel>> getAll(){
		List<TemaModel> objetoLista = repository.findAll();
		if(objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	}
	
	@GetMapping("/{idTema}")
	public ResponseEntity<TemaModel> findById (@PathVariable(value = "idTema") Long idTema){
		Optional<TemaModel> objetoTema = repository.findById(idTema);
		if(objetoTema.isPresent()){
			return ResponseEntity.status(200).body(objetoTema.get());
		}else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@PostMapping("/criarTema")
	public ResponseEntity<TemaModel> criarTema (@Valid @RequestBody TemaModel novoTema){
		return ResponseEntity.status(201).body(repository.save(novoTema));		
	}
	
	@PutMapping("/atualizarTema")
	public ResponseEntity<TemaModel> atualizarTema (@Valid @RequestBody TemaModel atualizacaoTema){
		return ResponseEntity.status(201).body(repository.save(atualizacaoTema));
	}
	
	@DeleteMapping("/deletar/{idTema}")
	public void deletarTema(@PathVariable(value ="idTema") Long idTema){
		repository.deleteById(idTema);
	}
}
