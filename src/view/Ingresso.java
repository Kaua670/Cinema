package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class Ingresso extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				new Ingresso().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Ingresso() {

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Filmes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// 🔙 VOLTAR
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(1700, 50, 150, 50);
		btnVoltar.addActionListener(e -> {
			new telalogin().setVisible(true);
			dispose();
		});
		contentPane.add(btnVoltar);

		// 🎬 BUSCAR FILMES DO BANCO
		ArrayList<String[]> filmes = dao.FilmeDAO.listarFilmes();

		int x = 500;
		int y = 350;

		for (String[] f : filmes) {

			String nome = f[0];
			String classificacao = f[1];
			String imagem = f[2];

			// 🔥 CORREÇÃO DO CAMINHO
			if (imagem != null) {
				imagem = imagem.replace("\\", "/");
			}

			// 🖼️ IMAGEM
			JLabel lblFilme = new JLabel();
			lblFilme.setBounds(x, y, 150, 220);
			lblFilme.setCursor(new Cursor(Cursor.HAND_CURSOR));

			ImageIcon icon = new ImageIcon(imagem);

			// 🔥 VALIDAÇÃO DA IMAGEM
			if (icon.getIconWidth() == -1) {
				System.out.println("❌ ERRO AO CARREGAR IMAGEM: " + imagem);

				// imagem fallback (opcional)
				lblFilme.setText("Sem imagem");
				lblFilme.setForeground(Color.WHITE);
			} else {
				Image img = icon.getImage().getScaledInstance(150, 220, Image.SCALE_SMOOTH);
				lblFilme.setIcon(new ImageIcon(img));
			}

			// 🎬 CLICK
			lblFilme.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					new Sessao(nome).setVisible(true);
					dispose();
				}
			});

			contentPane.add(lblFilme);

			// 📛 NOME
			JLabel lblNome = new JLabel(nome);
			lblNome.setForeground(Color.WHITE);
			lblNome.setBounds(x, y + 230, 150, 20);
			contentPane.add(lblNome);

			// 🔞 CLASSIFICAÇÃO
			JLabel lblClass = new JLabel("Classificação: " + classificacao);
			lblClass.setForeground(Color.WHITE);
			lblClass.setBounds(x, y + 250, 200, 20);
			contentPane.add(lblClass);

			x += 200;

			// quebra de linha
			if (x > 1200) {
				x = 500;
				y += 300;
			}
		}

		// 🎨 TÍTULO
		JLabel titulo = new JLabel("Filmes em Cartaz");
		titulo.setFont(new Font("Verdana", Font.BOLD, 28));
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(700, 200, 400, 50);
		contentPane.add(titulo);

		// 🌌 FUNDO
		JLabel fundo = new JLabel();
		fundo.setIcon(new ImageIcon("images/galaxy_2560x1250.png"));
		fundo.setBounds(-20, 0, 2060, 1250);
		contentPane.add(fundo);

		// 🔥 GARANTE FUNDO ATRÁS
		contentPane.setComponentZOrder(fundo, contentPane.getComponentCount() - 1);
	}
}