package view;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;

public class telalogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				telalogin frame = new telalogin();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public telalogin() {
		setTitle("Login - Cine Lumi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel robo = new JLabel();
		robo.setIcon(new ImageIcon("images/Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"));
		robo.setBounds(1162, 130, 1598, 1342);
		contentPane.add(robo);

		JTextPane usuarioTxt = new JTextPane();
		usuarioTxt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		usuarioTxt.setText("Usuário");
		usuarioTxt.setForeground(Color.WHITE);
		usuarioTxt.setBounds(772, 332, 200, 25);
		usuarioTxt.setOpaque(false);
		usuarioTxt.setBorder(null);
		contentPane.add(usuarioTxt);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		textField.setBounds(772, 368, 326, 40);
		contentPane.add(textField);

		JTextPane senhaTxt = new JTextPane();
		senhaTxt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		senhaTxt.setText("Senha");
		senhaTxt.setForeground(Color.WHITE);
		senhaTxt.setBounds(772, 419, 200, 25);
		senhaTxt.setOpaque(false);
		senhaTxt.setBorder(null);
		contentPane.add(senhaTxt);

		passwordField = new JPasswordField();
		passwordField.setBounds(772, 455, 326, 40);
		contentPane.add(passwordField);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(772, 506, 155, 49);
		contentPane.add(btnEntrar);

		JButton btnCadastro = new JButton("Cadastro");
		btnCadastro.setBounds(937, 506, 161, 49);
		contentPane.add(btnCadastro);
		
		JLabel lblNewLabel_1 = new JLabel("\r\n");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\kaua62316556\\Documents\\Cinema\\images\\image-removebg-preview (1).png"));
		lblNewLabel_1.setBounds(651, 116, 1279, 205);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\kaua62316556\\Documents\\Cinema\\images\\galaxy_2560x1250.png"));
		lblNewLabel.setBounds(-55, -135, 2774, 1459);
		contentPane.add(lblNewLabel);

		btnEntrar.addActionListener(e -> {
			String usuario = textField.getText().trim();
			String senha = new String(passwordField.getPassword()).trim();

			if (usuario.isEmpty() || senha.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				return;
			}

			if (Arquivo.verificar(usuario, senha)) {
				JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");

				Ingresso telaIngresso = new Ingresso();
				telaIngresso.setVisible(true);

				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
			}
		});

		btnCadastro.addActionListener(e -> {
			new telacadastro().setVisible(true);
			dispose();
		});
	}
}
