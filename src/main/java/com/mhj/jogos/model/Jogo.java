package com.mhj.jogos.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Jogo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6128114598966977515L;
	
	private Integer[] dezenas;
	
	private String quantidadeDezenas;
	
	private String[] dezenasSelecionadas;
	
	private String validator;

	public Jogo() {
		super();
	}

	public void setDezenas(Integer[] dezenas) {
		this.dezenas = dezenas;
	}

	public Integer[] getDezenas() {
		return dezenas;
	}

	public List<Integer> getDezenasList() {
		return Arrays.asList(dezenas);
	}

	public String getQuantidadeDezenas() {
		return quantidadeDezenas;
	}

	public void setQuantidadeDezenas(String quantidadeDezenas) {
		this.quantidadeDezenas = quantidadeDezenas;
	}

	public String[] getDezenasSelecionadas() {
		return dezenasSelecionadas;
	}

	public void setDezenasSelecionadas(String[] dezenasSelecionadas) {
		this.dezenasSelecionadas = dezenasSelecionadas;
	}

	public String getValidator() {
		return validator;
	}

	public void setValidator(String validator) {
		this.validator = validator;
	}

}
