package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Servico;
import model.Venda;
import javax.swing.JScrollPane;

public class VendaTableView extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public VendaTableView() {
		setTitle("Listagem");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("petshop");
		EntityManager em = emf.createEntityManager();
		
		String[] cols= {"ID","CPF do Cliente", "CPF do Funcionário", "Valor total", "Data", "Forma de Pagamento"};
		
		List<Venda> list = (List<Venda>) em.createQuery("from Venda").getResultList();
		DefaultTableModel dtm = new DefaultTableModel(cols, 0);
		
		for (Venda s:list) {
			String[] row = {
					Integer.toString(s.getId_venda()),
					s.getCliente_cpf(),
					s.getFuncionario_cpf(),
					Float.toString(s.getValor_total()),
					s.getData().toString(),
					em.createNativeQuery("SELECT forma_pagamento.descricao FROM forma_pagamento WHERE id_forma_pagamento = " + s.getForma_pagamento()).getSingleResult().toString()
					};
			dtm.addRow(row);
		}
		
		em.close();
		emf.close();
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(dtm);
		setVisible(true);
	}

}
