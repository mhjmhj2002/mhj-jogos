package com.mhj.jogos.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;

import org.springframework.format.annotation.DateTimeFormat;

import com.mhj.jogos.model.FrequenciaDezena;

@SqlResultSetMapping(name = "frequenciaDezenaMapping", classes = {
		@ConstructorResult(targetClass = FrequenciaDezena.class, columns = {
				@ColumnResult(name = "numero", type = Integer.class),
				@ColumnResult(name = "quantidade", type = BigInteger.class) }) 		
})

@Entity
public class Concurso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long numero;
	@DateTimeFormat
	private Date data;
	private BigDecimal arrecadacao;

	@ManyToOne
	private Jogo jogo;

	@OneToMany(mappedBy = "concurso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Dezena> dezenas;

	@OneToMany(mappedBy = "concurso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Premio> premios;

	public Concurso() {
		dezenas = new ArrayList<>();
		premios = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public BigDecimal getArrecadacao() {
		return arrecadacao;
	}

	public void setArrecadacao(BigDecimal arrecadacao) {
		this.arrecadacao = arrecadacao;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}

	public List<Dezena> getDezenas() {
		return dezenas;
	}

	public void setDezenas(List<Dezena> dezenas) {
		this.dezenas = dezenas;
	}

	public List<Premio> getPremios() {
		return premios;
	}

	public void setPremios(List<Premio> premios) {
		this.premios = premios;
	}
}
