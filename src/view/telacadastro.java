package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class telacadastro extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public telacadastro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JTextPane usuarioTxt = new JTextPane();
		usuarioTxt.setFont(new Font("Tahoma", Font.PLAIN, 22));
		usuarioTxt.setText("Usuário");
		usuarioTxt.setForeground(Color.WHITE);
		usuarioTxt.setBounds(772, 332, 200, 25);
		usuarioTxt.setOpaque(false);
		usuarioTxt.setBorder(null);
		contentPane.add(usuarioTxt);

		textField = new JTextField();
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

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(772, 506, 155, 49);
		contentPane.add(btnCadastrar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(937, 506, 161, 49);
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images/Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"));
		lblNewLabel.setBounds(1162, 130, 1598, 1342);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\kaua62316556\\Documents\\Cinema\\images\\image-removebg-preview (2).png"));
		lblNewLabel_1.setBounds(650, 191, 590, 155);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\kaua62316556\\Documents\\Cinema\\images\\galaxy_2560x1250.png"));
		lblNewLabel_2.setBounds(-19, 0, 2560, 1250);
		contentPane.add(lblNewLabel_2);

		// 💾 SALVAR USUÁRIO
		btnCadastrar.addActionListener(e -> {
			String usuario = textField.getText();
			String senha = new String(passwordField.getPassword());

			if (usuario.isEmpty() || senha.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				return;
			}

			Arquivo.salvar(usuario, senha);
			JOptionPane.showMessageDialog(null, "Cadastro realizado!");

			new telalogin().setVisible(true);
			dispose();
		});

		// 🔁 VOLTAR
		btnVoltar.addActionListener(e -> {
			new telalogin().setVisible(true);
			dispose();
		});
	}
}