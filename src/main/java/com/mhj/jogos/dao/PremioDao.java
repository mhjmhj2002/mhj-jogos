package com.mhj.jogos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mhj.jogos.domain.Concurso;
import com.mhj.jogos.domain.Premio;

@Repository
@Transactional
public class PremioDao {

	@PersistenceContext
	private EntityManager manager;

	public void insert(Premio premio) {
		manager.persist(premio);
	}
	
	public void update(Premio premio) {
		manager.merge(premio);
	}

	public Premio find(Long id) {
		return manager.createQuery("select j from Premio j where j.id = :id", Premio.class).setParameter("id", id)
				.getSingleResult();
	}

	public void delete(Premio premio) {
		manager.remove(premio);		
	}

	public List<Premio> findByConcurso(Concurso concurso) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT p ");  
		sql.append(" FROM Premio p "); 
		sql.append(" WHERE p.concurso.id = :idConcurso ");

		Query query = manager.createQuery(sql.toString(), Premio.class);
		query.setParameter("idConcurso", concurso.getId());

		@SuppressWarnings("unchecked")
		List<Premio> resultList = query.getResultList();

		return resultList;
	}

	public List<Premio> findPremiosSemValor(int acertos) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT p ");  
		sql.append(" FROM Premio p "); 
		sql.append(" WHERE p.quantidadeAcertos = :acertos ");
		sql.append(" AND p.valor = 0 ");
		sql.append(" AND p.acumulado = 0 ");
		sql.append(" ORDER BY p.concurso.id ");

		Query query = manager.createQuery(sql.toString(), Premio.class);
		query.setParameter("acertos", acertos);

		@SuppressWarnings("unchecked")
		List<Premio> resultList = query.getResultList();

		return resultList;
	}

	public void atualizarValorNulo() {
		Query query = manager.createNativeQuery(" UPDATE PREMIO SET VALOR = 0 WHERE VALOR IS NULL ");
		query.executeUpdate();
	}

	public void atualizarAcumuladoNulo() {
		Query query = manager.createNativeQuery(" UPDATE PREMIO SET ACUMULADO = 0 WHERE ACUMULADO IS NULL ");
		query.executeUpdate();
		
	}

}
