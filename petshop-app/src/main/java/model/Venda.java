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
@Table (name = "venda")

public class Venda {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id_venda;
	
	private String cliente_cpf;
	private String funcionario_cpf;
	private float valor_total;
	
	@Temporal(value = TemporalType.DATE)
	private Date data;
	
	private int forma_pagamento;

	public Venda(String cliente_cpf, String funcionario_cpf, float valor_total, Date data, int forma_pagamento) {
		super();
		this.cliente_cpf = cliente_cpf;
		this.funcionario_cpf = funcionario_cpf;
		this.valor_total = valor_total;
		this.data = data;
		this.forma_pagamento = forma_pagamento;
	}
	
	public Venda() {
		super();
	}

	public int getId_venda() {
		return id_venda;
	}

	public void setId_venda(int id_venda) {
		this.id_venda = id_venda;
	}

	public String getCliente_cpf() {
		return cliente_cpf;
	}

	public void setCliente_cpf(String cliente_cpf) {
		this.cliente_cpf = cliente_cpf;
	}

	public String getFuncionario_cpf() {
		return funcionario_cpf;
	}

	public void setFuncionario_cpf(String funcionario_cpf) {
		this.funcionario_cpf = funcionario_cpf;
	}

	public float getValor_total() {
		return valor_total;
	}

	public void setValor_total(float valor_total) {
		this.valor_total = valor_total;
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
	
	
}
