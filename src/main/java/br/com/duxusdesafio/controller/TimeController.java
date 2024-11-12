package br.com.duxusdesafio.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.repository.TimeRepository;
import br.com.duxusdesafio.service.ApiService;

@Controller
@SessionAttributes("integranteForm")
public class TimeController {
	
	@Autowired
	private ApiService apiService;
	
	@Autowired
	private ComposicaoTimeRepository composicaoTimeRepository;
	
	@Autowired
	private TimeRepository timeRepository;
	
	@Autowired
	private IntegranteRepository integranteRepository;
	
	private List<Integrante> integrantes;
	
	@GetMapping("/times")
	public String mostrarTime(Model model) {
		Map<Time, List<ComposicaoTime>> times = composicaoTimeRepository.findAll().stream().collect(Collectors.groupingBy(c -> c.getTime()));
		model.addAttribute("times", times);
		return "times";
	}

	@GetMapping("/montar-time")
	public String montarTime(Model model) {
		integrantes =
				apiService.buscarIntegrantesSemTimes()
				.stream()
				.toList();
		
		model.addAttribute("integrantes", integrantes);
		model.addAttribute("integrante", new Integrante());
		model.addAttribute("time", new Time());

		return "montar-time";
	}
	
   @PostMapping("/integrar")
   public String montarTimes(@Valid Time time, BindingResult result, @RequestParam(defaultValue = "") List<Long> integrantes) {
	   
       if (result.hasFieldErrors("nome") || integrantes.isEmpty()) {
           return "montar-time";
         }
	   
	   Time timeSaved = timeRepository.save(time);
	   
	   integrantes.forEach(i -> {
		   Integrante integrante = integranteRepository.findById(i).get();
		   
		   ComposicaoTime composicaoTime = ComposicaoTime
				   								.builder()
				   								.integrante(integrante)
				   								.time(timeSaved)
				   								.build();
		   composicaoTimeRepository.save(composicaoTime);
	   });
	   
	   return "redirect:times";
   }

}
