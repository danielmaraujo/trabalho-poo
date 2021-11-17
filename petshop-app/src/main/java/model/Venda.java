package model;

import java.util.Date;

public class Venda {
	private Cliente cliente;
	private Funcionario funcionario;
	private float valor_total;
	private Date data;
	private String forma_pagamento;
	
	public Venda(Cliente cliente, Funcionario funcionario, float valor_total, Date data, String forma_pagamento) {
		super();
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.valor_total = valor_total;
		this.data = data;
		this.forma_pagamento = forma_pagamento;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
	
	public String getForma_pagamento() {
		return forma_pagamento;
	}
	
	public void setForma_pagamento(String forma_pagamento) {
		this.forma_pagamento = forma_pagamento;
	}
	
	
}
