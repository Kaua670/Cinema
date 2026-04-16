package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

public class assento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String filme;
	private String horario;
	private String tipo;

	private String assentoSelecionado = null; // 🔥 começa NULL
	private JButton assentoSelecionadoBtn = null;

	public assento(String filme, String horario, String tipoSelecionado) {
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

		JLabel lblInfo = new JLabel(
			"Filme: " + filme + 
			"   Sessão: " + horario + 
			"   Tipo: " + tipo
		);
		lblInfo.setForeground(Color.WHITE);
		lblInfo.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblInfo.setBounds(706, 267, 800, 63);
		contentPane.add(lblInfo);

		JPanel painelAssentos = new JPanel();
		painelAssentos.setBounds(464, 341, 861, 205);
		painelAssentos.setLayout(new GridLayout(4, 5, 10, 10));
		painelAssentos.setOpaque(false);
		contentPane.add(painelAssentos);

		String[] assentos = {
			"A1","A2","A3","A4","A5",
			"B1","B2","B3","B4","B5",
			"C1","C2","C3","C4","C5",
			"D1","D2","D3","D4","D5"
		};

		for (String nomeAssento : assentos) {

			JButton btnAssento = new JButton(nomeAssento);
			btnAssento.setBackground(Color.GRAY);
			btnAssento.setForeground(Color.BLACK);
			btnAssento.setFocusPainted(false);

			// Assentos ocupados
			if (nomeAssento.equals("B2") || nomeAssento.equals("C3")) {
				btnAssento.setBackground(Color.CYAN);
				btnAssento.setEnabled(false);
			}

			btnAssento.addActionListener(e -> {

				// 🔥 remove seleção anterior
				if (assentoSelecionadoBtn != null) {
					assentoSelecionadoBtn.setBackground(Color.GRAY);
				}

				// 🔥 nova seleção
				assentoSelecionadoBtn = btnAssento;
				assentoSelecionado = nomeAssento;

				btnAssento.setBackground(Color.ORANGE);
			});

			painelAssentos.add(btnAssento);
		}

		// 🔙 VOLTAR
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(912, 557, 179, 53);
		btnVoltar.addActionListener(e -> {
			Sessao telaSessao = new Sessao(filme);
			telaSessao.setVisible(true);
			dispose();
		});
		contentPane.add(btnVoltar);

		// ✅ CONFIRMAR COM VALIDAÇÃO
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(670, 557, 179, 53);
		btnConfirmar.addActionListener(e -> {

			// 🔥 OBRIGATÓRIO TER 1 ASSENTO
			if (assentoSelecionado == null) {
				JOptionPane.showMessageDialog(null, "Selecione um assento!");
				return;
			}

			// segue fluxo
			pagamento telaPagamento = new pagamento(filme, horario, assentoSelecionado, tipo);
			telaPagamento.setVisible(true);
			dispose();
		});
		contentPane.add(btnConfirmar);

		// IMAGENS
		JLabel lbl1 = new JLabel("");
		lbl1.setIcon(new ImageIcon("images/image-removebg-preview (4).png"));
		lbl1.setBounds(587, 150, 1279, 205);
		contentPane.add(lbl1);

		JLabel lbl2 = new JLabel("");
		lbl2.setIcon(new ImageIcon("images/Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"));
		lbl2.setBounds(922, 535, 445, 524);
		contentPane.add(lbl2);

		JLabel lbl3 = new JLabel("");
		lbl3.setIcon(new ImageIcon("images/galaxy_2560x1250.png"));
		lbl3.setBounds(-20, 0, 2560, 1250);
		contentPane.add(lbl3);
	}
}