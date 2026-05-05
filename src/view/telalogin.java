package view;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

import dao.UsuarioDAO;
import dao.Banco;

public class telalogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Banco.init(); // 🔥 inicia banco
				telalogin frame = new telalogin();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public telalogin() {

		// 🔥 garante banco/tabelas
		Banco.init();

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Login - Cine Lumi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);

		JLabel fundo = new JLabel(new ImageIcon("images/galaxy_2560x1250.png"));
		fundo.setLayout(null);
		setContentPane(fundo);

		contentPane = fundo;

		// 👤 USUÁRIO
		textField = new JTextField();
		textField.setBounds(772, 368, 326, 40);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(textField);

		// 🔒 SENHA
		passwordField = new JPasswordField();
		passwordField.setBounds(772, 455, 326, 40);
		contentPane.add(passwordField);

		// 🔐 BOTÃO ENTRAR
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(772, 506, 155, 49);
		contentPane.add(btnEntrar);

		// 🆕 CADASTRO
		JButton btnCadastro = new JButton("Cadastro");
		btnCadastro.setBounds(937, 506, 161, 49);
		contentPane.add(btnCadastro);

		// 🔥 ESQUECI SENHA
		JButton btnEsqueciASenha = new JButton("Esqueci a senha");
		btnEsqueciASenha.setBounds(772, 565, 326, 49);
		contentPane.add(btnEsqueciASenha);

		// 🤖 IMAGEM
		JLabel robo = new JLabel();
		robo.setBounds(1162, 130, 1598, 1342);
		robo.setIcon(new ImageIcon("images/Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"));
		contentPane.add(robo);

		// 🏷️ TÍTULO
		JLabel titulo = new JLabel();
		titulo.setBounds(655, 152, 1279, 205);
		titulo.setIcon(new ImageIcon("images/image-removebg-preview (1).png"));
		contentPane.add(titulo);

		// LABEL USUARIO
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(772, 326, 128, 42);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUsuario.setForeground(Color.WHITE);
		contentPane.add(lblUsuario);

		// LABEL SENHA
		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(772, 413, 128, 42);
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblSenha);

		// 🔥 LOGIN CORRIGIDO
		btnEntrar.addActionListener(e -> {

			String usuario = textField.getText().trim();
			String senha = new String(passwordField.getPassword()).trim();

			if (usuario.isEmpty() || senha.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				return;
			}

			// ✅ ADMIN FIXO PRIMEIRO (CORREÇÃO)
			if (usuario.equals("adm01") && senha.equals("141516")) {
				JOptionPane.showMessageDialog(null, "Login como ADMIN!");
				new Administrador().setVisible(true);
				dispose();
				return;
			}

			// 🔍 VERIFICA NO BANCO
			if (!UsuarioDAO.usuarioExiste(usuario)) {
				JOptionPane.showMessageDialog(null, "Usuário não existe!");
				return;
			}

			// 🔐 ADMIN DO BANCO
			if (UsuarioDAO.Administrador(usuario, senha)) {
				JOptionPane.showMessageDialog(null, "Login como ADMIN!");
				new Administrador().setVisible(true);
				dispose();
			}

			// 👤 USUÁRIO NORMAL
			else if (UsuarioDAO.verificar(usuario, senha)) {
				JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
				new Ingresso().setVisible(true);
				dispose();
			}

			// ❌ SENHA ERRADA
			else {
				JOptionPane.showMessageDialog(null, "Senha incorreta!");
			}
		});

		// 🆕 CADASTRO
		btnCadastro.addActionListener(e -> {
			new telacadastro().setVisible(true);
			dispose();
		});

		// 🔥 RECUPERAÇÃO
		btnEsqueciASenha.addActionListener(e -> {

			String usuario = textField.getText().trim();

			if (usuario.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite o usuário!");
				return;
			}

			if (!UsuarioDAO.usuarioExiste(usuario)) {
				JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
				return;
			}

			new telacadastro(usuario).setVisible(true);
			dispose();
		});
	}
}