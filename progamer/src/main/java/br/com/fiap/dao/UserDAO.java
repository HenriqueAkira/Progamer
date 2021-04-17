package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;

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
}
