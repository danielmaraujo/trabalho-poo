package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Cliente;

public class ClienteController {
	
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("petshop");
	private EntityManager em = emf.createEntityManager();
	
	public void create(Cliente c) {
		em.getTransaction().begin();
		em.persist(c);
		em.getTransaction().commit();
	}
	
	public void update(Cliente c) {
		em.getTransaction().begin();
		em.merge(c);
		em.getTransaction().commit();
	}
	
	public Cliente read(String cpf){
		return em.find(Cliente.class, cpf);
	}
	
	public void delete(Cliente c) {
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
	}
	
	public void shutdown() {
		em.close();
		emf.close();
	}
	
}
