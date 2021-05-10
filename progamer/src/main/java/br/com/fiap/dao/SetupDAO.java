package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;

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

	public Setup findById(int id) {
		Setup setup = em.find(Setup.class, id);
		return setup;
	}
	
	
	public void update(Setup setup) {
		em.getTransaction().begin();
		em.merge(setup);
		em.flush();
		em.getTransaction().commit();
	}
	
	public void delete(int id) {
		em.getTransaction().begin();
		Setup setup = findById(id);
		em.remove(setup);
		em.getTransaction().commit();
	}
	

}
