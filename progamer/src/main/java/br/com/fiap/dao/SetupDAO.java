package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.Setup;
import br.com.fiap.model.User;
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
		List<Setup> result = em.createQuery(query, Setup.class).getResultList();
		em.close();
		return result;
	}

	public Setup findById(int id) {
		Setup setup = em.find(Setup.class, id);
		em.close();
		return setup;
	}
	
	
	public void update(Setup setup) {
		em.getTransaction().begin();
		em.merge(setup);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete(int id) {
		em.getTransaction().begin();
		Setup setup = findById(id);
		em.remove(setup);
		em.getTransaction().commit();
		em.close();
	}

	public List<Setup> getSetupsByUser(User user) {
		System.out.println(user);
		TypedQuery<Setup> query = em.createQuery("SELECT s FROM Setup s WHERE user = :user", Setup.class).setParameter("user", user);
		List<Setup> result = query.getResultList();
		em.close();
		return result;
	}
	

}
