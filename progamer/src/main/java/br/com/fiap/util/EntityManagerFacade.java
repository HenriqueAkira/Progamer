package br.com.fiap.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class EntityManagerFacade {
	
	private static EntityManagerFactory emf;
	
	private EntityManagerFacade() {
	}
	
	public static EntityManager get() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("progamer-persistence-unit");
		}
		return emf.createEntityManager();
		
	}
	
}
