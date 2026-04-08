package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JComboBox;
import javax.swing.JLayeredPane;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;

public class telalogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table_1;
	private JPasswordField senha;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telalogin frame = new telalogin();
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
	public telalogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 573);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton entrarbutton = new JButton("ENTRAR");
		entrarbutton.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		entrarbutton.setForeground(new Color(240, 255, 255));
		entrarbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		entrarbutton.setBackground(new Color(255, 127, 80));
		entrarbutton.setBounds(38, 386, 162, 27);
		contentPane.add(entrarbutton);
		
		JButton cadastrobutton = new JButton("CADASTRO");
		cadastrobutton.setForeground(new Color(240, 255, 255));
		cadastrobutton.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 20));
		cadastrobutton.setBackground(new Color(0, 0, 255));
		cadastrobutton.setBounds(272, 88, 226, 70);
		contentPane.add(cadastrobutton);
		
		JLabel txttitulo = new JLabel("CINE LUMI");
		txttitulo.setForeground(new Color(240, 255, 255));
		txttitulo.setBounds(319, -13, 253, 106);
		txttitulo.setFont(new Font("Rockwell Condensed", Font.BOLD, 30));
		txttitulo.setBackground(new Color(0, 64, 128));
		contentPane.add(txttitulo);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("images/image-removebg-preview.png"));
		lblNewLabel.setBounds(423, 198, 349, 352);
		contentPane.add(lblNewLabel);
		
		table_1 = new JTable();
		table_1.setBackground(new Color(0, 0, 139));
		table_1.setBounds(258, 74, 253, 97);
		contentPane.add(table_1);
		
		JEditorPane editorUsuario = new JEditorPane();
		editorUsuario.setBounds(38, 284, 245, 15);
		contentPane.add(editorUsuario);
		
		senha = new JPasswordField();
		senha.setBounds(38, 336, 245, 15);
		contentPane.add(senha);
		
		JTextPane txtUsuario = new JTextPane();
		txtUsuario.setForeground(new Color(255, 255, 255));
		txtUsuario.setBackground(new Color(0, 0, 0));
		txtUsuario.setText("Usuário");
		txtUsuario.setBounds(38, 253, 62, 20);
		contentPane.add(txtUsuario);
		
		JTextPane txtSenha = new JTextPane();
		txtSenha.setText("Senha");
		txtSenha.setForeground(Color.WHITE);
		txtSenha.setBackground(Color.BLACK);
		txtSenha.setBounds(38, 305, 62, 20);
		contentPane.add(txtSenha);
		
		table = new JTable();
		table.setBackground(new Color(0, 0, 0));
		table.setBounds(10, 11, 737, 514);
		contentPane.add(table);

	}
}