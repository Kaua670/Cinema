package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Sessao extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private String filme;

	// ================= VARIÁVEIS =================
	private String horarioSelecionado;
	private String tipoSelecionado;
	private String diaSelecionado;

	private JTextField campoData;

	private static final DateTimeFormatter FORMATADOR_DATA =
			DateTimeFormatter.ofPattern("dd/MM/uuuu")
					.withResolverStyle(ResolverStyle.STRICT);

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {

			try {

				Sessao frame =
						new Sessao("Batman");

				frame.setVisible(true);

			} catch (Exception e) {

				e.printStackTrace();
			}
		});
	}

	public Sessao(String filme) {

		this.filme = filme;

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setTitle("Sessões");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 1920, 1080);

		contentPane = new JPanel();

		contentPane.setBackground(Color.BLACK);

		contentPane.setBorder(
				new EmptyBorder(5, 5, 5, 5)
		);

		contentPane.setLayout(null);

		setContentPane(contentPane);

		// ================= TÍTULO =================

		JLabel titulo =
				new JLabel("SELECIONE A SESSÃO");

		titulo.setForeground(Color.WHITE);

		titulo.setFont(
				new Font(
						"Verdana",
						Font.BOLD,
						28
				)
		);

		titulo.setBounds(700, 100, 500, 50);

		contentPane.add(titulo);

		// ================= FILME =================

		JLabel lblFilme =
				new JLabel("Filme: " + filme);

		lblFilme.setForeground(Color.WHITE);

		lblFilme.setFont(
				new Font(
						"Tahoma",
						Font.BOLD,
						22
				)
		);

		lblFilme.setBounds(873, 294, 500, 40);

		contentPane.add(lblFilme);

		// ================= DATA =================

		JLabel lblData =
				new JLabel("Data:");

		lblData.setForeground(Color.WHITE);

		lblData.setFont(
				new Font(
						"Tahoma",
						Font.BOLD,
						18
				)
		);

		lblData.setBounds(789, 349, 250, 30);

		contentPane.add(lblData);

		campoData =
				new JTextField();

		campoData.setBounds(917, 345, 250, 40);

		campoData.setFont(
				new Font(
						"Tahoma",
						Font.BOLD,
						16
				)
		);

		// Traz automaticamente a data do dia atual
		campoData.setText(
				LocalDate.now().format(FORMATADOR_DATA)
		);

		// Limita e formata o campo para dd/MM/yyyy
		((AbstractDocument) campoData.getDocument()).setDocumentFilter(
				new FiltroData()
		);

		contentPane.add(campoData);

		// ================= BOTÕES HORÁRIO =================

		JButton btn14 =
				new JButton("14 Horas");

		btn14.setFont(
				new Font(
						"Tahoma",
						Font.BOLD,
						14
				)
		);

		btn14.setBounds(742, 428, 138, 50);

		contentPane.add(btn14);

		JButton btn18 =
				new JButton("18 Horas");

		btn18.setFont(
				new Font(
						"Tahoma",
						Font.BOLD,
						14
				)
		);

		btn18.setBounds(1053, 428, 138, 50);

		contentPane.add(btn18);

		// ================= BOTÕES 2D / 3D =================

		JButton btn2D =
				new JButton("2D");

		btn2D.setFont(
				new Font(
						"Tahoma",
						Font.BOLD,
						14
				)
		);

		btn2D.setBounds(742, 507, 138, 50);

		btn2D.setVisible(false);

		contentPane.add(btn2D);

		JButton btn3D =
				new JButton("3D");

		btn3D.setFont(
				new Font(
						"Tahoma",
						Font.BOLD,
						14
				)
		);

		btn3D.setBounds(1053, 507, 138, 50);

		btn3D.setVisible(false);

		contentPane.add(btn3D);

		// ================= AÇÃO HORÁRIO 14 =================

		btn14.addActionListener(e -> {

			if (!validarData()) {
				return;
			}

			diaSelecionado =
					campoData.getText();

			horarioSelecionado = "14:00";

			btn2D.setVisible(true);

			btn3D.setVisible(true);
		});

		// ================= AÇÃO HORÁRIO 18 =================

		btn18.addActionListener(e -> {

			if (!validarData()) {
				return;
			}

			diaSelecionado =
					campoData.getText();

			horarioSelecionado = "18:00";

			btn2D.setVisible(true);

			btn3D.setVisible(true);
		});

		// ================= AÇÃO 2D =================

		btn2D.addActionListener(e -> {

			tipoSelecionado = "2D";

			abrirAssento();
		});

		// ================= AÇÃO 3D =================

		btn3D.addActionListener(e -> {

			tipoSelecionado = "3D";

			abrirAssento();
		});

		// ================= BOTÃO VOLTAR =================

		JButton btnVoltar =
				new JButton("Voltar");

		btnVoltar.setFont(
				new Font(
						"Tahoma",
						Font.BOLD,
						14
				)
		);

		btnVoltar.setBounds(896, 598, 143, 57);

		btnVoltar.addActionListener(e -> {

			new Ingresso().setVisible(true);

			dispose();
		});

		contentPane.add(btnVoltar);

		// ================= IMAGENS =================

		JLabel robo =
				new JLabel("");

		robo.setIcon(
				new ImageIcon(
						"images/Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"
				)
		);

		robo.setBounds(1162, 130, 1598, 1342);

		contentPane.add(robo);

		JLabel logo =
				new JLabel("");

		logo.setIcon(
				new ImageIcon(
						"images/image-removebg-preview (3).png"
				)
		);

		logo.setBounds(655, 152, 1279, 205);

		contentPane.add(logo);

		JLabel fundo =
				new JLabel("");

		fundo.setIcon(
				new ImageIcon(
						"images/galaxy_2560x1250.png"
				)
		);

		fundo.setBounds(-22, -45, 2964, 1326);

		contentPane.add(fundo);

		// ===== FUNDO ATRÁS =====
		contentPane.setComponentZOrder(
				fundo,
				contentPane.getComponentCount() - 1
		);
	}

	// ================= VALIDAR DATA =================

	private boolean validarData() {

		String data =
				campoData.getText().trim();

		if (data.isEmpty() || data.length() != 10) {

			JOptionPane.showMessageDialog(
					null,
					"Informe uma data válida no formato dd/MM/yyyy."
			);

			campoData.requestFocus();

			return false;
		}

		try {

			LocalDate dataInformada =
					LocalDate.parse(data, FORMATADOR_DATA);

			LocalDate dataAtual =
					LocalDate.now();

			if (dataInformada.isBefore(dataAtual)) {

				JOptionPane.showMessageDialog(
						null,
						"Não é permitido escolher uma data anterior ao dia de hoje."
				);

				campoData.setText(
						dataAtual.format(FORMATADOR_DATA)
				);

				campoData.requestFocus();

				return false;
			}

			return true;

		} catch (DateTimeParseException e) {

			JOptionPane.showMessageDialog(
					null,
					"Data inválida. Verifique o dia, mês e ano.\nExemplo: fevereiro não aceita dia 30 ou 31."
			);

			campoData.requestFocus();

			return false;
		}
	}

	// ================= FILTRO DO CAMPO DATA =================

	private static class FiltroData extends DocumentFilter {

		@Override
		public void insertString(
				FilterBypass fb,
				int offset,
				String string,
				AttributeSet attr
		) throws BadLocationException {

			replace(fb, offset, 0, string, attr);
		}

		@Override
		public void replace(
				FilterBypass fb,
				int offset,
				int length,
				String text,
				AttributeSet attrs
		) throws BadLocationException {

			String textoAtual =
					fb.getDocument().getText(
							0,
							fb.getDocument().getLength()
					);

			StringBuilder novoTexto =
					new StringBuilder(textoAtual);

			novoTexto.replace(
					offset,
					offset + length,
					text == null ? "" : text
			);

			String somenteNumeros =
					novoTexto.toString().replaceAll("[^0-9]", "");

			if (somenteNumeros.length() > 8) {

				somenteNumeros =
						somenteNumeros.substring(0, 8);
			}

			String textoFormatado =
					formatarData(somenteNumeros);

			fb.replace(
					0,
					fb.getDocument().getLength(),
					textoFormatado,
					attrs
			);
		}

		@Override
		public void remove(
				FilterBypass fb,
				int offset,
				int length
		) throws BadLocationException {

			String textoAtual =
					fb.getDocument().getText(
							0,
							fb.getDocument().getLength()
					);

			StringBuilder novoTexto =
					new StringBuilder(textoAtual);

			novoTexto.delete(
					offset,
					offset + length
			);

			String somenteNumeros =
					novoTexto.toString().replaceAll("[^0-9]", "");

			if (somenteNumeros.length() > 8) {

				somenteNumeros =
						somenteNumeros.substring(0, 8);
			}

			String textoFormatado =
					formatarData(somenteNumeros);

			fb.replace(
					0,
					fb.getDocument().getLength(),
					textoFormatado,
					null
			);
		}

		private static String formatarData(String numeros) {

			StringBuilder sb =
					new StringBuilder();

			for (int i = 0; i < numeros.length(); i++) {

				if (i == 2 || i == 4) {
					sb.append("/");
				}

				sb.append(numeros.charAt(i));
			}

			return sb.toString();
		}
	}

	// ================= ABRIR ASSENTO =================

	private void abrirAssento() {

		assento telaAssento =
				new assento(
						filme,
						horarioSelecionado,
						tipoSelecionado,
						diaSelecionado
				);

		telaAssento.setVisible(true);

		dispose();
	}
}