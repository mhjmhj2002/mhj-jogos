package com.mhj.jogos.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class JogoAcerto {

	private BigInteger concurso;
	private BigInteger acertos;
	private BigDecimal valor;
	@DateTimeFormat
	private Date data;

	public JogoAcerto(BigInteger concurso, BigInteger acertos, BigDecimal valor, Date data) {
		this.concurso = concurso;
		this.acertos = acertos;
		this.valor = valor;
		this.data = data;
	}

	public BigInteger getConcurso() {
		return concurso;
	}

	public void setConcurso(BigInteger concurso) {
		this.concurso = concurso;
	}

	public BigInteger getAcertos() {
		return acertos;
	}

	public void setAcertos(BigInteger acertos) {
		this.acertos = acertos;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
