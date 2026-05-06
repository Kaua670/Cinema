package view;

import java.awt.EventQueue;
import javax.swing.*;

import Controle.ControlePreco;
import Controle.ControlerAssento;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class Administrador extends JFrame {

	private JPanel contentPane;
	private JTextField campo2D;
	private JTextField campo3D;
	private JTextField campoExcluir;

	// 🎬 CAMPOS FILME
	private JTextField campoFilme;
	private JTextField campoClass;
	private JTextField campoExcluirFilme;
	private String caminhoImagem = "";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new Administrador().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Administrador() {
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Painel Admin");

		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);

		// ================= TÍTULO =================
		JLabel titulo = new JLabel("PAINEL ADMINISTRADOR");
		titulo.setFont(new Font("Verdana", Font.BOLD, 28));
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(650, 30, 500, 50);
		contentPane.add(titulo);

		// ================= 🎬 CADASTRAR FILME =================
		JLabel tituloFilme = new JLabel("CADASTRAR FILME");
		tituloFilme.setBounds(100, 80, 300, 30);
		tituloFilme.setForeground(Color.WHITE);
		tituloFilme.setFont(new Font("Verdana", Font.BOLD, 18));
		contentPane.add(tituloFilme);

		JLabel lblFilme = new JLabel("Nome do Filme:");
		lblFilme.setBounds(100, 120, 200, 30);
		lblFilme.setForeground(Color.WHITE);
		contentPane.add(lblFilme);

		campoFilme = new JTextField();
		campoFilme.setBounds(100, 150, 250, 40);
		contentPane.add(campoFilme);

		JLabel lblClass = new JLabel("Classificação:");
		lblClass.setBounds(100, 200, 200, 30);
		lblClass.setForeground(Color.WHITE);
		contentPane.add(lblClass);

		campoClass = new JTextField();
		campoClass.setBounds(100, 230, 250, 40);
		contentPane.add(campoClass);

		JButton btnImagem = new JButton("Escolher Imagem");
		btnImagem.setBounds(100, 290, 250, 40);
		contentPane.add(btnImagem);

		btnImagem.addActionListener(e -> {
			JFileChooser chooser = new JFileChooser();
			int res = chooser.showOpenDialog(null);

			if (res == JFileChooser.APPROVE_OPTION) {
				caminhoImagem = chooser.getSelectedFile().getAbsolutePath();
				JOptionPane.showMessageDialog(null, "Imagem selecionada!");
			}
		});

		JButton btnCadastrarFilme = new JButton("Cadastrar Filme");
		btnCadastrarFilme.setBounds(100, 350, 250, 50);

		btnCadastrarFilme.addActionListener(e -> {

			String nome = campoFilme.getText().trim();
			String classificacao = campoClass.getText().trim();

			if (nome.isEmpty() || classificacao.isEmpty() || caminhoImagem.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				return;
			}

			dao.FilmeDAO.adicionarFilme(nome, classificacao, caminhoImagem);

			JOptionPane.showMessageDialog(null, "Filme cadastrado!");

			campoFilme.setText("");
			campoClass.setText("");
			caminhoImagem = "";
		});

		contentPane.add(btnCadastrarFilme);

		// ================= 🗑️ EXCLUIR FILME =================
		JLabel lblExcluirFilme = new JLabel("Excluir filme:");
		lblExcluirFilme.setBounds(100, 420, 200, 30);
		lblExcluirFilme.setForeground(Color.WHITE);
		contentPane.add(lblExcluirFilme);

		campoExcluirFilme = new JTextField();
		campoExcluirFilme.setBounds(100, 450, 250, 40);
		contentPane.add(campoExcluirFilme);

		JButton btnExcluirFilme = new JButton("Excluir Filme");
		btnExcluirFilme.setBounds(100, 500, 250, 50);
		contentPane.add(btnExcluirFilme);

		btnExcluirFilme.addActionListener(e -> {

			String nome = campoExcluirFilme.getText().trim();

			if (nome.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Digite o nome do filme!");
				return;
			}

			if (dao.FilmeDAO.excluirFilme(nome)) {
				JOptionPane.showMessageDialog(null, "Filme excluído com sucesso!");
				campoExcluirFilme.setText("");
			} else {
				JOptionPane.showMessageDialog(null, "Filme não encontrado!");
			}
		});

		// ================= PREÇOS =================
		JLabel lbl1 = new JLabel("PREÇO 2D:");
		lbl1.setBounds(650, 120, 200, 30);
		lbl1.setForeground(Color.WHITE);
		contentPane.add(lbl1);

		campo2D = new JTextField(String.valueOf(ControlePreco.preco2D));
		campo2D.setBounds(650, 150, 200, 40);
		contentPane.add(campo2D);

		JLabel lbl2 = new JLabel("PREÇO 3D:");
		lbl2.setBounds(900, 120, 200, 30);
		lbl2.setForeground(Color.WHITE);
		contentPane.add(lbl2);

		campo3D = new JTextField(String.valueOf(ControlePreco.preco3D));
		campo3D.setBounds(900, 150, 200, 40);
		contentPane.add(campo3D);

		// ================= ASSENTOS =================
		Set<String> assentosTemp = new HashSet<>(ControlerAssento.assentosBloqueados);

		JPanel painel = new JPanel(new GridLayout(4, 5, 10, 10));
		painel.setBounds(500, 250, 900, 250);
		painel.setOpaque(false);
		contentPane.add(painel);

		String[] assentos = {
			"A1","A2","A3","A4","A5",
			"B1","B2","B3","B4","B5",
			"C1","C2","C3","C4","C5",
			"D1","D2","D3","D4","D5"
		};

		for (String a : assentos) {

			JButton btn = new JButton(a);
			btn.setForeground(Color.WHITE);
			btn.setBackground(assentosTemp.contains(a) ? Color.RED : Color.GRAY);

			btn.addActionListener(e -> {
				if (assentosTemp.contains(a)) {
					assentosTemp.remove(a);
					btn.setBackground(Color.GRAY);
				} else {
					assentosTemp.add(a);
					btn.setBackground(Color.RED);
				}
			});

			painel.add(btn);
		}

		// ================= SALVAR =================
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(600, 520, 180, 50);

		btnSalvar.addActionListener(e -> {
			try {
				ControlePreco.preco2D = Double.parseDouble(campo2D.getText());
				ControlePreco.preco3D = Double.parseDouble(campo3D.getText());

				ControlerAssento.assentosBloqueados.clear();
				ControlerAssento.assentosBloqueados.addAll(assentosTemp);

				JOptionPane.showMessageDialog(null, "Alterações salvas!");
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "Erro nos valores!");
			}
		});

		contentPane.add(btnSalvar);

		// ================= VOLTAR =================
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(1000, 520, 180, 50);

		btnVoltar.addActionListener(e -> {
			new telalogin().setVisible(true);
			dispose();
		});

		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("images/galaxy_2560x1250.png"));
		lblNewLabel.setBounds(-18, 0, 2560, 1250);
		contentPane.add(lblNewLabel);
	}
}