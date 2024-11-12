package br.com.duxusdesafio.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.ComposicaoTimeRepository;
import br.com.duxusdesafio.repository.IntegranteRepository;
import br.com.duxusdesafio.utils.ComposicaoTimeUtil;

@SpringBootTest
public class ApiServiceTest {

	@InjectMocks
	private ApiService apiService;
	
	@Mock
	private ComposicaoTimeRepository composicaoTimeRepository;
	
	@Mock
	private IntegranteRepository integranteRepository;
	
	@Test
	public void deveContarFuncoesDentroDoPeriodo() {
		when(composicaoTimeRepository.findAll()).thenReturn(ComposicaoTimeUtil.listaComposicaoTime());
		Map<String, Long> contagemPorFuncao = apiService.contagemPorFuncao(LocalDate.of(2021, 12, 01), LocalDate.of(2021, 12, 20));
		
		assertTrue(contagemPorFuncao.containsKey("Funcao 1"));
		assertTrue(contagemPorFuncao.get("Funcao 1").intValue() == 2);
		
		assertTrue(contagemPorFuncao.containsKey("Funcao 2"));
		assertTrue(contagemPorFuncao.get("Funcao 2").intValue() == 1);
		
	}
	
	@Test
    public void deveTrazerIntegrantesDoTimeMaisComum(){
		when(composicaoTimeRepository.findAll()).thenReturn(ComposicaoTimeUtil.listaComposicaoTime());
		List<String> contagemPorFranquia = apiService.integrantesDoTimeMaisComum(LocalDate.of(2021, 12, 01), LocalDate.of(2025, 12, 20));
		assertTrue(contagemPorFranquia.size() ==  2);
    }
	
	@Test
	public void deveContarFranquiasDentroDoPeriodo() {
		when(composicaoTimeRepository.findAll()).thenReturn(ComposicaoTimeUtil.listaComposicaoTime());
		Map<String, Long> contagemPorFranquia = apiService.contagemPorFranquia(LocalDate.of(2021, 12, 01), LocalDate.of(2021, 12, 20));
		
		assertTrue(contagemPorFranquia.containsKey("Franquia 1"));
		assertTrue(contagemPorFranquia.get("Franquia 1").intValue() == 2);
		
		assertTrue(contagemPorFranquia.containsKey("Franquia 2"));
		assertTrue(contagemPorFranquia.get("Franquia 2").intValue() == 1);
	}
	
	@Test
	public void deveBuscarFranquiaMaisComum() {
		when(integranteRepository.findAll()).thenReturn(ComposicaoTimeUtil.listaIntegrantes());
		String franquiaMaisFamosa = apiService.franquiaMaisFamosa(LocalDate.of(2021, 12, 01), LocalDate.of(2021, 12, 20));
		assertEquals("Franquia 1", franquiaMaisFamosa);

	}
	
	@Test
	public void deveBuscarFuncaoMaisComum() {
		when(integranteRepository.findAll()).thenReturn(ComposicaoTimeUtil.listaIntegrantes());
		String funcaoMaisComum = apiService.funcaoMaisComum(LocalDate.of(2021, 12, 01), LocalDate.of(2021, 12, 20));
		assertEquals("Funcao 2", funcaoMaisComum);
	}
	
	@Test
	public void deveBuscarIntegranteEmMaisTimes() {
		when(composicaoTimeRepository.findAll()).thenReturn(ComposicaoTimeUtil.listaComposicaoTime());
		Integrante integranteMaisUsado = apiService.integranteMaisUsado(LocalDate.of(2021, 12, 01), LocalDate.of(2021, 12, 20));
		assertEquals(2, integranteMaisUsado.getId().longValue());
	}
	
	@Test
	public void devebuscarIntegrantesSemTimes() {
		when(composicaoTimeRepository.findAll()).thenReturn(ComposicaoTimeUtil.listaComposicaoTime());
		List<Integrante> integrantes = apiService.buscarIntegrantesSemTimes();
		integrantes.forEach(d -> System.out.println(d.getNome()));
	}
	
	
//    * Vai retornar um Time, com a composição do time daquela data
//    */
//   public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
//       return todosOsTimes.stream().filter(time -> time.getData().equals(data)).findFirst().get();
//   }

}
