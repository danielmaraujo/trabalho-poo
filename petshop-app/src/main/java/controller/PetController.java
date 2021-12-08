package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Pet;

public class PetController {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("petshop");
	private EntityManager em = emf.createEntityManager();
	
	public void create(Pet p) {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
	}
	
	public void update(Pet p) {
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
	}
	
	public Pet read(String cpf, String nome){
		return (Pet) em.createQuery("FROM Pet WHERE cliente_cpf = '"+ cpf + "' AND nome = '" + nome + "'").getSingleResult();
	}
	
	public void delete(Pet p) {
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
	}
	
	public void shutdown() {
		em.close();
		emf.close();
	}
}
