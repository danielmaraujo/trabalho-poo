package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "funcionario")
public class Funcionario {
	
	@Id
	private String cpf;
	private String nome;
	private String telefone;
	private float salario;
	private int funcao;
	
	
	
	public Funcionario() {
		super();
	}

	public Funcionario(String cpf, String nome, String telefone, float salario, int funcao) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
		this.salario = salario;
		this.funcao = funcao;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public float getSalario() {
		return salario;
	}
	public void setSalario(float salario) {
		this.salario = salario;
	}
	public int getFuncao() {
		return funcao;
	}
	public void setFuncao(int funcao) {
		this.funcao = funcao;
	}
}
