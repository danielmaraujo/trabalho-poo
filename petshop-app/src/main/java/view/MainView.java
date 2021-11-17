package view;

import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 562);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 283, 199);
		contentPane.add(panel);
		
		JMenuItem clientesMenuItem = new JMenuItem("Gerenciar Clientes");
		clientesMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ClienteView frameCliente = new ClienteView();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				clientesMenuItem.setBackground(UIManager.getColor("Button.focus"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				clientesMenuItem.setBackground(UIManager.getColor("Button.background"));
			}
		});
		clientesMenuItem.setFont(new Font("Dialog", Font.BOLD, 12));
		clientesMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		SpringLayout sl_panel = new SpringLayout();
		sl_panel.putConstraint(SpringLayout.NORTH, clientesMenuItem, 50, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, clientesMenuItem, 0, SpringLayout.WEST, panel);
		panel.setLayout(sl_panel);
		
		JTextPane txtpnMenu = new JTextPane();
		txtpnMenu.setEditable(false);
		sl_panel.putConstraint(SpringLayout.NORTH, txtpnMenu, 6, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, txtpnMenu, 107, SpringLayout.WEST, panel);
		txtpnMenu.setFont(new Font("Dialog", Font.BOLD, 18));
		txtpnMenu.setBackground(UIManager.getColor("menu"));
		txtpnMenu.setText("MENU");
		panel.add(txtpnMenu);
		
		JSeparator separator = new JSeparator();
		sl_panel.putConstraint(SpringLayout.EAST, clientesMenuItem, 0, SpringLayout.EAST, separator);
		sl_panel.putConstraint(SpringLayout.NORTH, separator, 41, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, separator, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, separator, 282, SpringLayout.WEST, panel);
		panel.add(separator);
		panel.add(clientesMenuItem);
		
		JMenuItem mntmGerenciarPets = new JMenuItem("Gerenciar Pets");
		sl_panel.putConstraint(SpringLayout.NORTH, mntmGerenciarPets, 76, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, mntmGerenciarPets, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, mntmGerenciarPets, 0, SpringLayout.EAST, separator);
		panel.add(mntmGerenciarPets);
		
		JMenuItem mntmGerenciarVenda = new JMenuItem("Gerenciar Venda");
		sl_panel.putConstraint(SpringLayout.NORTH, mntmGerenciarVenda, 102, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, mntmGerenciarVenda, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, mntmGerenciarVenda, 0, SpringLayout.EAST, separator);
		panel.add(mntmGerenciarVenda);
		
		JMenuItem mntmGerenciarServio = new JMenuItem("Gerenciar Serviço");
		sl_panel.putConstraint(SpringLayout.NORTH, mntmGerenciarServio, 128, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, mntmGerenciarServio, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, mntmGerenciarServio, 0, SpringLayout.EAST, separator);
		panel.add(mntmGerenciarServio);
		
		JMenuItem funcionariosMenuItem = new JMenuItem("Gerenciar Funcionários");
		funcionariosMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				FuncionarioView frameFuncionario = new FuncionarioView();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				funcionariosMenuItem.setBackground(UIManager.getColor("Button.focus"));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				funcionariosMenuItem.setBackground(UIManager.getColor("Button.background"));
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, funcionariosMenuItem, 154, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, funcionariosMenuItem, 0, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, funcionariosMenuItem, 0, SpringLayout.EAST, separator);
		funcionariosMenuItem.setHorizontalAlignment(SwingConstants.LEFT);
		panel.add(funcionariosMenuItem);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("src/main/resources/pets.png"));
		label.setBounds(0, 57, 634, 533);
		contentPane.add(label);
	}
}
