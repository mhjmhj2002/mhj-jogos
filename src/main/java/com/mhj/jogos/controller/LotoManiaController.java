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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mhj.jogos.dao.ConcursoDao;
import com.mhj.jogos.dao.JogoDao;
import com.mhj.jogos.domain.Concurso;
import com.mhj.jogos.domain.Jogo;
import com.mhj.jogos.util.MhjUtilFile;

@Controller
@RequestMapping("/lotomania")
public class LotoManiaController {
	
	@Autowired
	private JogoDao jogoDao;
	
	@Autowired
	private ConcursoDao concursoDao;

	@RequestMapping("/gravar")
	public void lerSorteios() {
		List<File> files = getSortedFiles("C:\\Users\\majara\\Desktop\\Jogos\\Lotomania\\D_lotoma", "D_LOTMAN");

		for (File file : files) {
			try {
				Jogo jogo = this.processFile(file);
				for (Concurso concurso : jogo.getConcursos()) {
					concursoDao.gravar(concurso);
				}
				jogoDao.insert(jogo);
				//this.classificar(jogo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private Jogo processFile(File file) throws IOException, ParseException {
		Reader reader = null;
		BufferedReader bufferedReader = null;
		Jogo jogo = new Jogo();
		try {
			DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
			reader = new InputStreamReader(new FileInputStream(file));
			bufferedReader = new BufferedReader(reader);
			String linha = "";
			int numeroRegistro = 1;
			try {
				Concurso concurso = new Concurso();
				while ((linha = bufferedReader.readLine()) != null) {
					if (linha.startsWith("<td ") && !linha.contains("&nbsp")) {
//						System.out.println("linha: "+linha+"registro: "+numeroRegistro);
						linha = limpaRegistro(linha);
//							numeroRegistro = new Long(linha);
						switch (numeroRegistro) {
						case 1:
							concurso = new Concurso();
							concurso.setNumero(new Long(linha));
//							//System.out.println(linha);
							break;
						case 2:
							concurso.setData(format.parse(linha));
//							//System.out.println(linha);
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
						case 18:
						case 19:
						case 20:
						case 21:
						case 22:
//							concurso.getBolas().add(new Integer(linha));
//							//System.out.println(linha);
							break;
						case 23:
							linha = linha.replaceAll("\\.", "");
							linha = linha.replaceAll(",", ".");
							concurso.setArrecadacao(new BigDecimal(linha));
//							//System.out.println(linha);
							break;
						case 24:	
							//System.out.println(linha);
							break;
						case 25:
							//System.out.println(linha);
							break;
						case 26:
							//System.out.println(linha);
							break;
						case 27:
							//System.out.println(linha);
							break;
						case 28:
							//System.out.println(linha);
							break;
						case 29:
							//System.out.println(linha);
							break;
						case 30:
							//System.out.println(linha);
							break;
						case 31:
							//System.out.println(linha);
							break;
						case 32:
							//System.out.println(linha);
							break;
						case 33:
							//System.out.println(linha);
							break;
						case 34:
							//System.out.println(linha);
							break;
						case 35:
							//System.out.println(linha);
							break;
						case 36:
							//System.out.println(linha);
							break;
						case 37:
							//System.out.println(linha);
							break;
						case 38:
							//System.out.println(linha);
							break;
						case 39:
							//System.out.println(linha);
							break;
						case 40:
							//System.out.println(linha);
							break;
						case 41:
							//System.out.println(linha);
							break;
						case 42:
							//System.out.println(linha);
							break;
						case 43:
//							System.out.println(linha);
							numeroRegistro = 0;
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
	
	private String limpaRegistro(String linha){

		linha = linha.replaceAll("<td rowspan=\"1\">", "");
		linha = linha.replaceAll("<td rowspan=\"2\">", "");
		linha = linha.replaceAll("<td rowspan=\"3\">", "");
		linha = linha.replaceAll("<td rowspan=\"4\">", "");
		linha = linha.replaceAll("<td rowspan=\"5\">", "");
		linha = linha.replaceAll("<td rowspan=\"6\">", "");
		return linha.replaceAll("</td>", "");
		
	}

	public final List<File> getSortedFiles(String folderName, String fileBaseName) {
		return MhjUtilFile.obterArquivos(folderName, fileBaseName);
	}

}
