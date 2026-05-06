package view;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;

import Controle.ControlePreco;
import Controle.SessaoUsuario;

import dao.IngressoDAO;
import dao.AssentoDAO;

public class pagamento extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private String filme;
	private String horario;
	private String assento;
	private String tipo;

	private JTextField txtValor;

	private JComboBox<String> comboPagamento;
	private JComboBox<String> comboTipoCartao;
	private JComboBox<String> comboParcelas;

	private JLabel PIX20;
	private JLabel PIX30;

	private JButton btnFinalizar;
	private JButton btnConfirmarPix;

	private boolean pagamentoPixConfirmado = false;

	public pagamento(
			String filme,
			String horario,
			String assento,
			String tipo
	) {

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		this.filme = filme;
		this.horario = horario;
		this.assento = assento;
		this.tipo = tipo;

		setTitle("Pagamento");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 1920, 1080);

		contentPane = new JPanel();

		contentPane.setLayout(null);

		contentPane.setBackground(Color.BLACK);

		setContentPane(contentPane);

		// ===== TEXTOS =====
		JLabel lblFilme =
				new JLabel("Filme: " + filme);

		lblFilme.setFont(
				new Font(
						"Tahoma",
						Font.PLAIN,
						20
				)
		);

		lblFilme.setForeground(Color.WHITE);

		lblFilme.setBounds(655, 401, 300, 25);

		contentPane.add(lblFilme);

		JLabel lblSessao =
				new JLabel("Sessão: " + horario);

		lblSessao.setFont(
				new Font(
						"Tahoma",
						Font.PLAIN,
						20
				)
		);

		lblSessao.setForeground(Color.WHITE);

		lblSessao.setBounds(655, 437, 300, 25);

		contentPane.add(lblSessao);

		JLabel lblAssento =
				new JLabel("Assento: " + assento);

		lblAssento.setFont(
				new Font(
						"Tahoma",
						Font.PLAIN,
						20
				)
		);

		lblAssento.setForeground(Color.WHITE);

		lblAssento.setBounds(655, 473, 300, 25);

		contentPane.add(lblAssento);

		JLabel lblTipo =
				new JLabel("Tipo: " + tipo);

		lblTipo.setFont(
				new Font(
						"Tahoma",
						Font.PLAIN,
						20
				)
		);

		lblTipo.setForeground(Color.WHITE);

		lblTipo.setBounds(655, 509, 300, 25);

		contentPane.add(lblTipo);

		JLabel lblValor =
				new JLabel("Valor:");

		lblValor.setFont(
				new Font(
						"Tahoma",
						Font.PLAIN,
						20
				)
		);

		lblValor.setForeground(Color.WHITE);

		lblValor.setBounds(655, 545, 80, 25);

		contentPane.add(lblValor);

		// ===== CAMPO VALOR =====
		txtValor = new JTextField();

		txtValor.setBounds(718, 545, 140, 25);

		txtValor.setEditable(false);

		contentPane.add(txtValor);

		atualizarValor();

		// ===== PAGAMENTO =====
		comboPagamento =
				new JComboBox<>();

		comboPagamento.addItem("Dinheiro");

		comboPagamento.addItem("Cartão");

		comboPagamento.addItem("PIX");

		comboPagamento.setBounds(655, 631, 228, 25);

		contentPane.add(comboPagamento);

		// ===== TIPO CARTÃO =====
		comboTipoCartao =
				new JComboBox<>();

		comboTipoCartao.addItem("Débito");

		comboTipoCartao.addItem("Crédito");

		comboTipoCartao.setBounds(655, 689, 228, 25);

		comboTipoCartao.setEnabled(false);

		contentPane.add(comboTipoCartao);

		// ===== PARCELAS =====
		comboParcelas =
				new JComboBox<>();

		comboParcelas.addItem("1x");

		comboParcelas.addItem("2x");

		comboParcelas.addItem("3x");

		comboParcelas.setBounds(655, 725, 228, 25);

		comboParcelas.setEnabled(false);

		contentPane.add(comboParcelas);

		// ===== EVENTO PAGAMENTO =====
		comboPagamento.addActionListener(e -> {

			String tipoPagamento =
					comboPagamento
							.getSelectedItem()
							.toString();

			if (tipoPagamento.equals("Cartão")) {

				comboTipoCartao.setEnabled(true);

				comboParcelas.setEnabled(true);

				PIX20.setVisible(false);

				PIX30.setVisible(false);

				btnConfirmarPix.setVisible(false);

			}

			else if (tipoPagamento.equals("PIX")) {

				comboTipoCartao.setEnabled(false);

				comboParcelas.setEnabled(false);

				btnConfirmarPix.setVisible(true);

				if (tipo.equals("2D")) {

					PIX20.setVisible(true);

					PIX30.setVisible(false);

				} else {

					PIX20.setVisible(false);

					PIX30.setVisible(true);
				}

			}

			else {

				comboTipoCartao.setEnabled(false);

				comboParcelas.setEnabled(false);

				PIX20.setVisible(false);

				PIX30.setVisible(false);

				btnConfirmarPix.setVisible(false);
			}
		});

		// ===== QR CODE 20 =====
		PIX20 = new JLabel();

		PIX20.setIcon(
				new ImageIcon(
						"images/WhatsApp Image 2026-04-15 at 20.52.42.jpeg"
				)
		);

		PIX20.setBounds(965, 388, 313, 319);

		PIX20.setVisible(false);

		contentPane.add(PIX20);

		// ===== QR CODE 30 =====
		PIX30 = new JLabel();

		PIX30.setIcon(
				new ImageIcon(
						"images/WhatsApp Image 2026-04-15 at 20.52.48.jpeg"
				)
		);

		PIX30.setBounds(965, 347, 392, 403);

		PIX30.setVisible(false);

		contentPane.add(PIX30);

		// ===== BOTÃO FINALIZAR =====
		btnFinalizar =
				new JButton("Finalizar");

		btnFinalizar.setBounds(655, 800, 133, 43);

		btnFinalizar.addActionListener(
				e -> finalizarPagamento()
		);

		contentPane.add(btnFinalizar);

		// ===== BOTÃO CONFIRMAR PIX =====
		btnConfirmarPix =
				new JButton("Confirmar PIX");

		btnConfirmarPix.setBounds(655, 854, 133, 43);

		btnConfirmarPix.setVisible(false);

		btnConfirmarPix.addActionListener(e -> {

			pagamentoPixConfirmado = true;

			btnFinalizar.setEnabled(true);

			JOptionPane.showMessageDialog(
					this,
					"PIX confirmado!"
			);
		});

		contentPane.add(btnConfirmarPix);

		// ===== BOTÃO VOLTAR =====
		JButton btnVoltar =
				new JButton("Voltar");

		btnVoltar.setBounds(655, 965, 133, 43);

		btnVoltar.addActionListener(e -> {

			new assento(
					filme,
					horario,
					tipo
			).setVisible(true);

			dispose();
		});

		contentPane.add(btnVoltar);

		// ===== BOTÃO IMPRIMIR =====
		JButton btnImprimir =
				new JButton("Imprimir");

		btnImprimir.setBounds(655, 911, 133, 43);

		btnImprimir.addActionListener(
				e -> imprimirRelatorio("Teste")
		);

		contentPane.add(btnImprimir);

		// ===== TÍTULO =====
		JLabel titulo =
				new JLabel();

		titulo.setIcon(
				new ImageIcon(
						"images/image-removebg-preview (5).png"
				)
		);

		titulo.setBounds(657, 221, 1279, 205);

		contentPane.add(titulo);

		// ===== FUNDO =====
		JLabel bg =
				new JLabel("");

		bg.setIcon(
				new ImageIcon(
						"images/galaxy_2560x1250.png"
				)
		);

		bg.setBounds(-23, 0, 2560, 1250);

		contentPane.add(bg);

		// ===== FUNDO ATRÁS =====
		contentPane.setComponentZOrder(
				bg,
				contentPane.getComponentCount() - 1
		);
	}

	// ===== VALOR =====
	private void atualizarValor() {

		double valor;

		if (tipo.equals("3D")) {

			valor = ControlePreco.preco3D;

		} else {

			valor = ControlePreco.preco2D;
		}

		txtValor.setText(
				String.format("%.2f", valor)
		);
	}

	// ===== FINALIZAR PAGAMENTO =====
	private void finalizarPagamento() {

		// ===== PIX NÃO CONFIRMADO =====
		if (
				comboPagamento
						.getSelectedItem()
						.equals("PIX")
						&& !pagamentoPixConfirmado
		) {

			JOptionPane.showMessageDialog(
					this,
					"Confirme o PIX primeiro!"
			);

			return;
		}

		// ===== SALVA ASSENTO =====
		AssentoDAO.salvarAssento(
				filme,
				horario,
				tipo,
				assento
		);

		// ===== SALVA INGRESSO =====
		IngressoDAO.salvarIngresso(
				SessaoUsuario.usuarioLogado,
				filme,
				horario,
				tipo,
				assento
		);

		// ===== RELATÓRIO =====
		String relatorio =

				"CINE LUMI\n\n"

				+ "===== RELATÓRIO =====\n\n"

				+ "Cliente: "
				+ SessaoUsuario.usuarioLogado
				+ "\n\n"

				+ "Filme: "
				+ filme
				+ "\n"

				+ "Sessão: "
				+ horario
				+ "\n"

				+ "Tipo: "
				+ tipo
				+ "\n"

				+ "Assento: "
				+ assento
				+ "\n"

				+ "Pagamento: "
				+ comboPagamento.getSelectedItem()
				+ "\n"

				+ "Valor: R$ "
				+ txtValor.getText();

		JOptionPane.showMessageDialog(
				this,
				relatorio
		);

		// ===== IMPRIMIR =====
		int op =
				JOptionPane.showConfirmDialog(
						this,
						"Deseja imprimir?"
				);

		if (op == JOptionPane.YES_OPTION) {

			imprimirRelatorio(relatorio);
		}

		// ===== VOLTA TELA =====
		new Ingresso().setVisible(true);

		dispose();
	}

	// ===== IMPRIMIR =====
	private void imprimirRelatorio(String texto) {

		try {

			JTextArea area =
					new JTextArea(texto);

			area.print();

		} catch (PrinterException e) {

			e.printStackTrace();
		}
	}
}