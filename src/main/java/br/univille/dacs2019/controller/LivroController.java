package br.univille.dacs2019.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.univille.dacs2019.model.Livro;
import br.univille.dacs2019.service.LibService;

@Controller
@RequestMapping("/livro")
public class LivroController {
	
	@Autowired
	private LibService service;
	
	@GetMapping()
	public ModelAndView index() {
		return new ModelAndView("livro/index","livros",service.getAll());
	}
	
	@GetMapping("/novo")
	public ModelAndView createForm(@ModelAttribute Livro livro) {
		return new ModelAndView("livro/form");
	}
	
	@PostMapping(params="form")
	public ModelAndView save(@Valid Livro livro) {
		
		service.save(livro);
		return new ModelAndView("redirect:/livro");
		
	}
	@GetMapping(value="/alterar/{id}")
	public ModelAndView edit(@PathVariable("id") Livro livro) {
		return new ModelAndView("livro/form","livro", livro);
		
	}
	@GetMapping(value="/remover/{id}")
	public ModelAndView remove(@PathVariable("id") Livro livro) {
		service.remove(livro);
		return new ModelAndView("redirect:/livro");
	}
}
