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

import controller.PetController;
import model.Pet;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PetView extends JFrame {

	private JPanel contentPane;
	private JTextField txtCpf;
	private JLabel lblNome;
	private JTextField txtNome;
	private JLabel lblEspecie;
	private JTextField txtEspecie;
	private JLabel lblRaca;
	private JTextField txtRaca;
	private JButton btnInserir;
	private JButton btnConsultar;
	private JButton btnAlterar;
	private JButton btnApagar;
	private JLabel lblDatanasc;
	private JTextField txtDatanasc;

	/**
	 * Create the frame.
	 */
	public PetView() {
		setResizable(false);
		setTitle("Gerenciar Pets");
		setBounds(100, 100, 439, 215);
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
				ColumnSpec.decode("default:grow"),
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
		
		JLabel lblCpf = new JLabel("CPF do Cliente:");
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
		
		lblNome = new JLabel("Nome do pet:");
		contentPane.add(lblNome, "2, 4, right, default");
		
		txtNome = new JTextField();
		contentPane.add(txtNome, "4, 4, 5, 1, fill, default");
		txtNome.setColumns(10);
		
		lblEspecie = new JLabel("Espécie");
		contentPane.add(lblEspecie, "2, 6, right, default");
		
		txtEspecie = new JTextField();
		contentPane.add(txtEspecie, "4, 6, 5, 1, fill, default");
		txtEspecie.setColumns(10);
		
		lblRaca = new JLabel("Raça:");
		contentPane.add(lblRaca, "2, 8, right, default");
		
		txtRaca = new JTextField();
		contentPane.add(txtRaca, "4, 8, 5, 1, fill, default");
		txtRaca.setColumns(10);
		
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PetController controller = new PetController();
				Pet p;
				try {
					p = new Pet(txtNome.getText(), txtEspecie.getText(), txtRaca.getText(), dateFormatter.parse(txtDatanasc.getText()), txtCpf.getText());
					controller.create(p);
					if(controller.read(p.getCliente_cpf(), p.getNome()) != null) {
						JOptionPane.showMessageDialog(null,"Pet cadastrado corretamente!","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null,"Erro no cadastro","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
					}
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null,"Erro na data","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
					e1.printStackTrace();
				}
				controller.shutdown();
			}
		});
		
		lblDatanasc = new JLabel("Data de nasc:");
		contentPane.add(lblDatanasc, "2, 10, right, default");
		
		txtDatanasc = new JTextField();
		txtDatanasc.setToolTipText("dd/mm/aaaa");
		txtDatanasc.setText("dd/mm/aaaa");
		txtDatanasc.setColumns(10);
		contentPane.add(txtDatanasc, "4, 10, 5, 1, fill, default");
		contentPane.add(btnInserir, "2, 12, fill, default");
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PetController controller = new PetController();
				Pet p = controller.read(txtCpf.getText(), txtNome.getText());
				if(p != null) {
					txtEspecie.setText(p.getEspecie());
					txtRaca.setText(p.getRaca());
					txtDatanasc.setText(dateFormatter.format(p.getData_nascimento()));
				}else {
					JOptionPane.showMessageDialog(null,"Pet não encontrado!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
				}
				controller.shutdown();
			}
		});
		contentPane.add(btnConsultar, "4, 12, center, default");
		
		btnApagar = new JButton("Apagar");
		btnApagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PetController controller = new PetController();
				Pet p = controller.read(txtCpf.getText(), txtNome.getText());
				if(p != null) {
					txtEspecie.setText(p.getEspecie());
					txtRaca.setText(p.getRaca());
					txtDatanasc.setText(dateFormatter.format(p.getData_nascimento()));
					controller.delete(p);
					JOptionPane.showMessageDialog(null,"Pet excluído corretamente!","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Pet não encontrado!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
				}
				controller.shutdown();
			}
		});
		contentPane.add(btnApagar, "6, 12, center, default");
		
		btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PetController controller = new PetController();
				Pet p = controller.read(txtCpf.getText(), txtNome.getText());
				if(p != null) {
					try {
						p.setNome(txtNome.getText());
						p.setEspecie(txtEspecie.getText());
						p.setRaca(txtRaca.getText());
						p.setData_nascimento(dateFormatter.parse(txtDatanasc.getText()));
						p.setCliente_cpf(txtCpf.getText());
						controller.update(p);
					} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null,"Erro na data","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null,"Pet alterado corretamente!","Mensagem de Informação",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null,"Pet não encontrado!","Mensagem de Erro",JOptionPane.ERROR_MESSAGE);
				}
				controller.shutdown();
			}
		});
		contentPane.add(btnAlterar, "8, 12, center, default");
	}

}
