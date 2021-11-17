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

import controller.ClienteController;
import model.Cliente;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClienteView extends JFrame {

	private JPanel contentPane;
	private JTextField txtCpf;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblEndereo;
	private JTextField txtEndereco;
	private JLabel lblTelefone;
	private JTextField txtTelefone;
	private JButton btnInserir;
	private JButton btnConsultar;
	private JButton btnAlterar;
	private JButton btnApagar;

	/**
	 * Create the frame.
	 */
	public ClienteView() {
		setResizable(false);
		setTitle("Gerenciar Clientes");
		setBounds(100, 100, 412, 211);
		contentPane = new JPanel();
		contentPane.setToolTipText(" Apenas números");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setVisible(true);
		
		contentPane.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
		txtCpf.setText(" Apenas números");
		txtCpf.setToolTipText(" Apenas números");
		txtCpf.setForeground(Color.LIGHT_GRAY);
		contentPane.add(txtCpf, "4, 2, 5, 1, fill, default");
		txtCpf.setColumns(10);
		txtCpf.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtCpf.setText("");
				txtCpf.setForeground(Color.BLACK);
			}
		});
		
		lblNome = new JLabel("Nome:");
		contentPane.add(lblNome, "2, 4, right, default");
		
		txtNome = new JTextField();
		contentPane.add(txtNome, "4, 4, 5, 1, fill, default");
		txtNome.setColumns(10);
		
		lblEndereo = new JLabel("Endereço");
		contentPane.add(lblEndereo, "2, 6, right, default");
		
		txtEndereco = new JTextField();
		contentPane.add(txtEndereco, "4, 6, 5, 1, fill, default");
		txtEndereco.setColumns(10);
		
		lblTelefone = new JLabel("Telefone");
		contentPane.add(lblTelefone, "2, 8, right, default");
		
		txtTelefone = new JTextField();
		contentPane.add(txtTelefone, "4, 8, 5, 1, fill, default");
		txtTelefone.setColumns(10);
		
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteController controller = new ClienteController();
				Cliente c = new Cliente(txtCpf.getText(), txtNome.getText(), txtEndereco.getText(), txtTelefone.getText());
				controller.create(c);
				if(controller.read(c.getCpf()) != null) {
					JOptionPane.showMessageDialog(null,"Cliente cadastrado corretamente!","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Erro no cadastro","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
				}
				controller.shutdown();
			}
		});
		contentPane.add(btnInserir, "2, 12, center, default");
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteController controller = new ClienteController();
				Cliente c = controller.read(txtCpf.getText());
				if(c != null) {
					txtNome.setText(c.getNome());
					txtEndereco.setText(c.getEndereco());
					txtTelefone.setText(c.getTelefone());
				}else {
					JOptionPane.showMessageDialog(null,"Cliente não encontrado!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
				}
				controller.shutdown();
			}
		});
		contentPane.add(btnConsultar, "4, 12, center, default");
		
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteController controller = new ClienteController();
				Cliente c = controller.read(txtCpf.getText());
				if(c != null) {
					txtNome.setText(c.getNome());
					txtEndereco.setText(c.getEndereco());
					txtTelefone.setText(c.getTelefone());
					controller.delete(c);
					JOptionPane.showMessageDialog(null,"Cliente excluído corretamente!","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Cliente não encontrado!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
				}
				controller.shutdown();
			}
		});
		contentPane.add(btnApagar, "6, 12, center, default");
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteController controller = new ClienteController();
				Cliente c = controller.read(txtCpf.getText());
				if(c != null) {
					c = new Cliente(txtCpf.getText(), txtNome.getText(), txtEndereco.getText(), txtTelefone.getText());
					controller.update(c);
					JOptionPane.showMessageDialog(null,"Cliente alterado corretamente!","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Cliente não encontrado!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
				}
				controller.shutdown();
			}
		});
		contentPane.add(btnAlterar, "8, 12, center, default");
	}

}
