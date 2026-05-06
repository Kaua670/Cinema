package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controle.ControlerAssento;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

import dao.IngressoDAO;

public class assento extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private String filme;
	private String horario;
	private String tipo;

	private String assentoSelecionado = null;

	private JButton assentoSelecionadoBtn = null;

	public assento(String filme, String horario, String tipoSelecionado) {

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		this.filme = filme;
		this.horario = horario;
		this.tipo = tipoSelecionado;

		setTitle("Assentos");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 1920, 1080);

		contentPane = new JPanel();

		contentPane.setLayout(null);

		contentPane.setBackground(Color.BLACK);

		setContentPane(contentPane);

		// ================= DEBUG =================
		System.out.println(
				"Verificando assentos ocupados para:\n" +
				"Filme: " + filme +
				"\nSessão: " + horario +
				"\nTipo: " + tipo
		);

		// ================= INFORMAÇÕES =================
		JLabel lblInfo = new JLabel(
				"Filme: " + filme +
				"   Sessão: " + horario +
				"   Tipo: " + tipo
		);

		lblInfo.setForeground(Color.WHITE);

		lblInfo.setFont(
				new Font(
						"Tahoma",
						Font.BOLD,
						22
				)
		);

		lblInfo.setBounds(706, 267, 900, 63);

		contentPane.add(lblInfo);

		// ================= PAINEL ASSENTOS =================
		JPanel painelAssentos = new JPanel();

		painelAssentos.setBounds(464, 341, 861, 205);

		painelAssentos.setLayout(
				new GridLayout(4, 5, 10, 10)
		);

		painelAssentos.setOpaque(false);

		contentPane.add(painelAssentos);

		String[] assentos = {

				"A1", "A2", "A3", "A4", "A5",

				"B1", "B2", "B3", "B4", "B5",

				"C1", "C2", "C3", "C4", "C5",

				"D1", "D2", "D3", "D4", "D5"
		};

		// ================= LOOP ASSENTOS =================
		for (String nomeAssento : assentos) {

			JButton btnAssento =
					new JButton(nomeAssento);

			btnAssento.setBackground(Color.GRAY);

			btnAssento.setForeground(Color.BLACK);

			btnAssento.setFocusPainted(false);

			// ================= ASSENTO BLOQUEADO ADMIN =================
			boolean bloqueadoAdmin =
					ControlerAssento.assentosBloqueados
							.contains(nomeAssento);

			// ================= ASSENTO OCUPADO CLIENTE =================
			boolean ocupadoCliente =
					IngressoDAO.assentoOcupado(
							filme,
							horario,
							tipo,
							nomeAssento
					);

			// ================= DEBUG =================
			System.out.println(
					nomeAssento +
					" -> Admin: " + bloqueadoAdmin +
					" | Cliente: " + ocupadoCliente
			);

			// ================= BLOQUEIA ASSENTO =================
			if (bloqueadoAdmin || ocupadoCliente) {

				btnAssento.setBackground(Color.RED);

				btnAssento.setForeground(Color.WHITE);

				btnAssento.setEnabled(false);
			}

			// ================= SELECIONAR ASSENTO =================
			btnAssento.addActionListener(e -> {

				// remove seleção anterior
				if (assentoSelecionadoBtn != null) {

					assentoSelecionadoBtn.setBackground(
							Color.GRAY
					);

					assentoSelecionadoBtn.setForeground(
							Color.BLACK
					);
				}

				// nova seleção
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

		// ================= BOTÃO CONFIRMAR =================
		JButton btnConfirmar =
				new JButton("Confirmar");

		btnConfirmar.setBounds(670, 557, 179, 53);

		btnConfirmar.addActionListener(e -> {

			if (assentoSelecionado == null) {

				JOptionPane.showMessageDialog(
						null,
						"Selecione um assento!"
				);

				return;
			}

			// 🔥 VERIFICA NOVAMENTE ANTES DE PAGAR
			if (
					IngressoDAO.assentoOcupado(
							filme,
							horario,
							tipo,
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
						tipo
				).setVisible(true);

				dispose();

				return;
			}

			pagamento telaPagamento =
					new pagamento(
							filme,
							horario,
							assentoSelecionado,
							tipo
					);

			telaPagamento.setVisible(true);

			dispose();
		});

		contentPane.add(btnConfirmar);

		// ================= BOTÃO VOLTAR =================
		JButton btnVoltar =
				new JButton("Voltar");

		btnVoltar.setBounds(912, 557, 179, 53);

		btnVoltar.addActionListener(e -> {

			Sessao telaSessao =
					new Sessao(filme);

			telaSessao.setVisible(true);

			dispose();
		});

		contentPane.add(btnVoltar);

		// ================= IMAGEM TOPO =================
		JLabel lbl1 = new JLabel("");

		lbl1.setIcon(
				new ImageIcon(
						"images/image-removebg-preview (4).png"
				)
		);

		lbl1.setBounds(587, 150, 1279, 205);

		contentPane.add(lbl1);

		// ================= ROBÔ =================
		JLabel lbl2 = new JLabel("");

		lbl2.setIcon(
				new ImageIcon(
						"images/Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"
				)
		);

		lbl2.setBounds(922, 535, 445, 524);

		contentPane.add(lbl2);

		// ================= FUNDO =================
		JLabel lbl3 = new JLabel("");

		lbl3.setIcon(
				new ImageIcon(
						"images/galaxy_2560x1250.png"
				)
		);

		lbl3.setBounds(-20, 0, 2560, 1250);

		contentPane.add(lbl3);

		// 🔥 FUNDO ATRÁS
		contentPane.setComponentZOrder(
				lbl3,
				contentPane.getComponentCount() - 1
		);
	}
}