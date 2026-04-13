package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class assento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String filme;
	private String horario;
	private String assentoSelecionado = "";
	private JButton assentoSelecionadoBtn = null;

	public assento(String filme, String horario) {
		this.filme = filme;
		this.horario = horario;

		setTitle("Assentos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);

		JLabel lblTitulo = new JLabel("Escolha seu assento");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setBounds(190, 10, 220, 25);
		contentPane.add(lblTitulo);

		JLabel lblInfo = new JLabel("Filme: " + filme + "   Sessão: " + horario);
		lblInfo.setForeground(Color.WHITE);
		lblInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInfo.setBounds(160, 40, 300, 25);
		contentPane.add(lblInfo);

		JLabel lblTela = new JLabel("TELA");
		lblTela.setForeground(Color.YELLOW);
		lblTela.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTela.setBounds(260, 70, 100, 25);
		contentPane.add(lblTela);

		JPanel painelAssentos = new JPanel();
		painelAssentos.setBounds(100, 110, 380, 160);
		painelAssentos.setLayout(new GridLayout(4, 5, 10, 10));
		painelAssentos.setBackground(Color.BLACK);
		contentPane.add(painelAssentos);

		String[] assentos = {
			"A1", "A2", "A3", "A4", "A5",
			"B1", "B2", "B3", "B4", "B5",
			"C1", "C2", "C3", "C4", "C5",
			"D1", "D2", "D3", "D4", "D5"
		};

		for (int i = 0; i < assentos.length; i++) {
			final String nomeAssento = assentos[i];

			JButton btnAssento = new JButton(nomeAssento);
			btnAssento.setBackground(Color.GREEN);
			btnAssento.setForeground(Color.BLACK);
			btnAssento.setFocusPainted(false);
			btnAssento.setBorderPainted(false);
			btnAssento.setOpaque(true);

			if (nomeAssento.equals("B2") || nomeAssento.equals("C3")) {
				btnAssento.setBackground(Color.RED);
				btnAssento.setForeground(Color.WHITE);
				btnAssento.setEnabled(false);
			}

			btnAssento.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (assentoSelecionadoBtn != null) {
						assentoSelecionadoBtn.setBackground(Color.GREEN);
						assentoSelecionadoBtn.setForeground(Color.BLACK);
					}

					assentoSelecionadoBtn = btnAssento;
					assentoSelecionado = nomeAssento;

					btnAssento.setBackground(Color.ORANGE);
					btnAssento.setForeground(Color.BLACK);
				}
			});

			painelAssentos.add(btnAssento);
		}

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(130, 300, 120, 30);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sessao telaSessao = new Sessao(filme);
				telaSessao.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVoltar);

		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(320, 300, 120, 30);
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (assentoSelecionado.equals("")) {
					JOptionPane.showMessageDialog(null, "Selecione um assento!");
				} else {
					pagamento telaPagamento = new pagamento(filme, horario, assentoSelecionado);
					telaPagamento.setVisible(true);
					dispose();
				}
			}
		});
		contentPane.add(btnConfirmar);
	}
}