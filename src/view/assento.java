package view;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;

import dao.IngressoDAO;

public class assento extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private String filme;
	private String horario;
	private String tipo;
	private String data;

	private String assentoSelecionado = null;

	private JButton assentoSelecionadoBtn = null;

	public assento(
			String filme,
			String horario,
			String tipoSelecionado,
			String dataSelecionada
	) {

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		this.filme = filme;
		this.horario = horario;
		this.tipo = tipoSelecionado;
		this.data = dataSelecionada;

		setTitle("Assentos");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 1920, 1080);

		contentPane = new JPanel();

		contentPane.setLayout(null);

		contentPane.setBackground(Color.BLACK);

		// ÁREA MAIOR QUE A JANELA
		contentPane.setPreferredSize(new Dimension(2200, 1400));

		// SCROLL
		JScrollPane scrollPane = new JScrollPane(contentPane);

		scrollPane.setHorizontalScrollBarPolicy(
		        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		scrollPane.setVerticalScrollBarPolicy(
		        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		setContentPane(scrollPane);

		// ================= DEBUG =================

		System.out.println(
				"Verificando assentos ocupados para:\n" +
				"Filme: " + filme +
				"\nSessão: " + horario +
				"\nTipo: " + tipo +
				"\nData: " + data
		);

		// ================= IMAGEM TOPO =================

		JLabel lblLogo = new JLabel("");

		lblLogo.setIcon(
				new ImageIcon(
						"images/image-removebg-preview (4).png"
				)
		);

		lblLogo.setBounds(587, 120, 1279, 205);

		contentPane.add(lblLogo);

		// ================= INFORMAÇÕES =================

		JLabel lblInfo = new JLabel(
				"Filme: " + filme +
				"   Sessão: " + horario +
				"   Tipo: " + tipo +
				"   Data: " + data
		);

		lblInfo.setForeground(Color.WHITE);

		lblInfo.setFont(
				new Font(
						"Tahoma",
						Font.BOLD,
						22
				)
		);

		lblInfo.setBounds(500, 255, 1200, 40);

		contentPane.add(lblInfo);

		// ================= TELA DO CINEMA =================

		JPanel painelTelaCinema = new JPanel() {

			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {

				super.paintComponent(g);

				Graphics2D g2 =
						(Graphics2D) g;

				g2.setRenderingHint(
						RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON
				);

				// Sombra da tela
				g2.setColor(new Color(0, 0, 0, 180));

				g2.fillRoundRect(
						18,
						18,
						getWidth() - 36,
						getHeight() - 30,
						40,
						40
				);

				// Corpo da tela
				g2.setColor(new Color(220, 220, 220));

				g2.fillRoundRect(
						10,
						10,
						getWidth() - 20,
						getHeight() - 25,
						40,
						40
				);

				// Borda da tela
				g2.setColor(Color.WHITE);

				g2.setStroke(
						new BasicStroke(3)
				);

				g2.drawRoundRect(
						10,
						10,
						getWidth() - 20,
						getHeight() - 25,
						40,
						40
				);

				// Base inferior da tela
				g2.setColor(new Color(90, 90, 90));

				g2.fillRect(
						80,
						getHeight() - 18,
						getWidth() - 160,
						10
				);
			}
		};

		painelTelaCinema.setOpaque(false);

		painelTelaCinema.setBounds(540, 315, 780, 90);

		contentPane.add(painelTelaCinema);

		JLabel lblTelaTexto =
				new JLabel("TELA DO CINEMA");

		lblTelaTexto.setForeground(Color.BLACK);

		lblTelaTexto.setFont(
				new Font(
						"Tahoma",
						Font.BOLD,
						24
				)
		);

		lblTelaTexto.setHorizontalAlignment(JLabel.CENTER);

		lblTelaTexto.setBounds(540, 340, 780, 40);

		contentPane.add(lblTelaTexto);

		// ================= PAINEL ASSENTOS =================

		JPanel painelAssentos =
				new JPanel();

		painelAssentos.setBounds(620, 430, 650, 280);

		painelAssentos.setLayout(null);

		painelAssentos.setOpaque(false);

		contentPane.add(painelAssentos);

		String[] assentos = {

				"A1", "A2", "A3", "A4", "A5",

				"B1", "B2", "B3", "B4", "B5",

				"C1", "C2", "C3", "C4", "C5",

				"D1", "D2", "D3", "D4", "D5"
		};

		/*
		 * POSIÇÕES EM FORMATO QUADRADO / GRADE
		 *
		 * A = primeira fileira
		 * B = segunda fileira
		 * C = terceira fileira
		 * D = quarta fileira
		 */

		int[][] posicoes = {

				// Linha A
				{0, 0}, {120, 0}, {240, 0}, {360, 0}, {480, 0},

				// Linha B
				{0, 65}, {120, 65}, {240, 65}, {360, 65}, {480, 65},

				// Linha C
				{0, 130}, {120, 130}, {240, 130}, {360, 130}, {480, 130},

				// Linha D
				{0, 195}, {120, 195}, {240, 195}, {360, 195}, {480, 195}
		};

		// ================= LOOP ASSENTOS =================

		for (int i = 0; i < assentos.length; i++) {

			String nomeAssento =
					assentos[i];

			JButton btnAssento =
					new JButton(nomeAssento);

			btnAssento.setBounds(
					posicoes[i][0],
					posicoes[i][1],
					75,
					45
			);

			btnAssento.setBackground(Color.GRAY);

			btnAssento.setForeground(Color.BLACK);

			btnAssento.setFont(
					new Font(
							"Tahoma",
							Font.BOLD,
							13
					)
			);

			btnAssento.setFocusPainted(false);

			// ================= ASSENTO BLOQUEADO ADMIN =================

			boolean bloqueadoAdmin =
					dao.AssentoDAO.assentoOcupado(
							filme,
							horario,
							tipo,
							data,
							nomeAssento
					);

			// ================= ASSENTO OCUPADO CLIENTE =================

			boolean ocupadoCliente =
					IngressoDAO.assentoOcupado(
							filme,
							horario,
							tipo,
							data,
							nomeAssento
					);

			// ================= DEBUG =================

			System.out.println(
					nomeAssento +
					" -> Admin: " + bloqueadoAdmin +
					" | Cliente: " + ocupadoCliente
			);

			// ================= BLOQUEIO =================

			if (ocupadoCliente) {

				btnAssento.setBackground(new Color(120, 0, 0));

				btnAssento.setForeground(Color.WHITE);

				btnAssento.setEnabled(false);

			} else if (bloqueadoAdmin) {

				btnAssento.setBackground(Color.RED);

				btnAssento.setForeground(Color.WHITE);

				btnAssento.setEnabled(false);
			}

			// ================= SELECIONAR ASSENTO =================

			btnAssento.addActionListener(e -> {

				if (assentoSelecionadoBtn != null) {

					assentoSelecionadoBtn.setBackground(
							Color.GRAY
					);

					assentoSelecionadoBtn.setForeground(
							Color.BLACK
					);
				}

				assentoSelecionadoBtn =
						btnAssento;

				assentoSelecionado =
						nomeAssento;

				btnAssento.setBackground(
						Color.ORANGE
				);

				btnAssento.setForeground(
						Color.BLACK
				);
			});

			painelAssentos.add(btnAssento);
		}

		// ================= LEGENDA =================

		JButton corLivre =
				new JButton("");

		corLivre.setEnabled(false);

		corLivre.setBackground(Color.GRAY);

		corLivre.setBounds(470, 760, 40, 25);

		contentPane.add(corLivre);

		JLabel lblLegendaLivre =
				new JLabel("Livre");

		lblLegendaLivre.setForeground(Color.WHITE);

		lblLegendaLivre.setBounds(520, 760, 100, 25);

		contentPane.add(lblLegendaLivre);

		JButton corSelecionado =
				new JButton("");

		corSelecionado.setEnabled(false);

		corSelecionado.setBackground(Color.ORANGE);

		corSelecionado.setBounds(650, 760, 40, 25);

		contentPane.add(corSelecionado);

		JLabel lblLegendaSelecionado =
				new JLabel("Selecionado");

		lblLegendaSelecionado.setForeground(Color.WHITE);

		lblLegendaSelecionado.setBounds(700, 760, 150, 25);

		contentPane.add(lblLegendaSelecionado);

		JButton corOcupado =
				new JButton("");

		corOcupado.setEnabled(false);

		corOcupado.setBackground(new Color(120, 0, 0));

		corOcupado.setBounds(880, 760, 40, 25);

		contentPane.add(corOcupado);

		JLabel lblLegendaOcupado =
				new JLabel("Ocupado");

		lblLegendaOcupado.setForeground(Color.WHITE);

		lblLegendaOcupado.setBounds(930, 760, 150, 25);

		contentPane.add(lblLegendaOcupado);

		JButton corBloqueado =
				new JButton("");

		corBloqueado.setEnabled(false);

		corBloqueado.setBackground(Color.RED);

		corBloqueado.setBounds(1080, 760, 40, 25);

		contentPane.add(corBloqueado);

		JLabel lblLegendaBloqueado =
				new JLabel("Bloqueado Admin");

		lblLegendaBloqueado.setForeground(Color.WHITE);

		lblLegendaBloqueado.setBounds(1130, 760, 180, 25);

		contentPane.add(lblLegendaBloqueado);

		// ================= BOTÃO CONFIRMAR =================

		JButton btnConfirmar =
				new JButton("Confirmar");

		btnConfirmar.setBounds(670, 820, 179, 53);

		btnConfirmar.setFont(
				new Font(
						"Tahoma",
						Font.BOLD,
						14
				)
		);

		btnConfirmar.addActionListener(e -> {

			if (assentoSelecionado == null) {

				JOptionPane.showMessageDialog(
						null,
						"Selecione um assento!"
				);

				return;
			}

			// ================= VERIFICA NOVAMENTE =================

			if (
					IngressoDAO.assentoOcupado(
							filme,
							horario,
							tipo,
							data,
							assentoSelecionado
					)
			) {

				JOptionPane.showMessageDialog(
						null,
						"Esse assento acabou de ser ocupado!"
				);

				new assento(
						filme,
						horario,
						tipo,
						data
				).setVisible(true);

				dispose();

				return;
			}

			pagamento telaPagamento =
					new pagamento(
							filme,
							horario,
							assentoSelecionado,
							tipo,
							data
					);

			telaPagamento.setVisible(true);

			dispose();
		});

		contentPane.add(btnConfirmar);

		// ================= BOTÃO VOLTAR =================

		JButton btnVoltar =
				new JButton("Voltar");

		btnVoltar.setBounds(912, 820, 179, 53);

		btnVoltar.setFont(
				new Font(
						"Tahoma",
						Font.BOLD,
						14
				)
		);

		btnVoltar.addActionListener(e -> {

			Sessao telaSessao =
					new Sessao(filme);

			telaSessao.setVisible(true);

			dispose();
		});

		contentPane.add(btnVoltar);

		// ================= ROBÔ =================

		JLabel lblRobo =
				new JLabel("");

		lblRobo.setIcon(
				new ImageIcon(
						"images/Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"
				)
		);

		lblRobo.setBounds(1350, 500, 445, 524);

		contentPane.add(lblRobo);

		// ================= FUNDO =================

		JLabel lblFundo =
				new JLabel("");

		lblFundo.setIcon(
				new ImageIcon(
						"images/galaxy_2560x1250.png"
				)
		);

		lblFundo.setBounds(-20, 0, 2560, 1250);

		contentPane.add(lblFundo);

		contentPane.setComponentZOrder(
				lblFundo,
				contentPane.getComponentCount() - 1
		);
	}
}