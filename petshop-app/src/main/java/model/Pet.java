package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name = "pet")

public class Pet {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_pet;
	
	private String nome;
	private String especie;
	private String raca;
	
	@Temporal(value = TemporalType.DATE)
	private Date data_nascimento;
	
	private String cliente_cpf;
	
	public Pet() {
	}

	public Pet(int id_pet, String nome, String especie, String raca, Date data_nascimento, String cliente_cpf) {
		super();
		this.id_pet = id_pet;
		this.cliente_cpf = cliente_cpf;
		this.nome = nome;
		this.especie = especie;
		this.raca = raca;
		this.data_nascimento = data_nascimento;
	}

	public int getId_pet() {
		return id_pet;
	}

	public void setId_pet(int id_pet) {
		this.id_pet = id_pet;
	}

	public String getCliente_cpf() {
		return cliente_cpf;
	}

	public void setCliente_cpf(String cliente_cpf) {
		this.cliente_cpf = cliente_cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getRaca() {
		return raca;
	}

	public void setRaca(String raca) {
		this.raca = raca;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	
	
	
	
}
