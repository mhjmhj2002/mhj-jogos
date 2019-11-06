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

}
