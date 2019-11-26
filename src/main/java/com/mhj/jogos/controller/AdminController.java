package com.mhj.jogos.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mhj.jogos.dao.ConcursoDao;
import com.mhj.jogos.dao.JogoDao;
import com.mhj.jogos.dao.PremioDao;
import com.mhj.jogos.domain.Concurso;
import com.mhj.jogos.domain.Dezena;
import com.mhj.jogos.domain.Jogo;
import com.mhj.jogos.domain.Premio;
import com.mhj.jogos.domain.Usuario;
import com.mhj.jogos.enums.TipoJogo;
import com.mhj.jogos.util.Constantes;
import com.mhj.jogos.util.MhjUtilFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private JogoDao jogoDao;

	@Autowired
	private ConcursoDao concursoDao;

	@Autowired
	private PremioDao premioDao;

//	@Autowired
//	private GanhadorDao ganhadorDao;

//	@Autowired
//	private DezenaDao dezenaDao;

//	@Autowired
//	private UsuarioDAO usuarioDAO;
	
	Logger logger = Logger.getLogger(this.getClass().getName());

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView home() {

		ModelAndView modelAndView = new ModelAndView("/admin/home");

		return modelAndView;
	}

	@RequestMapping("/dadosLotofacil")
	public ModelAndView dadosLotofacil(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("jogo/lotofacil/atualizaDados");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView gravar(@RequestParam("sorteio") MultipartFile sorteio, @Valid Usuario usuario,
			BindingResult result, RedirectAttributes redirectAttributes) throws IOException, ParseException {
		
		logger.log(Level.INFO, "inicio gravar");
		
//		if (result.hasErrors()) {
//			return dadosLotofacil(usuario);
//		}

		ModelAndView modelAndView = new ModelAndView("redirect:/admin/dadosOk");

//		Usuario user = usuarioDAO.loadUserByUsername("mhjmhj2002@gmail.com");

//		if (user == null) {
//			return modelAndView;
//		}

//		if (!checkPassword(usuario.getPassword(), user.getPassword())) {
//			return modelAndView;
//		}

		InputStream inputStream = sorteio.getInputStream();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				Jogo jogoNovo = null;
				
				logger.log(Level.INFO, "inicio thread");

				Jogo jogoEncontrado = jogoDao.findByTipo(TipoJogo.LOTOFACIL);

//				if (jogoEncontrado != null) {
					
//					List<Concurso> concursos = concursoDao.findByJogo(jogo);
//					
//					for (Concurso concurso : concursos) {
//						
//						List<Premio> premios = premioDao.findByConcurso(concurso);
//						
//						for (Premio premio : premios) {
//							
//							List<Ganhador> ganhadores = ganhadorDao.findByPremio(premio);
//							
//							for (Ganhador ganhador : ganhadores) {
//								ganhadorDao.delete(ganhador);
//							}
//							
//							premioDao.delete(premio);
//							
//						}
//						
//						List<Dezena> dezenas = dezenaDao.findByConcurso(concurso);
//						
//						for (Dezena dezena : dezenas) {
//							dezenaDao.delete(dezena);
//						}
//						
//						concursoDao.delete(concurso);
//						
//					}
					
//					jogoDao.delete(jogoEncontrado);
					
//				}

				try {
					logger.log(Level.INFO, "processa arquivo");
					jogoNovo = processFile(inputStream);
				} catch (IOException | ParseException e) {
					e.printStackTrace();
				}
				
				if (jogoEncontrado == null) {
					logger.log(Level.INFO, "inserir dados novos");
					jogoDao.insert(jogoNovo);
				} else {
					logger.log(Level.INFO, "atualizar dados");
					Long numConcurso = concursoDao.findLastConcurso();
					List<Concurso> concursos = jogoNovo.getConcursos();
					logger.log(Level.INFO, "validacao loop");
					for (Concurso concurso : concursos) {
						if (concurso.getNumero().compareTo(numConcurso) > 0) {
							Concurso concursoNovo = concursoDao.findByNumero(concurso.getNumero());
							if (concursoNovo == null) {
								concurso.setJogo(jogoEncontrado);
								concursoDao.gravar(concurso);
							}
						}
					}
					
					logger.log(Level.INFO, "fim loop");

					validarConcursosSemGanhadores(concursos, 15);
					validarConcursosSemGanhadores(concursos, 14);
					validarConcursosSemGanhadores(concursos, 13);
					validarConcursosSemGanhadores(concursos, 12);
					validarConcursosSemGanhadores(concursos, 11);
					
					validarValoresNulos();
				}

				logger.log(Level.INFO, "fim thread");
				
			}
		}).start();
		
		logger.log(Level.INFO, "fim gravar");
		
		return modelAndView;

	}

	@RequestMapping("/dadosOk")
	public ModelAndView dadosOk(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("jogo/lotofacil/atualizaDadosOk");
		return modelAndView;
	}

	public Jogo processFile(File file) throws IOException, ParseException {
		Reader reader = null;
		BufferedReader bufferedReader = null;
		Jogo jogo = new Jogo();
		jogo.setNome(TipoJogo.LOTOFACIL.name());
		jogo.setTipoJogo(TipoJogo.LOTOFACIL);
		jogo.setValor(Constantes.VALOR_LOTO_FACIL);

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
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.getPremios().get(0).setAcumulado(new BigDecimal(linha));
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

	private Jogo processFile(InputStream inputStream) throws IOException, ParseException {
		Reader reader = null;
		BufferedReader bufferedReader = null;
		Jogo jogo = new Jogo();
		jogo.setNome(TipoJogo.LOTOFACIL.name());
		jogo.setTipoJogo(TipoJogo.LOTOFACIL);
		jogo.setValor(new BigDecimal(2.0));

		try {
			DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			reader = new BufferedReader(new InputStreamReader(inputStream));
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
							// System.out.println(linha);
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.getPremios().get(0).setAcumulado(new BigDecimal(linha));
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
	
	public boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

//		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return(password_verified);
	}

	private void validarConcursosSemGanhadores(List<Concurso> concursos, Integer nums) {
		logger.log(Level.INFO, "validarConcursosSemGanhadores inicio");
		
		List<Premio> premios = premioDao.findPremiosSemValor(nums);

		for (Premio premio : premios) {
			for (Concurso concurso : concursos) {
				if (premio.getConcurso().getNumero().equals(concurso.getNumero())) {
					premio.setAcumulado(concurso.getPremios().get(0).getAcumulado());
					premioDao.update(premio);
					break;
				}
			}			
		}
		
		logger.log(Level.INFO, "validarConcursosSemGanhadores fim");		
	}

	private void validarValoresNulos() {
		logger.log(Level.INFO, "validarValoresNulos inicio");
		
		premioDao.atualizarValorNulo();
		premioDao.atualizarAcumuladoNulo();
		
		logger.log(Level.INFO, "validarValoresNulos fim");
	}

}
