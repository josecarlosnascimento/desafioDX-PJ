package br.com.duxusdesafio.utils;

import java.time.LocalDate;
import java.util.List;

import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;

public class ComposicaoTimeUtil {
	
	public static List<ComposicaoTime> listaComposicaoTime() {
		ComposicaoTime composicao1 = ComposicaoTime.builder().time(time1()).integrante(integrante1()).build();
		ComposicaoTime composicao2 = ComposicaoTime.builder().time(time2()).integrante(integrante2()).build();
		ComposicaoTime composicao3 = ComposicaoTime.builder().time(time3()).integrante(integrante3()).build();
		ComposicaoTime composicao4 = ComposicaoTime.builder().time(time4()).integrante(integrante4()).build();
		ComposicaoTime composicao5 = ComposicaoTime.builder().time(time5()).integrante(integrante5()).build();
		ComposicaoTime composicao6 = ComposicaoTime.builder().time(time6()).integrante(integrante6()).build();
		ComposicaoTime composicao11 = ComposicaoTime.builder().time(time7()).integrante(integrante11()).build();
		ComposicaoTime composicao12 = ComposicaoTime.builder().time(time8()).integrante(integrante12()).build();
		ComposicaoTime composicao13 = ComposicaoTime.builder().time(time9()).integrante(integrante11()).build();
		ComposicaoTime composicao14 = ComposicaoTime.builder().time(time9()).integrante(integrante2()).build();

		return List.of(composicao1,	composicao2,
					composicao3, composicao4,
					composicao5, composicao6,
					composicao11, composicao12, composicao13, composicao14);
	}

	public static List<Integrante> listaIntegrantes() {
		return List.of(integrante1(), integrante2(),
				integrante3(), integrante4(),
				integrante5(), integrante6());
	}
	
	public static Time time1() {
		return Time.builder()
				.id(1L)
				.data(LocalDate.of(2020, 1, 01))
				.nome("Sao Paulo")
				.build();
	}
	public static Time time2() {
		return Time.builder()
				.id(2L)
				.data(LocalDate.of(2020, 2, 11))
				.nome("Corinthians")
				.build();
	}
	public static Time time3() {
		return Time.builder()
				.id(3L)
				.data(LocalDate.of(2021, 11, 21))
				.nome("Palmeiras")
				.build();
	}
	public static Time time4() {
		return Time.builder()
				.id(4L)
				.data(LocalDate.of(2021, 12, 01))
				.nome("Santos")
				.build();
	}
	public static Time time5() {
		return Time.builder()
				.id(5L)
				.data(LocalDate.of(2021, 12, 11))
				.nome("Athletico Mineiro")
				.build();
	}
	public static Time time6() {
		return Time.builder()
				.id(6L)
				.data(LocalDate.of(2022, 2, 2))
				.nome("Athletico GO")
				.build();
	}
	public static Time time7() {
		return Time.builder()
				.id(7L)
				.data(LocalDate.of(2022, 2, 2))
				.nome("Lazio")
				.build();
	}
	public static Time time8() {
		return Time.builder()
				.id(8L)
				.data(LocalDate.of(2022, 3, 2))
				.nome("Manchester")
				.build();
	}
	public static Time time9() {
		return Time.builder()
				.id(9L)
				.data(LocalDate.of(2024, 11, 10))
				.nome("Milan")
				.build();
	}
	public static Integrante integrante1() {
		return Integrante.builder()
				.id(1L)
				.nome("Joao")
				.franquia("Franquia 1")
				.funcao("Funcao 2")
				.build();
	}

	public static Integrante integrante2() {
		return Integrante.builder()
				.id(2L)
				.nome("Isa")
				.franquia("Franquia 1")
				.funcao("Funcao 1")
				.build();
	}
	public static Integrante integrante3() {
		return Integrante.builder()
				.id(3L)
				.nome("Jessica")
				.franquia("Franquia 2")
				.funcao("Funcao 1")
				.build();
	}
	public static Integrante integrante4() {
		return Integrante.builder()
				.id(4L)
				.nome("Caio")
				.franquia("Franquia 1")
				.funcao("Funcao 3")
				.build();
	}
	public static Integrante integrante5() {
		return Integrante.builder()
				.id(5L)
				.nome("Fernando")
				.franquia("Franquia 3")
				.funcao("Funcao 2")
				.build();
	}
	public static Integrante integrante6() {
		return Integrante.builder()
				.id(6L)
				.nome("Edu")
				.franquia("Franquia 2")
				.funcao("Funcao 2")
				.build();
	}
	public static Integrante integrante13() {
		return Integrante.builder()
				.id(1L)
				.nome("Joao")
				.franquia("Franquia 1")
				.funcao("Funcao 2")
				.build();
	}
	public static Integrante integrante12() {
		return Integrante.builder()
				.id(1L)
				.nome("Joao")
				.franquia("Franquia 1")
				.funcao("Funcao 2")
				.build();
	}
	public static Integrante integrante11() {
		return Integrante.builder()
				.id(1L)
				.nome("Joao")
				.franquia("Franquia 1")
				.funcao("Funcao 2")
				.build();
	}
}
