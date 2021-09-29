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

import com.blog.blog.model.PostagemModel;
import com.blog.blog.model.TemaModel;
import com.blog.blog.repository.PostagemRepository;


public class PostagemController {
	private @Autowired PostagemRepository repository;
	
	 @GetMapping ("/todasPostagens")
	 public ResponseEntity<List<PostagemModel>> getAll(){
		 List<PostagemModel> objetoLista = repository.findAll();
		if(objetoLista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoLista);
		}
	 }
	 
	@GetMapping("/{idPostagem}")
	public ResponseEntity<PostagemModel> FindById (@PathVariable(value = "idPostagem") Long idPostagem){
		Optional<PostagemModel> objetoPostagem = repository.findById(idPostagem);
			if(objetoPostagem.isPresent()){
			return ResponseEntity.status(200).body(objetoPostagem.get());
		}else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/tituloPostagem")
	public ResponseEntity<List<PostagemModel>> findAllByTitulo (@PathVariable(value = "tituloPostagem") String tituloPostagem){
		List<PostagemModel> objetoPostagem = repository.findAllByTituloContainingIgnoreCase(tituloPostagem);
		if(objetoPostagem.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoPostagem);
		}
	}
	 
	 
	 @PostMapping("/criarPostagem")
	public ResponseEntity<PostagemModel> criarTema (@Valid @RequestBody PostagemModel novaPostagem){
		return ResponseEntity.status(201).body(repository.save(novaPostagem));		
	}
		
	@PutMapping("/atualizarPostagem")
	public ResponseEntity<PostagemModel> atualizarPostagem (@Valid @RequestBody PostagemModel atualizacaoPostagem){
		return ResponseEntity.status(201).body(repository.save(atualizacaoPostagem));
	}
	
	@DeleteMapping("/deletar/{idPostagem}")
	public void deletarTema(@PathVariable(value ="idPostagem") Long idPostagem){
		repository.deleteById(idPostagem);
	}
}

	
