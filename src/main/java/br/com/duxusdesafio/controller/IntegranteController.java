package br.com.duxusdesafio.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.service.ApiService;

@Controller
public class IntegranteController {
	
	@Autowired
	private IntegranteRepository integranteRepository;
	
	@Autowired
	private ApiService apiService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		model.addAttribute("integrantes", integranteRepository.findAll(Sort.by(Direction.DESC, "id")));
		return "integrantes";
	}

	@GetMapping("/novo-integrante")
	public String novoIntegrante(Model model) {
		model.addAttribute("integrante", new Integrante());
		model.addAttribute("integrantes", apiService.buscarIntegrantesSemTimes());
		return "novo-integrante";
	}
	
   @PostMapping("/add")
   public String adicionarIntegrante(@Valid Integrante integrante, BindingResult result, Model model) {
       
       if (result.hasFieldErrors("nome") 
             || result.hasFieldErrors("franquia") 
             || result.hasFieldErrors("funcao")) {
           
             return "novo-integrante";
       }
       
       integranteRepository.save(integrante);

       return "redirect:integrantes";
   }
}
