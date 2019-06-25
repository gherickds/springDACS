package br.univille.dacs2019.service;

import org.springframework.stereotype.Service;

import java.util.List;

import br.univille.dacs2019.model.Livro;

@Service
public interface LibService {

	List<Livro> getAll();

	void save(Livro livro);
	
	void remove(Livro livro);
	
	Livro findBy(long id);
}
