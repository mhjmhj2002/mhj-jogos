package com.mhj.jogos.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mhj.jogos.domain.Concurso;
import com.mhj.jogos.domain.Ganhador;
import com.mhj.jogos.domain.Premio;

@Repository
@Transactional
public class GanhadorDao {

	@PersistenceContext
	private EntityManager manager;

	public void insert(Ganhador ganhador) {
		manager.persist(ganhador);
	}
	
	public void update(Ganhador ganhador) {
		manager.merge(ganhador);
	}

	public Ganhador find(Long id) {
		return manager.createQuery("select j from Ganhador j where j.id = :id", Ganhador.class).setParameter("id", id)
				.getSingleResult();
	}

	public void delete(Ganhador ganhador) {
		manager.remove(ganhador);		
	}

	public List<Ganhador> findByPremio(Premio premio) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT g ");  
		sql.append(" FROM Ganhador g "); 
		sql.append(" WHERE g.premio.id = :idPremio ");

		Query query = manager.createQuery(sql.toString(), Ganhador.class);
		query.setParameter("idPremio", premio.getId());

		@SuppressWarnings("unchecked")
		List<Ganhador> resultList = query.getResultList();

		return resultList;
	}

}
