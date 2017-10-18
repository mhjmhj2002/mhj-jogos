package com.mhj.jogos.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mhj.jogos.dao.ConcursoDao;
import com.mhj.jogos.domain.Concurso;
import com.mhj.jogos.domain.Dezena;
import com.mhj.jogos.model.FrequenciaDezena;
import com.mhj.jogos.model.JogoAcerto;

@Controller
@RequestMapping("/lotofacil")
public class LotoFacilController {

	@Autowired
	private ConcursoDao concursoDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {

		Concurso ultimoConcurso = concursoDao.getUltimoConcurso();

		ModelAndView modelAndView = new ModelAndView("/jogo/lotofacil/home");
		modelAndView.addObject("numero", ultimoConcurso.getNumero());
		modelAndView.addObject("data", ultimoConcurso.getData());

		List<Integer> dezenas = new ArrayList<>();
		for (Dezena dezena : ultimoConcurso.getDezenas()) {
			dezenas.add(dezena.getNumero());
		}
		modelAndView.addObject("dezenas", dezenas);

		return modelAndView;
	}

	@RequestMapping("/maisSorteadas")
	public ModelAndView maisSorteadas() {
		List<JogoAcerto> maisSorteados = concursoDao.maisSorteados();
		BigDecimal somaMaisSorteados = concursoDao.somaMaisSorteados();
		BigDecimal gasto = concursoDao.gastoConcursos();
		List<FrequenciaDezena> dezenasMaisSorteadas = concursoDao.dezenasMaisSorteadas();
		BigDecimal lucro = somaMaisSorteados.subtract(gasto);

		ModelAndView modelAndView = new ModelAndView("/jogo/lotofacil/maisSorteadas");
		modelAndView.addObject("dezenasMaisSorteadas", dezenasMaisSorteadas);
		modelAndView.addObject("maisSorteados", maisSorteados);
		modelAndView.addObject("somaMaisSorteados", somaMaisSorteados);
		modelAndView.addObject("gasto", gasto);
		modelAndView.addObject("lucro", lucro);
		return modelAndView;
	}

	@RequestMapping("/menosSorteadas")
	public ModelAndView menosSorteadas() {
		List<JogoAcerto> maisSorteados = concursoDao.menosSorteados();
		BigDecimal somaMenosSorteados = concursoDao.somaMenosSorteados();
		BigDecimal gasto = concursoDao.gastoConcursos();
		List<FrequenciaDezena> dezenasMenosSorteadas = concursoDao.dezenasMenosSorteadas();
		BigDecimal lucro = somaMenosSorteados.subtract(gasto);

		ModelAndView modelAndView = new ModelAndView("/jogo/lotofacil/menosSorteadas");
		modelAndView.addObject("dezenasMenosSorteadas", dezenasMenosSorteadas);
		modelAndView.addObject("menosSorteados", maisSorteados);
		modelAndView.addObject("somaMenosSorteados", somaMenosSorteados);
		modelAndView.addObject("gasto", gasto);
		modelAndView.addObject("lucro", lucro);
		return modelAndView;
	}

	@RequestMapping("/dezenasMaisSorteadas")
	@ResponseBody
	public List<FrequenciaDezena> dezenasMaisSorteadas() {
		List<FrequenciaDezena> maisSorteados = concursoDao.dezenasMaisSorteadas();
		return maisSorteados;
	}

	@RequestMapping("/dezenasMenosSorteadas")
	@ResponseBody
	public List<FrequenciaDezena> dezenasMenosSorteadas() {
		List<FrequenciaDezena> maisSorteados = concursoDao.dezenasMenosSorteadas();
		return maisSorteados;
	}

	@RequestMapping("/maisSorteados")
	@ResponseBody
	public List<JogoAcerto> maisSorteados() {
		List<JogoAcerto> maisSorteados = concursoDao.maisSorteados();
		return maisSorteados;
	}

	@RequestMapping("/menosSorteados")
	@ResponseBody
	public List<JogoAcerto> menosSorteados() {
		List<JogoAcerto> menosSorteados = concursoDao.menosSorteados();
		return menosSorteados;
	}

	@RequestMapping("/somaMaisSorteados")
	@ResponseBody
	public BigDecimal somaMaisSorteados() {
		BigDecimal somaMaisSorteados = concursoDao.somaMaisSorteados();
		return somaMaisSorteados;
	}

	@RequestMapping("/somaMenosSorteados")
	@ResponseBody
	public BigDecimal somaMenosSorteados() {
		BigDecimal somaMenosSorteados = concursoDao.somaMenosSorteados();
		return somaMenosSorteados;
	}

	@RequestMapping("/gastoConcursos")
	@ResponseBody
	public BigDecimal gastoConcursos() {
		BigDecimal gasto = concursoDao.gastoConcursos();
		return gasto;
	}

	@RequestMapping("/ultimoConsurso")
	public ModelAndView ultimoConcurso() {

		ModelAndView modelAndView = new ModelAndView("/jogo/lotofacil/ultimoConcurso");

		return modelAndView;
	}

	@RequestMapping("/getUltimoConsurso")
	@ResponseBody
	public Concurso getUltimoConcurso() {

		Concurso concurso = concursoDao.getUltimoConcurso();

		return concurso;

	}

}
