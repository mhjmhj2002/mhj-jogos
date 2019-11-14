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

	public void setDezenas(Integer[] dezenas) {
		this.dezenas = dezenas;
	}

	public Integer[] getDezenas() {
		return dezenas;
	}

	public List<Integer> getDezenasList() {
		return Arrays.asList(dezenas);
	}

}
