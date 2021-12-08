package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Servico;
import model.Venda;

public class VendaController {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("petshop");
	private EntityManager em = emf.createEntityManager();
	
	public void create(Venda p) {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
	}
	
	public void update(Venda p) {
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
	}
	
	public Venda read(int id){
		return em.find(Venda.class, id);
	}
	
	public void delete(Venda p) {
		em.getTransaction().begin();
		em.remove(p);
		em.getTransaction().commit();
	}
	
	public List getPagamentoList() {
		return em.createNativeQuery("SELECT descricao FROM forma_pagamento").getResultList();
	}
	
	public int convertPagamentoId(String forma_pagamento) {
		return Integer.parseInt(em.createNativeQuery("SELECT id_forma_pagamento FROM forma_pagamento WHERE (descricao = '" + forma_pagamento + "')").getSingleResult().toString());
	}
	
	public void shutdown() {
		em.close();
		emf.close();
	}
}
