package model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import controller.PetController;

@Entity
@Table (name = "servico")

public class Servico {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_servico;
	
	private int pet_id_pet;
	private String funcionario_cpf;
	
	@Temporal(value = TemporalType.DATE)
	private Date data;
	
	private int forma_pagamento;
	private int tipo_servico;
	public Servico(String cliente_cpf, String nome_pet, String funcionario_cpf, Date data, int forma_pagamento, int tipo_servico) {
		super();
		PetController pet = new PetController();
		this.pet_id_pet = pet.read(cliente_cpf, nome_pet).getId_pet();
		this.funcionario_cpf = funcionario_cpf;
		this.data = data;
		this.forma_pagamento = forma_pagamento;
		this.tipo_servico = tipo_servico;
	}
	public Servico() {
		super();
	}
	public int getId_servico() {
		return id_servico;
	}
	public void setId_servico(int id_servico) {
		this.id_servico = id_servico;
	}
	public int getPet_id_pet() {
		return pet_id_pet;
	}
	public void setPet_id_pet(int pet_id_pet) {
		this.pet_id_pet = pet_id_pet;
	}
	public String getFuncionario_cpf() {
		return funcionario_cpf;
	}
	public void setFuncionario_cpf(String funcionario_cpf) {
		this.funcionario_cpf = funcionario_cpf;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getForma_pagamento() {
		return forma_pagamento;
	}
	public void setForma_pagamento(int forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}
	public int getTipo_servico() {
		return tipo_servico;
	}
	public void setTipo_servico(int tipo_servico) {
		this.tipo_servico = tipo_servico;
	}
	
	
}
