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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;

import com.mhj.jogos.enums.TipoJogo;
import com.mhj.jogos.model.JogoAcerto;

@SqlResultSetMapping(name = "jogoAcertoMapping", classes = {
		@ConstructorResult(targetClass = JogoAcerto.class, columns = {
				@ColumnResult(name = "concurso", type = BigInteger.class),
				@ColumnResult(name = "acertos", type = BigInteger.class),
				@ColumnResult(name = "valor", type = BigDecimal.class),
				@ColumnResult(name = "data", type = Date.class) })
})

@Entity
public class Jogo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private BigDecimal valor;
	@Enumerated(EnumType.STRING)
	private TipoJogo tipoJogo;

	@OneToMany(mappedBy = "jogo", cascade = CascadeType.ALL)
	private List<Concurso> concursos;

	public Jogo() {
		concursos = new ArrayList<>();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Concurso> getConcursos() {
		return concursos;
	}

	public void setConcursos(List<Concurso> concursos) {
		this.concursos = concursos;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoJogo getTipoJogo() {
		return tipoJogo;
	}

	public void setTipoJogo(TipoJogo tipoJogo) {
		this.tipoJogo = tipoJogo;
	}

}
