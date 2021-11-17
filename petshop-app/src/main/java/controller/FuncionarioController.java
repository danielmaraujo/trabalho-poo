package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Funcionario;

public class FuncionarioController {
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("petshop");
	private EntityManager em = emf.createEntityManager();
	
	public void create(Funcionario f) {
		em.getTransaction().begin();
		em.persist(f);
		em.getTransaction().commit();
	}
	
	public void update(Funcionario f) {
		em.getTransaction().begin();
		em.merge(f);
		em.getTransaction().commit();
	}
	
	public Funcionario read(String cpf){
		return em.find(Funcionario.class, cpf);
	}
	
	public void delete(Funcionario f) {
		em.getTransaction().begin();
		em.remove(f);
		em.getTransaction().commit();
	}
	
	public List getFuncaoList() {
		return em.createNativeQuery("SELECT descricao FROM funcao_funcionario").getResultList();
	}
	
	public int convertFuncaoId(String funcao) {
		return Integer.parseInt(em.createNativeQuery("SELECT id_funcao_funcionario FROM funcao_funcionario WHERE (descricao = '" + funcao + "')").getSingleResult().toString());
	}
	
	public void shutdown() {
		em.close();
		emf.close();
	}
}
