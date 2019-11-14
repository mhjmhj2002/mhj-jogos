package com.mhj.jogos.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mhj.jogos.dao.ConcursoDao;
import com.mhj.jogos.domain.Concurso;
import com.mhj.jogos.domain.Dezena;
import com.mhj.jogos.model.FrequenciaDezena;
import com.mhj.jogos.model.Jogo;
import com.mhj.jogos.model.JogoAcerto;
import com.mhj.jogos.util.Constantes;

@Controller
@RequestMapping("/lotofacil")
public class LotoFacilController {

	@Autowired
	private ConcursoDao concursoDao;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
//		binder.addValidators(new EquacaoGrau2Validation());
	}

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
		
		dezenas.sort(Comparator.naturalOrder());
		
		List<FrequenciaDezena> dezenasMaisSorteadas = concursoDao.dezenasMaisSorteadas();
		List<FrequenciaDezena> dezenasMenosSorteadas = concursoDao.dezenasMenosSorteadas();
		
		
		modelAndView.addObject("dezenas", dezenas);
		modelAndView.addObject("dezenaMaisSorteada", dezenasMaisSorteadas.get(0).getNumero());
		modelAndView.addObject("vezesMaisSorteada", dezenasMaisSorteadas.get(0).getQuantidade());
		modelAndView.addObject("dezenaMenosSorteada", dezenasMenosSorteadas.get(0).getNumero());
		modelAndView.addObject("vezesMenosSorteada", dezenasMenosSorteadas.get(0).getQuantidade());

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
		modelAndView.addObject("valor", Constantes.VALOR_LOTO_FACIL);
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
		modelAndView.addObject("valor", Constantes.VALOR_LOTO_FACIL);
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

	@RequestMapping("/jogo")
	public ModelAndView jogo(Jogo jogo) {

		ModelAndView modelAndView = new ModelAndView("/jogo/lotofacil/jogo");

		return modelAndView;
	}

//	@RequestMapping("jogo")
//	public ModelAndView form(Jogo jogo) {
//		ModelAndView modelAndView = new ModelAndView("math/em/1ano/equacao2grau");
//		modelAndView.addObject("sinais", Sinal.values());
//
//		return modelAndView;
//	}

	@PostMapping
	public ModelAndView jogar(Jogo jogo, Locale locale, BindingResult result, RedirectAttributes redirectAttributes) {

		List<Integer> dezenas = jogo.getDezenasList();
		
		List<JogoAcerto> sorteados = concursoDao.sorteados(dezenas);
		BigDecimal somaSorteados = concursoDao.somaMaisSorteados();
		BigDecimal gasto = concursoDao.gastoConcursos();
		List<FrequenciaDezena> dezenasSelecionadas = concursoDao.getFrequenciaDezenas(dezenas);
		BigDecimal lucro = somaSorteados.subtract(gasto);

		ModelAndView modelAndView = new ModelAndView("/jogo/lotofacil/jogoRetorno");
		modelAndView.addObject("dezenasSelecionadas", dezenasSelecionadas);
		modelAndView.addObject("sorteados", sorteados);
		modelAndView.addObject("somaSorteados", somaSorteados);
		modelAndView.addObject("gasto", gasto);
		modelAndView.addObject("lucro", lucro);
		modelAndView.addObject("valor", Constantes.VALOR_LOTO_FACIL);
		return modelAndView;
		
//		modelAndView.addObject("linha", Impressao.getHTML(operacao.getRetorno()));

	}

}
