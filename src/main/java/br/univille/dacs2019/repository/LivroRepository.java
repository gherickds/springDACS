package br.univille.dacs2019.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.univille.dacs2019.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
	Livro findByNome(String nome);
}
