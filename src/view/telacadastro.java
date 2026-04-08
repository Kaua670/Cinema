package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTable;

public class telacadastro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					telacadastro frame = new telacadastro();
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
	public telacadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 573);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("images/image-removebg-preview.png"));
		lblNewLabel.setBounds(416, 176, 340, 358);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(213, 190, 283, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(213, 404, 283, 20);
		contentPane.add(textField_1);
		
		JTextPane txtpnCrieUmUsurio = new JTextPane();
		txtpnCrieUmUsurio.setBackground(new Color(0, 0, 0));
		txtpnCrieUmUsurio.setForeground(new Color(255, 255, 255));
		txtpnCrieUmUsurio.setText("Usuário\r\n");
		txtpnCrieUmUsurio.setBounds(309, 159, 120, 20);
		contentPane.add(txtpnCrieUmUsurio);
		
		JTextPane txtpnCrieUmaSenha = new JTextPane();
		txtpnCrieUmaSenha.setBackground(new Color(0, 0, 0));
		txtpnCrieUmaSenha.setForeground(new Color(255, 255, 255));
		txtpnCrieUmaSenha.setText("Criar uma Senha");
		txtpnCrieUmaSenha.setBounds(309, 373, 120, 20);
		contentPane.add(txtpnCrieUmaSenha);
		
		table = new JTable();
		table.setBackground(new Color(0, 0, 0));
		table.setBounds(10, 11, 736, 512);
		contentPane.add(table);

	}

}
