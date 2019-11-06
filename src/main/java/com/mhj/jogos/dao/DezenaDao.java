package com.mhj.jogos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mhj.jogos.domain.Concurso;
import com.mhj.jogos.domain.Dezena;
import com.mhj.jogos.domain.Premio;

@Repository
@Transactional
public class DezenaDao {

	@PersistenceContext
	private EntityManager manager;

	public void insert(Dezena dezena) {
		manager.persist(dezena);
	}
	
	public void update(Dezena dezena) {
		manager.merge(dezena);
	}

	public Dezena find(Long id) {
		return manager.createQuery("select j from Dezena j where j.id = :id", Dezena.class).setParameter("id", id)
				.getSingleResult();
	}

	public void delete(Dezena dezena) {
		manager.remove(dezena);		
	}

	public List<Dezena> findByConcurso(Concurso concurso) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT d ");  
		sql.append(" FROM Dezena d "); 
		sql.append(" WHERE d.concurso.id = :idConcurso ");

		Query query = manager.createQuery(sql.toString(), Dezena.class);
		query.setParameter("idConcurso", concurso.getId());

		@SuppressWarnings("unchecked")
		List<Dezena> resultList = query.getResultList();

		return resultList;
	}

}
