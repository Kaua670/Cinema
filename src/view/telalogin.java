package view;

import java.awt.EventQueue;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.UsuarioDAO;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class telalogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				dao.Banco.init();
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
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel robo = new JLabel();
		robo.setBounds(1162, 130, 1598, 1342);
		robo.setIcon(new ImageIcon("images/Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"));
		contentPane.add(robo);

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

		// 🆕 BOTÃO CADASTRO
		JButton btnCadastro = new JButton("Cadastro");
		btnCadastro.setBounds(937, 506, 161, 49);
		contentPane.add(btnCadastro);

		// 📜 HISTÓRICO
		JButton btnHistorico = new JButton("Histórico");
		btnHistorico.setBounds(772, 565, 155, 49);
		contentPane.add(btnHistorico);

		// 🔥 ESQUECI SENHA
		JButton btnEsqueciASenha = new JButton("Esqueci a senha");
		btnEsqueciASenha.setBounds(937, 565, 161, 49);
		contentPane.add(btnEsqueciASenha);

		JLabel titulo = new JLabel();
		titulo.setBounds(655, 152, 1279, 205);
		titulo.setIcon(new ImageIcon("images/image-removebg-preview (1).png"));
		contentPane.add(titulo);

		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(772, 326, 128, 42);
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblUsuario.setForeground(Color.WHITE);
		contentPane.add(lblUsuario);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBounds(772, 413, 128, 42);
		lblSenha.setForeground(Color.WHITE);
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblSenha);

		JLabel fundo = new JLabel();
		fundo.setBounds(-18, 0, 2560, 1250);
		fundo.setIcon(new ImageIcon("images/galaxy_2560x1250.png"));
		contentPane.add(fundo);

		// 🔐 LOGIN
		btnEntrar.addActionListener(e -> {
			String usuario = textField.getText().trim();
			String senha = new String(passwordField.getPassword()).trim();

			if (usuario.isEmpty() || senha.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				return;
			}

			if (dao.UsuarioDAO.verificar(usuario, senha)) {
				JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");

				Ingresso telaIngresso = new Ingresso();
				telaIngresso.setVisible(true);

				dispose();
				
			
			} // 🔥 ADMIN FIXO
			if (usuario.equals("adm01") && senha.equals("999999")) {

			    JOptionPane.showMessageDialog(null, "Login como ADMIN!");

			    new Administrador().setVisible(true);
			    dispose();

			} 
			
			if (UsuarioDAO.Administrador(usuario, senha)) {
			    new Administrador().setVisible(true);
			}
			
			else if (dao.UsuarioDAO.verificar(usuario, senha)) {

			    JOptionPane.showMessageDialog(null, "Login normal!");

			    new Ingresso().setVisible(true);
			    dispose();

			} 		
			else {
				JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
			}
		});

		// 🆕 IR PARA CADASTRO
		btnCadastro.addActionListener(e -> {
			new telacadastro().setVisible(true);
			dispose();
		});

		// 📜 HISTÓRICO
		btnHistorico.addActionListener(e -> {
			String historico = dao.UsuarioDAO.listarUsuarios();

			if (historico.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Nenhum usuário cadastrado.");
			} else {
				JOptionPane.showMessageDialog(this, historico);
			}
		});

		// 🔥 ESQUECI SENHA (CORRIGIDO)
		btnEsqueciASenha.addActionListener(e -> {
			String usuario = textField.getText().trim();

			if (usuario.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite o usuário!");
				return;
			}

			// 🔎 Verifica se existe
			if (!dao.UsuarioDAO.usuarioExiste(usuario)) {
				JOptionPane.showMessageDialog(null, "Usuário não encontrado!");
				return;
			}

			// 🔥 Abre recuperação
			new telacadastro(usuario).setVisible(true);
			dispose();
		});
	}
}