package br.univille.dacs2019.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.univille.dacs2019.model.Livro;
import br.univille.dacs2019.service.LibService;

@RestController
@RequestMapping("/api/livros")
public class LivroControllerAPI {
	@Autowired
	private LibService service;
	
	@GetMapping
	public ResponseEntity<List<Livro>> listarLivros(){
		List<Livro> lista = new ArrayList<Livro>();
		try {
			lista = service.getAll();
		}catch(Exception e) {
			System.err.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return new ResponseEntity<List<Livro>>(lista,HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<?> save(@RequestBody Livro livro){
		service.save(livro);
		return ResponseEntity.ok().build();
	}
	@PutMapping(path="/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @RequestBody Livro livro){
		Livro livroatual = service.findBy(id);
		if(livroatual != null) {
			livroatual.setNome(livro.getNome());
			livroatual.setGenero(livro.getGenero());
			livroatual.setAutor(livro.getAutor());
			livroatual.setDataPublicacao(livro.getDataPublicacao());
			service.save(livroatual);
			ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	@DeleteMapping(path="/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id){
		Livro livroatual = service.findBy(id);
		if(livroatual != null) {
			service.remove(livroatual);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
