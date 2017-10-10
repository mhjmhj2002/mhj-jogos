package com.mhj.jogos.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mhj.jogos.dao.ConcursoDao;
import com.mhj.jogos.dao.JogoDao;
import com.mhj.jogos.domain.Concurso;
import com.mhj.jogos.domain.Dezena;
import com.mhj.jogos.domain.Jogo;
import com.mhj.jogos.domain.Premio;
import com.mhj.jogos.enums.TipoJogo;
import com.mhj.jogos.infra.FileSaver;
import com.mhj.jogos.model.FrequenciaDezena;
import com.mhj.jogos.model.JogoAcerto;
import com.mhj.jogos.util.MhjUtilFile;

@Controller
@RequestMapping("/lotofacil")
public class LotoFacilController {

	@Autowired
	private JogoDao jogoDao;

	@Autowired
	private ConcursoDao concursoDao;

	@Autowired
	private FileSaver fileSaver;
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home(){
		
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
	public ModelAndView ultimoConcurso(){

		
		ModelAndView modelAndView = new ModelAndView("/jogo/lotofacil/ultimoConcurso");
		
		return modelAndView;
	}
	
	@RequestMapping("/getUltimoConsurso")
	@ResponseBody
	public Concurso getUltimoConcurso(){
		
		Concurso concurso = concursoDao.getUltimoConcurso();
		
		return concurso;
		
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@RequestParam("sorteio") MultipartFile sorteio/*, BindingResult result, RedirectAttributes redirectAttributes*/) throws IOException, ParseException {
		
		File file = fileSaver.write("sorteio", sorteio);		

		Jogo jogo = jogoDao.findByTipo(TipoJogo.LOTOFACIL);
		
		if (jogo != null) {
			jogoDao.delete(jogo);
		}
		
		jogo = this.processFile(file);
		
		jogoDao.insert(jogo);
		
		ModelAndView modelAndView = new ModelAndView("/jogo/lotofacil/atualizaDadosOk");
		
		return modelAndView;
	}

	private Jogo processFile(File file) throws IOException, ParseException {
		Reader reader = null;
		BufferedReader bufferedReader = null;
		Jogo jogo = new Jogo();
		jogo.setNome(TipoJogo.LOTOFACIL.name());
		jogo.setTipoJogo(TipoJogo.LOTOFACIL);
		jogo.setValor(new BigDecimal(1.50));

		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			reader = new InputStreamReader(new FileInputStream(file));
			bufferedReader = new BufferedReader(reader);
			String linha = "";
			int numeroRegistro = 1;
			try {
				Concurso concurso = new Concurso();
				Premio premio;
				while ((linha = bufferedReader.readLine()) != null) {
					if (linha.startsWith("<td ") && !linha.contains("&nbsp")) {
						// System.out.println("linha: "+linha+"registro:
						// "+numeroRegistro);
						linha = limpaRegistro(linha);
						// numeroRegistro = new Long(linha);
						switch (numeroRegistro) {
						case 1:
							concurso = new Concurso();
							concurso.setNumero(new Long(linha));
							// //System.out.println(linha);
							break;
						case 2:
							concurso.setData(format.parse(linha));
							// //System.out.println(linha);
							break;
						case 3:
						case 4:
						case 5:
						case 6:
						case 7:
						case 8:
						case 9:
						case 10:
						case 11:
						case 12:
						case 13:
						case 14:
						case 15:
						case 16:
						case 17:
							Dezena dezena = new Dezena();
							dezena.setConcurso(concurso);
							dezena.setNumero(new Integer(linha));
							concurso.getDezenas().add(dezena);
							// System.out.println(linha);
							break;
						case 18:
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.setArrecadacao(new BigDecimal(linha));
							break;
						case 19:
							// System.out.println(linha);
							premio = new Premio();
							premio.setConcurso(concurso);
							premio.setQuantidadeAcertos(15);
							concurso.getPremios().add(premio);
							break;
						case 20:
							// System.out.println(linha);
							premio = new Premio();
							premio.setConcurso(concurso);
							premio.setQuantidadeAcertos(14);
							concurso.getPremios().add(premio);
							break;
						case 21:
							// System.out.println(linha);
							premio = new Premio();
							premio.setConcurso(concurso);
							premio.setQuantidadeAcertos(13);
							concurso.getPremios().add(premio);
							break;
						case 22:
							// System.out.println(linha);
							premio = new Premio();
							premio.setConcurso(concurso);
							premio.setQuantidadeAcertos(12);
							concurso.getPremios().add(premio);
							break;
						case 23:
							// System.out.println(linha);
							premio = new Premio();
							premio.setConcurso(concurso);
							premio.setQuantidadeAcertos(11);
							concurso.getPremios().add(premio);
							break;
						case 24:
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.getPremios().get(0).setValor(new BigDecimal(linha));
							break;
						case 25:
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.getPremios().get(1).setValor(new BigDecimal(linha));
							break;
						case 26:
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.getPremios().get(2).setValor(new BigDecimal(linha));
							break;
						case 27:
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.getPremios().get(3).setValor(new BigDecimal(linha));
							break;
						case 28:
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.getPremios().get(4).setValor(new BigDecimal(linha));
							break;
						case 29:
							// System.out.println(linha);
							break;
						case 30:
							// System.out.println(linha);
							break;
						case 31:
							// System.out.println(linha);
							numeroRegistro = 0;
							concurso.setJogo(jogo);
							jogo.getConcursos().add(concurso);
							break;
						default:
							numeroRegistro = 0;
							break;
						}
						numeroRegistro++;
					}

				}
			} catch (IOException e) {
				throw e;
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (UnsupportedEncodingException e) {
			throw e;
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}

		return jogo;

	}

	private String limpaRegistro(String linha) {

		for (int i = 1; i < 101; i++) {
			if (linha.contains("<td rowspan=\"" + i + "\">")) {
				linha = linha.replaceAll("<td rowspan=\"" + i + "\">", "");
				break;
			}
		}
		return linha.replaceAll("</td>", "");

	}

	public final List<File> getSortedFiles(String folderName, String fileBaseName) {
		return MhjUtilFile.obterArquivos(folderName, fileBaseName);
	}

	// @RequestMapping("/detalhe/{id}")
	// public ModelAndView detalhe(@PathVariable("id") int id) {
	// ModelAndView modelAndView = new ModelAndView("/produtos/detalhe");
	// Produto produto = produtoDao.find(id);
	// modelAndView.addObject("produto", produto);
	// return modelAndView;
	// }
	//
	// @RequestMapping("/{id}")
	// @ResponseBody
	// public Produto detalheJSON(@PathVariable("id") Integer id) {
	// return produtoDao.find(id);
	// }

}
