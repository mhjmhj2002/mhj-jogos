package com.mhj.jogos.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mhj.jogos.domain.Concurso;
import com.mhj.jogos.domain.Jogo;
import com.mhj.jogos.model.FrequenciaDezena;
import com.mhj.jogos.model.JogoAcerto;

@Repository
@Transactional
public class ConcursoDao {

	@PersistenceContext
	private EntityManager manager;

	public void gravar(Concurso concurso) {
		manager.persist(concurso);
	}
	
	public List<FrequenciaDezena> dezenasMaisSorteadas() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT numero, quantidade from ");
		sql.append(" ( ");
		sql.append(" SELECT d1.numero numero, count(d1.numero) as quantidade ");
		sql.append(" FROM jogo j1, concurso c1, dezena d1 ");
		sql.append(" WHERE j1.id = c1.jogo_id ");
		sql.append(" AND c1.id = d1.concurso_id ");
		sql.append(" AND j1.id = (SELECT id from jogo where tipojogo = 'LOTOFACIL') ");
		sql.append(" GROUP BY d1.numero ");
		sql.append(" ORDER BY count(d1.numero) desc ");
		sql.append(" LIMIT 15 ");
		sql.append(" ) as numeros order by numero ");

		Query query = manager.createNativeQuery(sql.toString(), "frequenciaDezenaMapping");

		@SuppressWarnings("unchecked")
		List<FrequenciaDezena> resultList = query.getResultList();

		return resultList;
	}
	
	public List<FrequenciaDezena> dezenasMenosSorteadas() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT numero, quantidade from ");
		sql.append(" ( ");
		sql.append(" SELECT d1.numero numero, count(d1.numero) as quantidade ");
		sql.append(" FROM jogo j1, concurso c1, dezena d1 ");
		sql.append(" WHERE j1.id = c1.jogo_id ");
		sql.append(" AND c1.id = d1.concurso_id ");
		sql.append(" AND j1.id = (SELECT id from jogo where tipojogo = 'LOTOFACIL') ");
		sql.append(" GROUP BY d1.numero ");
		sql.append(" ORDER BY count(d1.numero) ");
		sql.append(" LIMIT 15 ");
		sql.append(" ) as numeros order by numero ");

		Query query = manager.createNativeQuery(sql.toString(), "frequenciaDezenaMapping");

		@SuppressWarnings("unchecked")
		List<FrequenciaDezena> resultList = query.getResultList();

		return resultList;
	}
	
	public List<JogoAcerto> maisSorteados() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.concurso, a.acertos, p.valor, a.data ");
		sql.append(" FROM premio p, ");
		sql.append(" ( ");
		sql.append(" SELECT c.numero as concurso, count(c.numero) as acertos, c.id as id_concurso, c.data ");
		sql.append(" FROM jogo j, concurso c, dezena d, ");
		sql.append(" (SELECT d1.numero numero ");
		sql.append(" FROM jogo j1, concurso c1, dezena d1 ");
		sql.append(" WHERE j1.id = c1.jogo_id ");
		sql.append(" AND c1.id = d1.concurso_id ");
		sql.append(" AND j1.id = (SELECT id from jogo where tipojogo = 'LOTOFACIL') ");
		sql.append(" GROUP BY d1.numero ");
		sql.append(" ORDER BY count(d1.numero) desc ");
		sql.append(" LIMIT 15) numeros ");
		sql.append(" WHERE j.id = c.Jogo_id ");
		sql.append(" AND c.id = d.Concurso_id ");
		sql.append(" AND numeros.numero = d.numero ");
		sql.append(" GROUP BY c.numero, c.id ");
		sql.append(" HAVING count(c.numero) > 10 ");
		sql.append(" ORDER BY count(c.numero) desc ");
		sql.append(" ) as a ");
		sql.append(" where p.concurso_id = a.id_concurso ");
		sql.append(" and p.quantidadeacertos = a.acertos ");
		sql.append(" order by a.acertos desc, a.concurso ");

		Query query = manager.createNativeQuery(sql.toString(), "jogoAcertoMapping");

		@SuppressWarnings("unchecked")
		List<JogoAcerto> resultList = query.getResultList();

		return resultList;
	}

	public List<JogoAcerto> menosSorteados() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT a.concurso, a.acertos, p.valor, a.data ");
		sql.append(" FROM premio p, ");
		sql.append(" ( ");
		sql.append(" SELECT c.numero as concurso, count(c.numero) as acertos, c.id as id_concurso, c.data ");
		sql.append(" FROM jogo j, concurso c, dezena d, ");
		sql.append(" (SELECT d1.numero numero ");
		sql.append(" FROM jogo j1, concurso c1, dezena d1 ");
		sql.append(" WHERE j1.id = c1.jogo_id ");
		sql.append(" AND c1.id = d1.concurso_id ");
		sql.append(" AND j1.id = (SELECT id from jogo where tipojogo = 'LOTOFACIL') ");
		sql.append(" GROUP BY d1.numero ");
		sql.append(" ORDER BY count(d1.numero) ");
		sql.append(" LIMIT 15) numeros ");
		sql.append(" WHERE j.id = c.Jogo_id ");
		sql.append(" AND c.id = d.Concurso_id ");
		sql.append(" AND numeros.numero = d.numero ");
		sql.append(" GROUP BY c.numero, c.id ");
		sql.append(" HAVING count(c.numero) > 10 ");
		sql.append(" ORDER BY count(c.numero) desc ");
		sql.append(" ) as a ");
		sql.append(" where p.concurso_id = a.id_concurso ");
		sql.append(" and p.quantidadeacertos = a.acertos ");
		sql.append(" order by a.acertos desc, a.concurso ");

		Query query = manager.createNativeQuery(sql.toString(), "jogoAcertoMapping");

		@SuppressWarnings("unchecked")
		List<JogoAcerto> resultList = query.getResultList();

		return resultList;
	}
	
	public BigDecimal somaMaisSorteados() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT sum(p.valor) ");
		sql.append(" FROM premio p, ");
		sql.append(" ( ");
		sql.append(" SELECT c.numero as concurso, count(c.numero) as acertos, c.id as id_concurso, c.data ");
		sql.append(" FROM jogo j, concurso c, dezena d, ");
		sql.append(" (SELECT d1.numero numero ");
		sql.append(" FROM jogo j1, concurso c1, dezena d1 ");
		sql.append(" WHERE j1.id = c1.jogo_id ");
		sql.append(" AND c1.id = d1.concurso_id ");
		sql.append(" AND j1.id = (SELECT id from jogo where tipojogo = 'LOTOFACIL') ");
		sql.append(" GROUP BY d1.numero ");
		sql.append(" ORDER BY count(d1.numero) desc ");
		sql.append(" LIMIT 15) numeros ");
		sql.append(" WHERE j.id = c.Jogo_id ");
		sql.append(" AND c.id = d.Concurso_id ");
		sql.append(" AND numeros.numero = d.numero ");
		sql.append(" GROUP BY c.numero, c.id ");
		sql.append(" HAVING count(c.numero) > 10 ");
		sql.append(" ORDER BY count(c.numero) desc ");
		sql.append(" ) as a ");
		sql.append(" where p.concurso_id = a.id_concurso ");
		sql.append(" and p.quantidadeacertos = a.acertos ");

		Query query = manager.createNativeQuery(sql.toString());

		BigDecimal soma = (BigDecimal) query.getSingleResult();

		return soma;
	}

	public BigDecimal somaMenosSorteados() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT sum(p.valor) ");
		sql.append(" FROM premio p, ");
		sql.append(" ( ");
		sql.append(" SELECT c.numero as concurso, count(c.numero) as acertos, c.id as id_concurso, c.data ");
		sql.append(" FROM jogo j, concurso c, dezena d, ");
		sql.append(" (SELECT d1.numero numero ");
		sql.append(" FROM jogo j1, concurso c1, dezena d1 ");
		sql.append(" WHERE j1.id = c1.jogo_id ");
		sql.append(" AND c1.id = d1.concurso_id ");
		sql.append(" AND j1.id = (SELECT id from jogo where tipojogo = 'LOTOFACIL') ");
		sql.append(" GROUP BY d1.numero ");
		sql.append(" ORDER BY count(d1.numero) ");
		sql.append(" LIMIT 15) numeros ");
		sql.append(" WHERE j.id = c.Jogo_id ");
		sql.append(" AND c.id = d.Concurso_id ");
		sql.append(" AND numeros.numero = d.numero ");
		sql.append(" GROUP BY c.numero, c.id ");
		sql.append(" HAVING count(c.numero) > 10 ");
		sql.append(" ORDER BY count(c.numero) desc ");
		sql.append(" ) as a ");
		sql.append(" where p.concurso_id = a.id_concurso ");
		sql.append(" and p.quantidadeacertos = a.acertos ");

		Query query = manager.createNativeQuery(sql.toString());

		BigDecimal soma = (BigDecimal) query.getSingleResult();

		return soma;
	}

	public BigDecimal gastoConcursos() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT (qtd_concurso.qtd * valor_jogo.valor) as gasto ");  
		sql.append(" FROM "); 
		sql.append(" (SELECT count(id) as qtd FROM concurso) AS qtd_concurso, ");
		sql.append(" (SELECT j.valor FROM jogo j WHERE tipojogo = 'LOTOFACIL') AS valor_jogo ");

		Query query = manager.createNativeQuery(sql.toString());

		BigDecimal gasto = (BigDecimal) query.getSingleResult();

		return gasto;
	}

	public void deleteByJogo(Jogo jogo) {
		for (Concurso concurso : jogo.getConcursos()) {
			manager.detach(concurso);
		}
	}

	public Concurso getUltimoConcurso() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT c ");  
		sql.append(" FROM Concurso c "); 
		sql.append(" WHERE c.id = (SELECT MAX(id) FROM Concurso) ");

		Query query = manager.createQuery(sql.toString(), Concurso.class);

		Concurso concurso = (Concurso) query.getSingleResult();

		return concurso;
	}

}
