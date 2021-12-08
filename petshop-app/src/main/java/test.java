import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Pet;

public class test {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("petshop");
		EntityManager em = emf.createEntityManager();
		System.out.println(em.createNativeQuery("SELECT s.id_servico, p.nome, c.cpf, s.funcionario_cpf, s.data, fp.descricao fdescricao, ts.descricao tsdescricao FROM \n"
				+ "	servico s \n"
				+ "    INNER JOIN pet p ON s.pet_id_pet = p.id_pet \n"
				+ "    INNER JOIN cliente c ON p.cliente_cpf = c.cpf\n"
				+ "    INNER JOIN forma_pagamento fp ON s.forma_pagamento = fp.id_forma_pagamento\n"
				+ "    INNER JOIN tipo_servico ts ON s.tipo_servico = ts.id_tipo_servico;"));
		
		System.out.println(em.createNativeQuery("SELECT pet.nome FROM pet WHERE id_pet = 1").getSingleResult().toString());
	}

}
