package com.mhj.jogos.model;

import java.math.BigInteger;

public class FrequenciaDezena {

	private Integer numero;
	private BigInteger quantidade;

	public FrequenciaDezena(Integer numero, BigInteger quantidade) {
		this.numero = numero;
		this.quantidade = quantidade;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public BigInteger getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigInteger quantidade) {
		this.quantidade = quantidade;
	}

}
