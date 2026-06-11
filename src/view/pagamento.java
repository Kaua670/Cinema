package view;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
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
	private String data;

	private JTextField txtValor;

	private JComboBox<String> comboPagamento;
	private JComboBox<String> comboTipoCartao;
	private JComboBox<String> comboParcelas;

	private JLabel PIX20;
	private JLabel PIX30;

	private JButton btnFinalizar;
	private JButton btnConfirmarPix;

	private JTextField txtCodigoPix;
	private JButton btnCopiarPix;
	private JLabel lblCodigoPix;

	private boolean pagamentoPixConfirmado = false;

	public pagamento(
			String filme,
			String horario,
			String assento,
			String tipo,
			String data
	) {

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		this.filme = filme;
		this.horario = horario;
		this.assento = assento;
		this.tipo = tipo;
		this.data = data;

		setTitle("Pagamento");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 2560, 1250);

		contentPane = new JPanel();

		contentPane.setLayout(null);

		contentPane.setBackground(Color.BLACK);
		
		contentPane = new JPanel();
		contentPane.setLayout(null);

		// área maior que a tela
		contentPane.setPreferredSize(new Dimension(2560, 1400));

		JScrollPane scrollPane = new JScrollPane(contentPane);

		scrollPane.setVerticalScrollBarPolicy(
		        JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		scrollPane.setHorizontalScrollBarPolicy(
		        JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		setContentPane(scrollPane);

		// ===== FILME =====
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

		lblFilme.setBounds(655, 401, 400, 25);

		contentPane.add(lblFilme);

		// ===== SESSÃO =====
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

		lblSessao.setBounds(655, 437, 400, 25);

		contentPane.add(lblSessao);

		// ===== DATA =====
		JLabel lblData =
				new JLabel("Data: " + data);

		lblData.setFont(
				new Font(
						"Tahoma",
						Font.PLAIN,
						20
				)
		);

		lblData.setForeground(Color.WHITE);

		lblData.setBounds(655, 473, 400, 25);

		contentPane.add(lblData);

		// ===== ASSENTO =====
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

		lblAssento.setBounds(655, 509, 400, 25);

		contentPane.add(lblAssento);

		// ===== TIPO =====
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

		lblTipo.setBounds(655, 545, 400, 25);

		contentPane.add(lblTipo);

		// ===== VALOR =====
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

		lblValor.setBounds(655, 581, 80, 25);

		contentPane.add(lblValor);

		txtValor = new JTextField();

		txtValor.setBounds(718, 581, 140, 25);

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

		// ===== CÓDIGO PIX COPIA E COLA =====
		lblCodigoPix =
				new JLabel("PIX copia e cola:");

		lblCodigoPix.setFont(
				new Font(
						"Tahoma",
						Font.PLAIN,
						16
				)
		);

		lblCodigoPix.setForeground(Color.WHITE);

		lblCodigoPix.setBounds(965, 760, 200, 25);

		lblCodigoPix.setVisible(false);

		contentPane.add(lblCodigoPix);

		txtCodigoPix =
				new JTextField();

		txtCodigoPix.setBounds(965, 790, 392, 30);

		txtCodigoPix.setEditable(false);

		txtCodigoPix.setVisible(false);

		contentPane.add(txtCodigoPix);

		btnCopiarPix =
				new JButton("Copiar código PIX");

		btnCopiarPix.setBounds(965, 830, 180, 35);

		btnCopiarPix.setVisible(false);

		btnCopiarPix.addActionListener(e -> copiarCodigoPix());

		contentPane.add(btnCopiarPix);

		// ===== QR 20 =====
		PIX20 = new JLabel();

		PIX20.setIcon(
				new ImageIcon(
						"images/WhatsApp Image 2026-04-15 at 20.52.42.jpeg"
				)
		);

		PIX20.setBounds(965, 388, 313, 319);

		PIX20.setVisible(false);

		contentPane.add(PIX20);

		// ===== QR 30 =====
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

		// ===== BOTÃO PIX =====
		btnConfirmarPix =
				new JButton("Confirmar PIX");

		btnConfirmarPix.setBounds(655, 854, 133, 43);

		btnConfirmarPix.setVisible(false);

		btnConfirmarPix.addActionListener(e -> {

			pagamentoPixConfirmado = true;

			JOptionPane.showMessageDialog(
					this,
					"PIX confirmado!"
			);
		});

		contentPane.add(btnConfirmarPix);

		// ===== EVENTO PAGAMENTO =====
		comboPagamento.addActionListener(e -> atualizarFormaPagamento());

		// ===== EVENTO TIPO CARTÃO =====
		comboTipoCartao.addActionListener(e -> atualizarParcelasCartao());

		// ===== BOTÃO VOLTAR =====
		JButton btnVoltar =
				new JButton("Voltar");

		btnVoltar.setBounds(798, 800, 133, 43);

		btnVoltar.addActionListener(e -> {

			new assento(
					filme,
					horario,
					tipo,
					data
			).setVisible(true);

			dispose();
		});

		contentPane.add(btnVoltar);

		// ===== BOTÃO IMPRIMIR =====
		JButton btnImprimir =
				new JButton("Imprimir");

		btnImprimir.setBounds(798, 854, 133, 43);

		btnImprimir.addActionListener(e -> {

			String texto =

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

					+ "Data: "
					+ data
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

			imprimirRelatorio(texto);
		});

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

	// ===== ATUALIZA FORMA DE PAGAMENTO =====
	private void atualizarFormaPagamento() {

		pagamentoPixConfirmado = false;

		String tipoPagamento =
				comboPagamento
						.getSelectedItem()
						.toString();

		if (tipoPagamento.equals("Cartão")) {

			comboTipoCartao.setEnabled(true);

			atualizarParcelasCartao();

			PIX20.setVisible(false);

			PIX30.setVisible(false);

			btnConfirmarPix.setVisible(false);

			lblCodigoPix.setVisible(false);

			txtCodigoPix.setVisible(false);

			btnCopiarPix.setVisible(false);

			txtCodigoPix.setText("");
		}

		else if (tipoPagamento.equals("PIX")) {

			comboTipoCartao.setEnabled(false);

			comboParcelas.setEnabled(false);

			comboParcelas.setSelectedItem("1x");

			btnConfirmarPix.setVisible(true);

			if (tipo.equals("2D")) {

				PIX20.setVisible(true);

				PIX30.setVisible(false);

			} else {

				PIX20.setVisible(false);

				PIX30.setVisible(true);
			}

			String codigoPix =
					gerarCodigoPix();

			txtCodigoPix.setText(codigoPix);

			lblCodigoPix.setVisible(true);

			txtCodigoPix.setVisible(true);

			btnCopiarPix.setVisible(true);
		}

		else {

			comboTipoCartao.setEnabled(false);

			comboParcelas.setEnabled(false);

			comboParcelas.setSelectedItem("1x");

			PIX20.setVisible(false);

			PIX30.setVisible(false);

			btnConfirmarPix.setVisible(false);

			lblCodigoPix.setVisible(false);

			txtCodigoPix.setVisible(false);

			btnCopiarPix.setVisible(false);

			txtCodigoPix.setText("");
		}
	}

	// ===== PARCELAS DO CARTÃO =====
	private void atualizarParcelasCartao() {

		String tipoCartao =
				comboTipoCartao
						.getSelectedItem()
						.toString();

		if (tipoCartao.equals("Débito")) {

			comboParcelas.setSelectedItem("1x");

			comboParcelas.setEnabled(false);

		} else {

			comboParcelas.setEnabled(true);
		}
	}

	// ===== GERAR CÓDIGO PIX COPIA E COLA =====
	private String gerarCodigoPix() {

		String valor =
				txtValor
						.getText()
						.replace(",", ".");

		String codigoPix =
				"000201"
				+ "26360014BR.GOV.BCB.PIX"
				+ "0114cinelumi@email"
				+ "52040000"
				+ "5303986"
				+ "5405" + valor
				+ "5802BR"
				+ "5910CINE LUMI"
				+ "6009CURITIBA"
				+ "62170513" + assento.replace(" ", "")
				+ "6304ABCD";

		return codigoPix;
	}

	// ===== COPIAR CÓDIGO PIX =====
	private void copiarCodigoPix() {

		String codigo =
				txtCodigoPix.getText();

		if (codigo == null || codigo.trim().isEmpty()) {

			JOptionPane.showMessageDialog(
					this,
					"Nenhum código PIX gerado!"
			);

			return;
		}

		StringSelection selection =
				new StringSelection(codigo);

		Clipboard clipboard =
				Toolkit
						.getDefaultToolkit()
						.getSystemClipboard();

		clipboard.setContents(selection, null);

		JOptionPane.showMessageDialog(
				this,
				"Código PIX copiado!"
		);
	}

	// ===== FINALIZAR =====
	private void finalizarPagamento() {

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

		// ===== VERIFICA SE ASSENTO JÁ FOI COMPRADO =====
		if (
				IngressoDAO.assentoOcupado(
						filme,
						horario,
						tipo,
						data,
						assento
				)
		) {

			JOptionPane.showMessageDialog(
					this,
					"Esse assento já foi comprado!"
			);

			return;
		}

		// ===== SALVA ASSENTO =====
		AssentoDAO.salvarAssento(
				filme,
				horario,
				tipo,
				data,
				assento
		);

		// ===== SALVA INGRESSO =====
		IngressoDAO.salvarIngresso(
				SessaoUsuario.usuarioLogado,
				filme,
				horario,
				tipo,
				data,
				assento,
				comboPagamento.getSelectedItem().toString(),
				txtValor.getText()
		);

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

				+ "Data: "
				+ data
				+ "\n"

				+ "Tipo: "
				+ tipo
				+ "\n"

				+ "Assento: "
				+ assento
				+ "\n"

				+ "Pagamento: "
				+ comboPagamento.getSelectedItem()
				+ "\n";

		if (comboPagamento.getSelectedItem().equals("Cartão")) {

			relatorio +=
					"Tipo do cartão: "
					+ comboTipoCartao.getSelectedItem()
					+ "\n"
					+ "Parcelas: "
					+ comboParcelas.getSelectedItem()
					+ "\n";
		}

		relatorio +=
				"Valor: R$ "
				+ txtValor.getText();

		JOptionPane.showMessageDialog(
				this,
				relatorio
		);

		int op =
				JOptionPane.showConfirmDialog(
						this,
						"Deseja imprimir?"
				);

		if (op == JOptionPane.YES_OPTION) {

			imprimirRelatorio(relatorio);
		}

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

			JOptionPane.showMessageDialog(
					this,
					"Erro ao imprimir!"
			);

			e.printStackTrace();
		}
	}
}