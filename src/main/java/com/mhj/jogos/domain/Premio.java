package com.mhj.jogos.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Premio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer quantidadeAcertos;
	private BigDecimal valor;
	private BigDecimal acumulado;

	@ManyToOne
	private Concurso concurso;

	@OneToMany(mappedBy = "premio", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Ganhador> ganhadores;

	public Premio() {
		ganhadores = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQuantidadeAcertos() {
		return quantidadeAcertos;
	}

	public void setQuantidadeAcertos(Integer quantidadeAcertos) {
		this.quantidadeAcertos = quantidadeAcertos;
	}

	public BigDecimal getValor() {
		if (new BigDecimal(0).equals(valor)) {
			return getAcumulado();
		}
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Concurso getConcurso() {
		return concurso;
	}

	public void setConcurso(Concurso concurso) {
		this.concurso = concurso;
	}

	public List<Ganhador> getGanhadores() {
		return ganhadores;
	}

	public void setGanhadores(List<Ganhador> ganhadores) {
		this.ganhadores = ganhadores;
	}

	public BigDecimal getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(BigDecimal acumulado) {
		this.acumulado = acumulado;
	}

}
