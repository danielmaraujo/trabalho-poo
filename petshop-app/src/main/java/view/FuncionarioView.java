package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.FuncionarioController;
import model.Funcionario;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JComboBox;


public class FuncionarioView extends JFrame {

	private JPanel contentPane;
	private JTextField txtCpf;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblSalario;
	private JTextField txtSalario;
	private JLabel lblTelefone;
	private JTextField txtTelefone;
	private JButton btnInserir;
	private JButton btnConsultar;
	private JButton btnAlterar;
	private JButton btnApagar;
	private JLabel lblFuncao;
	private JComboBox funcaoComboBox;

	/**
	 * Create the frame.
	 */
	public FuncionarioView() {
		setResizable(false);
		setTitle("Gerenciar Funcionarios");
		setBounds(100, 100, 420, 225);
		contentPane = new JPanel();
		contentPane.setToolTipText(" Apenas números");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(45dlu;default)"),
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
		
		JLabel lblCpf = new JLabel("CPF:");
		contentPane.add(lblCpf, "2, 2, right, default");
		
		txtCpf = new JTextField();
		txtCpf.setToolTipText(" Apenas números");
		contentPane.add(txtCpf, "4, 2, 5, 1, fill, default");
		txtCpf.setColumns(10);
		
		lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "2, 4, right, default");
		
		txtNome = new JTextField();
		contentPane.add(txtNome, "4, 4, 5, 1, fill, default");
		txtNome.setColumns(10);
		
		lblTelefone = new JLabel("Telefone");
		contentPane.add(lblTelefone, "2, 6, right, default");
		
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  FuncionarioController controller = new FuncionarioController(); 
				  Funcionario f = new Funcionario(txtCpf.getText(), txtNome.getText(), txtTelefone.getText(), Float.parseFloat(txtSalario.getText()), controller.convertFuncaoId(funcaoComboBox.getSelectedItem().toString())); 
				  controller.create(f);
				  if(controller.read(f.getCpf()) != null) {
				  JOptionPane.showMessageDialog(null,"Funcionário cadastrado corretamente!"
				  ,"Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE); }else {
				  JOptionPane.showMessageDialog(null,"Erro no cadastro","Mensagem de Erro"
				  ,JOptionPane.ERROR_MESSAGE); } controller.shutdown();
				 			}
		});
		
		txtTelefone = new JTextField();
		contentPane.add(txtTelefone, "4, 6, 5, 1, fill, default");
		txtTelefone.setColumns(10);
		
		lblFuncao = new JLabel("Função:");
		contentPane.add(lblFuncao, "2, 8, right, default");
		
		funcaoComboBox = new JComboBox(this.getFuncaoComboBox().toArray());
		contentPane.add(funcaoComboBox, "4, 8, 5, 1, fill, default");
		
		lblSalario = new JLabel("Salário:");
		contentPane.add(lblSalario, "2, 10, right, default");
		
		txtSalario = new JTextField();
		contentPane.add(txtSalario, "4, 10, 5, 1, fill, default");
		txtSalario.setColumns(10);
		contentPane.add(btnInserir, "2, 12, center, default");
		txtSalario.setToolTipText(" Apenas números");
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioController controller = new FuncionarioController();
				Funcionario f = controller.read(txtCpf.getText());
				if(f != null) {
					txtNome.setText(f.getNome());
					txtSalario.setText(Float.toString(f.getSalario()));
					txtTelefone.setText(f.getTelefone());
					funcaoComboBox.setSelectedIndex((f.getFuncao() - 1));
				}else {
					JOptionPane.showMessageDialog(null,"Funcionário não encontrado!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
				}
				controller.shutdown();
			}
		});
		contentPane.add(btnConsultar, "4, 12, center, default");
		
		btnApagar = new JButton("Apagar");
		contentPane.add(btnApagar, "6, 12, center, default");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioController controller = new FuncionarioController();
				Funcionario f = controller.read(txtCpf.getText());
				if(f != null) {
					txtNome.setText(f.getNome());
					txtTelefone.setText(f.getTelefone());
					txtSalario.setText(Float.toString(f.getSalario()));
					funcaoComboBox.setSelectedIndex((f.getFuncao() - 1));
					controller.delete(f);
					JOptionPane.showMessageDialog(null,"Funcionário excluído corretamente!","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Funcionário não encontrado!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
				}
				controller.shutdown();
			}
		});
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FuncionarioController controller = new FuncionarioController();
				Funcionario f = controller.read(txtCpf.getText());
				if(f != null) {
					f = new Funcionario(txtCpf.getText(), txtNome.getText(), txtTelefone.getText(), Float.parseFloat(txtSalario.getText()), controller.convertFuncaoId(funcaoComboBox.getSelectedItem().toString()));
					controller.update(f);
					JOptionPane.showMessageDialog(null,"Funcionário alterado corretamente!","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Funcionário não encontrado!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
				}
				controller.shutdown();
			}
		});
		contentPane.add(btnAlterar, "8, 12, center, default");
	}
	
	private List getFuncaoComboBox() {
		FuncionarioController controller = new FuncionarioController();
		List lista = controller.getFuncaoList();
		controller.shutdown();
		return lista;
	}
}
