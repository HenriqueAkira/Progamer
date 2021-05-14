package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
		List<User> result = em.createQuery(query, User.class).getResultList();
		return result;
		
	}

	public User findById(Integer id) {
		User user = em.find(User.class, id);
		em.close();
		return user;
	}

	public void update(User user) {
		em.getTransaction().begin();
		em.merge(user);
		em.flush();
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete(int id) {
		em.getTransaction().begin();
		User user = findById(id);
		em.remove(user);
		em.getTransaction().commit();
		em.close();
	}

	public User exist(User user) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE email = :email AND password = :password", User.class)
				.setParameter("password", user.getPassword())
				.setParameter("email", user.getEmail());
		User result;
		try {
			result = query.getSingleResult();
			return result;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
		
	}
	
	
		
	
	
}
