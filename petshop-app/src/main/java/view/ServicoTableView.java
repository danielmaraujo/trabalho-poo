package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Servico;

import javax.swing.JTable;

public class ServicoTableView extends JFrame {

	private JPanel contentPane;
	private JTable table;


	public ServicoTableView() {
		setTitle("Listagem");
		setBounds(100, 100, 881, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		table = new JTable();
		contentPane.add(table, BorderLayout.CENTER);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("petshop");
		EntityManager em = emf.createEntityManager();
		
		String[] cols= {"ID","CPF do Cliente", "Nome do Pet", "CPF do Funcionário", "Data", "Forma de Pagamento", "Tipo de Servico"};
		
		List<Servico> list = (List<Servico>) em.createQuery("from Servico").getResultList();
		DefaultTableModel dtm = new DefaultTableModel(cols, 0);
		
		for (Servico s:list) {
			String[] row = {
					Integer.toString(s.getId_servico()),
					em.createNativeQuery("SELECT cliente.cpf FROM pet INNER JOIN cliente ON pet.cliente_cpf = cliente.cpf WHERE id_pet = 1").getSingleResult().toString(),
					em.createNativeQuery("SELECT pet.nome FROM pet WHERE id_pet = " + s.getPet_id_pet()).getSingleResult().toString(),
					s.getFuncionario_cpf(),
					s.getData().toString(),
					em.createNativeQuery("SELECT forma_pagamento.descricao FROM forma_pagamento WHERE id_forma_pagamento = " + s.getForma_pagamento()).getSingleResult().toString(),
					em.createNativeQuery("SELECT tipo_servico.descricao FROM tipo_servico WHERE id_tipo_servico = " + s.getTipo_servico()).getSingleResult().toString()
			};
			dtm.addRow(row);
		}
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(dtm);
		setVisible(true);
		
	}

}
