package br.com.duxusdesafio.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados solicitados no desafio!
 */
@Service
public class ApiService {
	
	@Autowired
	private ComposicaoTimeRepository composicaoTimeRepository;
	
	@Autowired
	private IntegranteRepository integranteRepository;

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        return todosOsTimes.stream().filter(time -> time.getData().equals(data)).findFirst().get();
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal){
    	List<ComposicaoTime> all = composicaoTimeRepository.findAll();
    	Map<Integrante, Long> collect = all.stream().collect(Collectors.groupingBy(t -> t.getIntegrante(), Collectors.counting()));
    	
    	Integrante first = collect.entrySet()
    			.stream()
    			.sorted(Map.Entry.<Integrante, Long>comparingByValue().reversed()) 
    			.findFirst()
    			.get()
    			.getKey();
        return first;
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    public List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal){
    	List<ComposicaoTime> all = composicaoTimeRepository.findAll();
    	List<String> integrantes = new ArrayList<>();
    	
    	Entry<Time, Long> entry = 
    				all
    				.stream()
    				.collect(Collectors.groupingBy(t -> t.getTime(), Collectors.counting()))
    				.entrySet()
    				.stream()
    				.sorted(Map.Entry.<Time, Long>comparingByValue().reversed())
    				.unordered()
    				.findFirst().get();
    	
    		all.stream()
    				.filter(a -> a.getTime().getData().isAfter(dataInicial) && a.getTime().getData().isBefore(dataFinal))
    				.filter(a -> a.getTime().getId().equals(entry.getKey().getId()))
    				.forEach(i -> integrantes.add(i.getIntegrante().getNome()));
    	
    	return integrantes;
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal){
    	
    	List<Integrante> all = integranteRepository.findAll();
    	Map<String, Long> collect = all.stream().collect(Collectors.groupingBy(i -> i.getFuncao(), Collectors.counting()));
    	String first = collect.entrySet()
    			.stream()
    			.sorted(Map.Entry.<String, Long>comparingByValue().reversed()) 
    			.findFirst()
    			.get()
    			.getKey();
    	
        return first;
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal) {
    	List<Integrante> all = integranteRepository.findAll();
    	Map<String, Long> collect = all.stream().collect(Collectors.groupingBy(i -> i.getFranquia(), Collectors.counting()));
    	String first = collect.entrySet()
    			.stream()
    			.sorted(Map.Entry.<String, Long>comparingByValue().reversed()) 
    			.findFirst()
    			.get()
    			.getKey();
    	
        return first;
    }


    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal){
    	List<ComposicaoTime> all = composicaoTimeRepository.findAll();
    	Map<String, Long> collect = all.stream()
    			.filter(f -> f.getTime().getData().isAfter(dataInicial) && f.getTime().getData().isBefore(dataFinal))
    			.collect(Collectors.groupingBy(i -> i.getIntegrante().getFranquia(), Collectors.counting()));
    	return collect;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal){
       	List<ComposicaoTime> all = composicaoTimeRepository.findAll();
    	Map<String, Long> collect = all.stream()
    			.filter(f ->  f.getTime().getData().isAfter(dataInicial) && f.getTime().getData().isBefore(dataFinal))
    			.collect(Collectors.groupingBy(i -> i.getIntegrante().getFuncao(), Collectors.counting()));
    	return collect;
    }
    
    
    public List<Integrante> buscarIntegrantesSemTimes(){
    	
    	List<Integrante> integrantes = new ArrayList<>();
    	
       	List<ComposicaoTime> all = composicaoTimeRepository.findAll();
       	Map<Integrante, Time> collect = all.stream().collect(Collectors.toMap(
       			c -> c.getIntegrante(),
       			c -> c.getTime(),
       			(a, b) -> a.getData().isAfter(b.getData()) ? a : b));
       	
       	collect.entrySet().forEach(g -> {
       		if(g.getValue().getData().plusDays(7).isBefore(LocalDate.now())) {
       			integrantes.add(g.getKey());
       		}
       	});
       	
       	return integrantes;
    }

}
