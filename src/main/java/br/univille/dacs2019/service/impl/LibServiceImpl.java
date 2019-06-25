package br.univille.dacs2019.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.univille.dacs2019.model.Livro;
import br.univille.dacs2019.repository.LivroRepository;
import br.univille.dacs2019.service.LibService;

@Service
public class LibServiceImpl implements LibService{

	@Autowired
	private LivroRepository LivroRepository;
	
	@Override
	public List<Livro> getAll() {
		return LivroRepository.findAll();
	}

	@Override
	public void save(Livro livro) {
		LivroRepository.save(livro);
		
	}
	
	@Override
	public void remove(Livro livro) {
		LivroRepository.delete(livro);
	}

	@Override
	public Livro findBy(long id) {
		Optional<Livro> retorno = LivroRepository.findById(id);
		if(retorno.isPresent())
			return retorno.get();
		return null;
	}
}
