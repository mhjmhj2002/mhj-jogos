package com.mhj.jogos.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mhj.jogos.domain.Jogo;
import com.mhj.jogos.enums.TipoJogo;

@Repository
@Transactional
public class JogoDao {

	@PersistenceContext
	private EntityManager manager;

	public void insert(Jogo jogo) {
		manager.persist(jogo);
	}
	
	public void update(Jogo jogo) {
		manager.merge(jogo);
	}

	public Jogo find(Long id) {
		return manager.createQuery("select j from Jogo j where j.id = :id", Jogo.class).setParameter("id", id)
				.getSingleResult();
	}

	public Jogo findByTipo(TipoJogo tipoJogo) {

		TypedQuery<Jogo> query = manager.createQuery("select j from Jogo j where j.tipoJogo = :tipoJogo", Jogo.class);
		query.setParameter("tipoJogo", tipoJogo);

		try {

			return query.getSingleResult();

		} catch (NoResultException e) {
			return null;
		}

	}

	public void delete(Jogo jogo) {
		Object c = manager.merge(jogo);
		manager.remove(c);		
	}

}
