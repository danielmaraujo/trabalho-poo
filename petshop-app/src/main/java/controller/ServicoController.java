package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Servico;

public class ServicoController {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("petshop");
	private EntityManager em = emf.createEntityManager();
	
	public void create(Servico p) {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
	}
	
	public void update(Servico p) {
		em.getTransaction().begin();
		em.merge(p);
		em.getTransaction().commit();
	}
	
	public Servico read(int id){
		return em.find(Servico.class, id);
	}
	
	public void delete(Servico p) {
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
	
	public List getTipoServicoList() {
		return em.createNativeQuery("SELECT descricao FROM tipo_servico").getResultList();
	}
	
	public int convertTipoServicoId(String tipo_servico) {
		return Integer.parseInt(em.createNativeQuery("SELECT id_tipo_servico FROM tipo_servico WHERE (descricao = '" + tipo_servico + "')").getSingleResult().toString());
	}
	
	public void shutdown() {
		em.close();
		emf.close();
	}

}
