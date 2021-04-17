package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.model.Setup;
import br.com.fiap.util.EntityManagerFacade;

public class SetupDAO {

	EntityManager em = EntityManagerFacade.get();
	
	public void save(Setup setup) {
		
		em.getTransaction().begin();
		em.persist(setup);
		em.getTransaction().commit();
		
		em.close();
		
	}

	public List<Setup> getAll() {
		String query = "SELECT s from Setup s";
		return em.createQuery(query, Setup.class).getResultList();
		
		
	}
	
	

}
