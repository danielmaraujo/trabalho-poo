package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.ServicoController;
import controller.VendaController;
import model.Servico;
import model.Venda;

public class VendaView extends JFrame {

	private JPanel contentPane;
	private JTextField txtCpfCliente;
	private JLabel lblCpfFunc;
	private JTextField txtCpfFunc;
	private JButton btnInserir;
	private JButton btnConsultar;
	private JButton btnAlterar;
	private JButton btnApagar;
	private JLabel lblPagamento;
	private JComboBox pagamentoComboBox;
	private JLabel lblValor;
	private JTextField txtValor;

	public VendaView() {
		setResizable(false);
		setTitle("Gerenciar Venda");
		setBounds(100, 100, 506, 225);
		contentPane = new JPanel();
		contentPane.setToolTipText(" Apenas números");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		
		SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
		
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(45dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblCpfCliente = new JLabel("CPF do Cliente:");
		contentPane.add(lblCpfCliente, "2, 2, right, default");
		
		txtCpfCliente = new JTextField();
		txtCpfCliente.setToolTipText(" Apenas números");
		contentPane.add(txtCpfCliente, "4, 2, 5, 1, fill, default");
		txtCpfCliente.setColumns(10);
		
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  VendaController controller = new VendaController(); 
				  Venda f = new Venda(txtCpfCliente.getText(), txtCpfFunc.getText(), Float.parseFloat(txtValor.getText()), new Date(), 
						  controller.convertPagamentoId(pagamentoComboBox.getSelectedItem().toString())); 
				  controller.create(f);
				  if(controller.read(f.getId_venda()) != null) {
				  JOptionPane.showMessageDialog(null,"Venda cadastrada corretamente!"
				  ,"Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE); }else {
				  JOptionPane.showMessageDialog(null,"Erro no cadastro","Mensagem de Erro"
				  ,JOptionPane.ERROR_MESSAGE); } controller.shutdown();
				 			}
		});
		
		lblCpfFunc = new JLabel("CPF do Funcionário:");
		contentPane.add(lblCpfFunc, "2, 4, right, default");
		
		txtCpfFunc = new JTextField();
		contentPane.add(txtCpfFunc, "4, 4, 5, 1, fill, default");
		txtCpfFunc.setColumns(10);
		
		lblValor = new JLabel("Valor total:");
		contentPane.add(lblValor, "2, 6, right, default");
		
		txtValor = new JTextField();
		txtValor.setColumns(10);
		contentPane.add(txtValor, "4, 6, 5, 1, fill, default");
		
		lblPagamento = new JLabel("Forma de pagamento:");
		contentPane.add(lblPagamento, "2, 8, right, default");
		
		pagamentoComboBox = new JComboBox(this.getPagamentoComboBox().toArray());
		contentPane.add(pagamentoComboBox, "4, 8, 5, 1, fill, default");
		contentPane.add(btnInserir, "2, 12, fill, default");
		
		btnConsultar = new JButton("Listar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendaTableView table = new VendaTableView();
			}
		});
		contentPane.add(btnConsultar, "4, 12, fill, default");
		
		btnApagar = new JButton("Apagar");
		contentPane.add(btnApagar, "6, 12, center, default");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendaController controller = new VendaController(); 
				Venda f = controller.read(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da venda a ser apagada")));
				if(f != null) {
					controller.delete(f);
					JOptionPane.showMessageDialog(null,"Venda excluída corretamente!","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Venda não encontrada!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
				}
				controller.shutdown();
			}
		});
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VendaController controller = new VendaController(); 
				Venda f = controller.read(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID da venda a ser alterada")));
				int id = f.getId_venda();
				if(f != null) {
					f = new Venda(txtCpfCliente.getText(), txtCpfFunc.getText(), Float.parseFloat(txtValor.getText()), f.getData(), 
							  controller.convertPagamentoId(pagamentoComboBox.getSelectedItem().toString())); 
					f.setId_venda(id);
					controller.update(f);
					JOptionPane.showMessageDialog(null,"Venda alterada corretamente!","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Venda não encontrada!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
				}
				controller.shutdown();
			}
		});
		contentPane.add(btnAlterar, "8, 12, center, default");
	}
	
	private List getPagamentoComboBox() {
		ServicoController controller = new ServicoController();
		List lista = controller.getPagamentoList();
		controller.shutdown();
		return lista;
	}
	
}