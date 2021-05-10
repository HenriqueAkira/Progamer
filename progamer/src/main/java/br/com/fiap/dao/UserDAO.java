package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.model.Setup;
import br.com.fiap.model.User;
import br.com.fiap.util.EntityManagerFacade;

public class UserDAO {

	EntityManager em = EntityManagerFacade.get();
	
	public void save(User user) {
		
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		
		em.close();
				
	
	}

	public List<User> getAll() {
		String query = "select s from User s";
		return em.createQuery(query, User.class).getResultList();
		
	}

	public User findById(Integer id) {
		User user = em.find(User.class, id);
		return user;
	}

	public void update(User user) {
		em.getTransaction().begin();
		em.merge(user);
		em.flush();
		em.getTransaction().commit();
	}
	
	public void delete(int id) {
		em.getTransaction().begin();
		User user = findById(id);
		em.remove(user);
		em.getTransaction().commit();
	}
	
}
