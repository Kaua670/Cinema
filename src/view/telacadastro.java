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
		setBounds(100, 100, 772, 573);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JTextPane titulo = new JTextPane();
		titulo.setText("Criar Conta");
		titulo.setFont(new Font("Verdana", Font.BOLD, 25));
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(127, 140, 300, 40);
		titulo.setOpaque(false);
		titulo.setBorder(null);
		contentPane.add(titulo);

		JTextPane usuarioTxt = new JTextPane();
		usuarioTxt.setText("Usuário");
		usuarioTxt.setForeground(Color.WHITE);
		usuarioTxt.setBounds(127, 191, 200, 25);
		usuarioTxt.setOpaque(false);
		usuarioTxt.setBorder(null);
		contentPane.add(usuarioTxt);

		textField = new JTextField();
		textField.setBounds(127, 210, 240, 25);
		contentPane.add(textField);

		JTextPane senhaTxt = new JTextPane();
		senhaTxt.setText("Senha");
		senhaTxt.setForeground(Color.WHITE);
		senhaTxt.setBounds(127, 249, 200, 25);
		senhaTxt.setOpaque(false);
		senhaTxt.setBorder(null);
		contentPane.add(senhaTxt);

		passwordField = new JPasswordField();
		passwordField.setBounds(127, 270, 240, 25);
		contentPane.add(passwordField);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setBounds(127, 320, 110, 25);
		contentPane.add(btnCadastrar);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(260, 320, 110, 25);
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\kaua62316556\\Documents\\Cinema\\images\\Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"));
		lblNewLabel.setBounds(316, 131, 430, 403);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\kaua62316556\\Documents\\Cinema\\images\\Nebulosas vibrantes no espaço profundo.png"));
		lblNewLabel_1.setBounds(-21, 0, 777, 534);
		contentPane.add(lblNewLabel_1);

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