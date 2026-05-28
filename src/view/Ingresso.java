package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Cursor;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;

public class Ingresso extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JPanel painelFilmes;

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

		// ================= BOTÃO VOLTAR =================

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(150, 100, 150, 50);
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnVoltar.addActionListener(e -> {
			new telalogin().setVisible(true);
			dispose();
		});

		contentPane.add(btnVoltar);

		// ================= TÍTULO =================

		JLabel titulo = new JLabel("Filmes em Cartaz");
		titulo.setFont(new Font("Verdana", Font.BOLD, 28));
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(700, 100, 400, 50);
		contentPane.add(titulo);

		// ================= PAINEL DOS FILMES =================

		painelFilmes = new JPanel();
		painelFilmes.setLayout(null);
		painelFilmes.setBackground(Color.BLACK);

		JScrollPane scrollPane = new JScrollPane(painelFilmes);
		scrollPane.setBounds(350, 190, 1200, 650);
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		contentPane.add(scrollPane);

		// ================= BUSCAR FILMES DO BANCO =================

		ArrayList<String[]> filmes = dao.FilmeDAO.listarFilmes();

		int larguraCard = 180;
		int alturaCard = 300;

		int larguraImagem = 150;
		int alturaImagem = 220;

		int margemX = 30;
		int margemY = 30;

		int colunas = 5;

		for (int i = 0; i < filmes.size(); i++) {

			String[] f = filmes.get(i);

			String nome = f[0];
			String classificacao = f[1];
			String imagem = f[2];

			if (imagem != null) {
				imagem = imagem.replace("\\", "/");
			}

			int coluna = i % colunas;
			int linha = i / colunas;

			int x = margemX + coluna * (larguraCard + 40);
			int y = margemY + linha * (alturaCard + 40);

			// ================= IMAGEM DO FILME =================

			JLabel lblFilme = new JLabel();
			lblFilme.setBounds(x, y, larguraImagem, alturaImagem);
			lblFilme.setCursor(new Cursor(Cursor.HAND_CURSOR));
			lblFilme.setHorizontalAlignment(SwingConstants.CENTER);
			lblFilme.setVerticalAlignment(SwingConstants.CENTER);

			ImageIcon icon = new ImageIcon(imagem);

			if (icon.getIconWidth() == -1) {

				System.out.println("❌ ERRO AO CARREGAR IMAGEM: " + imagem);

				lblFilme.setText("Sem imagem");
				lblFilme.setForeground(Color.WHITE);
				lblFilme.setBorder(BorderFactory.createLineBorder(Color.WHITE));

			} else {

				Image img = icon.getImage().getScaledInstance(
						larguraImagem,
						alturaImagem,
						Image.SCALE_SMOOTH
				);

				lblFilme.setIcon(new ImageIcon(img));
			}

			lblFilme.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseClicked(java.awt.event.MouseEvent e) {
					new Sessao(nome).setVisible(true);
					dispose();
				}
			});

			painelFilmes.add(lblFilme);

			// ================= NOME DO FILME =================

			JLabel lblNome = new JLabel(nome);
			lblNome.setForeground(Color.WHITE);
			lblNome.setFont(new Font("Tahoma", Font.BOLD, 13));
			lblNome.setBounds(x, y + 230, 180, 25);
			lblNome.setHorizontalAlignment(SwingConstants.CENTER);
			painelFilmes.add(lblNome);

			// ================= CLASSIFICAÇÃO =================

			JLabel lblClass = new JLabel("Classificação: " + classificacao);
			lblClass.setForeground(Color.WHITE);
			lblClass.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblClass.setBounds(x, y + 255, 180, 25);
			lblClass.setHorizontalAlignment(SwingConstants.CENTER);
			painelFilmes.add(lblClass);
		}

		// ================= AJUSTA ALTURA DO PAINEL CONFORME QUANTIDADE =================

		int quantidadeFilmes = filmes.size();

		int quantidadeLinhas =
				(int) Math.ceil(quantidadeFilmes / (double) colunas);

		int alturaTotal =
				margemY + quantidadeLinhas * (alturaCard + 40);

		if (alturaTotal < 650) {
			alturaTotal = 650;
		}

		painelFilmes.setPreferredSize(
				new java.awt.Dimension(1150, alturaTotal)
		);

		painelFilmes.revalidate();
		painelFilmes.repaint();

		// ================= FUNDO =================

		JLabel fundo = new JLabel();
		fundo.setIcon(new ImageIcon("images/galaxy_2560x1250.png"));
		fundo.setBounds(-20, 0, 2060, 1250);
		contentPane.add(fundo);

		// GARANTE FUNDO ATRÁS
		contentPane.setComponentZOrder(
				fundo,
				contentPane.getComponentCount() - 1
		);
	}
}