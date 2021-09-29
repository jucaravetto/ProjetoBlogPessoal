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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.blog.model.UsuarioModel;
import com.blog.blog.model.utilities.UsuarioLogin;
import com.blog.blog.repository.UsuarioRepository;
import com.blog.blog.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	private @Autowired UsuarioRepository repository;
	private @Autowired UsuarioService servicos;
	//endpoints ou crud
	
	@GetMapping("/todos")
	 public ResponseEntity<List<UsuarioModel>> pegarTodos(){
		
		List<UsuarioModel> objetolista = repository.findAll();
		if(objetolista.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetolista);
		}
	}
	
	@PostMapping("/salvar")
    public ResponseEntity<Object> cadastrarUsuario(@Valid @RequestBody UsuarioModel novoUsuario){
        Optional<Object> objetoSalvar = servicos.cadastrarUsuario(novoUsuario);

        if (objetoSalvar.isEmpty()) {
            return ResponseEntity.status(400).build();
        }else {
            return ResponseEntity.status(201).body(objetoSalvar.get());
        }
    }
	@PutMapping("/credenciais")
    public ResponseEntity<Object> credenciais(@Valid @RequestBody UsuarioLogin usuarioAutenticar) {
        Optional<?> objetoCredenciais = servicos.autenticador(usuarioAutenticar);

        if (objetoCredenciais.isEmpty()) {
            return ResponseEntity.status(400).build();
        } else {
            return ResponseEntity.status(201).body(objetoCredenciais.get());
        }
    }
	
	
	@PutMapping("/atualizar")
	public ResponseEntity<UsuarioModel> atualizarUsuario (UsuarioModel usuarioParaAtualizar){
		return ResponseEntity.status(201).body(repository.save(usuarioParaAtualizar));
	}
	
	@DeleteMapping("/deletar/{id_usuario}")
	public void deletarUsuario(@PathVariable(value ="id_usuario") Long idUsuario){
		repository.deleteById(idUsuario);
	}
	
	@GetMapping("/{id_usuario}")
	public ResponseEntity<UsuarioModel> buscarPorId(@PathVariable (value = "id_usuario") Long idDoUsuario){
		Optional<UsuarioModel> objetoUsuario = repository.findById(idDoUsuario);
		if (objetoUsuario.isPresent()) {
			return ResponseEntity.status(200).body(objetoUsuario.get());
		}else {
			return ResponseEntity.status(204).build();
		}
	}
	
	@GetMapping("/nome/{nome_usuario}")
	public ResponseEntity<List<UsuarioModel>> buscarPorNome (@PathVariable(value = "nome_usuario")String nome){
		List<UsuarioModel> objetoNomes = repository.findAllByNomeContainingIgnoreCase(nome);
		if (objetoNomes.isEmpty()) {
			return ResponseEntity.status(204).build();
		}else {
			return ResponseEntity.status(200).body(objetoNomes);
		}
	}
	
	@GetMapping("/email")
	public ResponseEntity<UsuarioModel> buscarPorEmail (@RequestParam(defaultValue = "") String email){
		return repository.findByEmail(email)
				.map(emailEncontrado -> ResponseEntity.ok(emailEncontrado))
				.orElse(ResponseEntity.notFound().build());
		
	}
}






