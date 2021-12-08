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
import model.Servico;

public class ServicoView extends JFrame {

	private JPanel contentPane;
	private JTextField txtCpfCliente;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblTipo;
	private JLabel lblCpfFunc;
	private JTextField txtCpfFunc;
	private JButton btnInserir;
	private JButton btnConsultar;
	private JButton btnAlterar;
	private JButton btnApagar;
	private JLabel lblPagamento;
	private JComboBox pagamentoComboBox;
	private JComboBox tipoServicoComboBox;

	public ServicoView() {
		setResizable(false);
		setTitle("Gerenciar Serviços");
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
		
		lblNome = new JLabel("Nome do pet:");
		contentPane.add(lblNome, "2, 4, right, default");
		
		txtNome = new JTextField();
		contentPane.add(txtNome, "4, 4, 5, 1, fill, default");
		txtNome.setColumns(10);
		
		lblCpfFunc = new JLabel("CPF do Funcionário:");
		contentPane.add(lblCpfFunc, "2, 6, right, default");
		
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  ServicoController controller = new ServicoController(); 
				  Servico f = new Servico(txtCpfCliente.getText(), txtNome.getText(), txtCpfFunc.getText(), new Date(), 
						  controller.convertPagamentoId(pagamentoComboBox.getSelectedItem().toString()),
						  controller.convertTipoServicoId(tipoServicoComboBox.getSelectedItem().toString())); 
				  controller.create(f);
				  if(controller.read(f.getId_servico()) != null) {
				  JOptionPane.showMessageDialog(null,"Serviço cadastrado corretamente!"
				  ,"Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE); }else {
				  JOptionPane.showMessageDialog(null,"Erro no cadastro","Mensagem de Erro"
				  ,JOptionPane.ERROR_MESSAGE); } controller.shutdown();
				 			}
		});
		
		txtCpfFunc = new JTextField();
		contentPane.add(txtCpfFunc, "4, 6, 5, 1, fill, default");
		txtCpfFunc.setColumns(10);
		
		lblPagamento = new JLabel("Forma de pagamento:");
		contentPane.add(lblPagamento, "2, 8, right, default");
		
		pagamentoComboBox = new JComboBox(this.getPagamentoComboBox().toArray());
		contentPane.add(pagamentoComboBox, "4, 8, 5, 1, fill, default");
		
		lblTipo = new JLabel("Tipo de Serviço:");
		contentPane.add(lblTipo, "2, 10, right, default");
		
		tipoServicoComboBox = new JComboBox(this.getTipoServicoComboBox().toArray());
		contentPane.add(tipoServicoComboBox, "4, 10, 5, 1, fill, default");
		contentPane.add(btnInserir, "2, 12, fill, default");
		
		btnConsultar = new JButton("Listar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServicoTableView table = new ServicoTableView();
			}
		});
		contentPane.add(btnConsultar, "4, 12, fill, default");
		
		btnApagar = new JButton("Apagar");
		contentPane.add(btnApagar, "6, 12, center, default");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServicoController controller = new ServicoController(); 
				Servico f = controller.read(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do serviço a ser apagado")));
				if(f != null) {
					controller.delete(f);
					JOptionPane.showMessageDialog(null,"Serviço excluído corretamente!","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Serviço não encontrado!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
				}
				controller.shutdown();
			}
		});
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ServicoController controller = new ServicoController(); 
				Servico f = controller.read(Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do serviço a ser alterado")));
				int id = f.getId_servico();
				if(f != null) {
					f = new Servico(txtCpfCliente.getText(), txtNome.getText(), txtCpfFunc.getText(), f.getData(), 
							  controller.convertPagamentoId(pagamentoComboBox.getSelectedItem().toString()),
							  controller.convertTipoServicoId(tipoServicoComboBox.getSelectedItem().toString()));
					f.setId_servico(id);
					controller.update(f);
					JOptionPane.showMessageDialog(null,"Serviço alterado corretamente!","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Serviço não encontrado!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
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
	
	private List getTipoServicoComboBox() {
		ServicoController controller = new ServicoController();
		List lista = controller.getTipoServicoList();
		controller.shutdown();
		return lista;
	}
}
