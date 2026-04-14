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

public class telalogin extends JFrame {

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 772, 573);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// 🌌 FUNDO
		JLabel fundo = new JLabel();
		fundo.setIcon(new ImageIcon("images/Nebulosas vibrantes no espaço profundo.png"));
		fundo.setBounds(-22, 0, 778, 534);
		contentPane.add(fundo);

		// 🤖 IMAGEM
		JLabel robo = new JLabel();
		robo.setIcon(new ImageIcon("C:\\Users\\kaua62316556\\Documents\\Cinema\\images\\Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"));
		robo.setBounds(305, 182, 416, 389);
		contentPane.add(robo);

		// 🎬 TÍTULO
		JTextPane titulo = new JTextPane();
		titulo.setText("CINE LUMI");
		titulo.setFont(new Font("Verdana", Font.BOLD, 25));
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(280, 100, 250, 40);
		titulo.setOpaque(false);
		titulo.setBorder(null);
		contentPane.add(titulo);

		// 👤 USUÁRIO
		JTextPane usuarioTxt = new JTextPane();
		usuarioTxt.setText("Usuário");
		usuarioTxt.setForeground(Color.WHITE);
		usuarioTxt.setBounds(127, 212, 200, 25);
		usuarioTxt.setOpaque(false);
		usuarioTxt.setBorder(null);
		contentPane.add(usuarioTxt);

		textField = new JTextField();
		textField.setBounds(127, 241, 240, 25);
		contentPane.add(textField);

		// 🔒 SENHA
		JTextPane senhaTxt = new JTextPane();
		senhaTxt.setText("Senha");
		senhaTxt.setForeground(Color.WHITE);
		senhaTxt.setBounds(127, 272, 200, 25);
		senhaTxt.setOpaque(false);
		senhaTxt.setBorder(null);
		contentPane.add(senhaTxt);

		passwordField = new JPasswordField();
		passwordField.setBounds(127, 300, 240, 25);
		contentPane.add(passwordField);

		// 🔘 BOTÕES
		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(127, 340, 100, 25);
		contentPane.add(btnEntrar);

		JButton btnCadastro = new JButton("Cadastro");
		btnCadastro.setBounds(260, 340, 100, 25);
		contentPane.add(btnCadastro);

		// 🔐 LOGIN + ABRIR SESSÃO
		btnEntrar.addActionListener(e -> {
			String usuario = textField.getText();
			String senha = new String(passwordField.getPassword());

			if (usuario.isEmpty() || senha.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				return;
			}

			if (Arquivo.verificar(usuario, senha)) {
				JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
				new Arquivo().setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Usuário ou senha incorretos!");
			}
		});

		// 🔁 IR PARA CADASTRO
		btnCadastro.addActionListener(e -> {
			new telacadastro().setVisible(true);
			dispose();
		});

		// 🔧 GARANTIR FUNDO ATRÁS DE TUDO
		contentPane.setComponentZOrder(fundo, contentPane.getComponentCount() - 1);
	}
}
