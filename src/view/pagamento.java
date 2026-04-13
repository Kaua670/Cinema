package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class pagamento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String filme;
	private String horario;
	private String assento;
	private JTextField txtValor;
	private JComboBox<String> comboPagamento;
	private JComboBox<String> comboTipoCartao;
	private JComboBox<String> comboParcelas;

	public pagamento(String filme, String horario, String assento) {
		this.filme = filme;
		this.horario = horario;
		this.assento = assento;

		setTitle("Pagamento");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 420);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);

		JLabel lblTitulo = new JLabel("Pagamento");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblTitulo.setBounds(180, 20, 150, 30);
		contentPane.add(lblTitulo);

		JLabel lblFilme = new JLabel("Filme: " + filme);
		lblFilme.setForeground(Color.WHITE);
		lblFilme.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFilme.setBounds(50, 80, 300, 25);
		contentPane.add(lblFilme);

		JLabel lblSessao = new JLabel("Sessão: " + horario);
		lblSessao.setForeground(Color.WHITE);
		lblSessao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSessao.setBounds(50, 110, 300, 25);
		contentPane.add(lblSessao);

		JLabel lblAssento = new JLabel("Assento: " + assento);
		lblAssento.setForeground(Color.WHITE);
		lblAssento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAssento.setBounds(50, 140, 300, 25);
		contentPane.add(lblAssento);

		JLabel lblValor = new JLabel("Valor:");
		lblValor.setForeground(Color.WHITE);
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(50, 180, 80, 25);
		contentPane.add(lblValor);

		txtValor = new JTextField("25.00");
		txtValor.setBounds(180, 180, 140, 25);
		contentPane.add(txtValor);

		JLabel lblForma = new JLabel("Forma de pagamento:");
		lblForma.setForeground(Color.WHITE);
		lblForma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblForma.setBounds(50, 220, 160, 25);
		contentPane.add(lblForma);

		comboPagamento = new JComboBox<String>();
		comboPagamento.addItem("Dinheiro");
		comboPagamento.addItem("Cartão");
		comboPagamento.addItem("PIX");
		comboPagamento.setBounds(220, 220, 140, 25);
		contentPane.add(comboPagamento);

		JLabel lblTipoCartao = new JLabel("Tipo do cartão:");
		lblTipoCartao.setForeground(Color.WHITE);
		lblTipoCartao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTipoCartao.setBounds(50, 260, 140, 25);
		contentPane.add(lblTipoCartao);

		comboTipoCartao = new JComboBox<String>();
		comboTipoCartao.addItem("Débito");
		comboTipoCartao.addItem("Crédito");
		comboTipoCartao.setBounds(220, 260, 140, 25);
		comboTipoCartao.setEnabled(false);
		contentPane.add(comboTipoCartao);

		JLabel lblParcelas = new JLabel("Parcelas:");
		lblParcelas.setForeground(Color.WHITE);
		lblParcelas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblParcelas.setBounds(50, 300, 140, 25);
		contentPane.add(lblParcelas);

		comboParcelas = new JComboBox<String>();
		comboParcelas.addItem("1x sem juros");
		comboParcelas.addItem("2x sem juros");
		comboParcelas.addItem("3x sem juros");
		comboParcelas.addItem("4x sem juros");
		comboParcelas.addItem("5x sem juros");
		comboParcelas.addItem("6x sem juros");
		comboParcelas.setBounds(220, 300, 140, 25);
		comboParcelas.setEnabled(false);
		contentPane.add(comboParcelas);

		comboPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String forma = comboPagamento.getSelectedItem().toString();

				if (forma.equals("Cartão")) {
					comboTipoCartao.setEnabled(true);

					String tipo = comboTipoCartao.getSelectedItem().toString();
					if (tipo.equals("Crédito")) {
						comboParcelas.setEnabled(true);
					} else {
						comboParcelas.setEnabled(false);
					}
				} else {
					comboTipoCartao.setEnabled(false);
					comboParcelas.setEnabled(false);
				}
			}
		});

		comboTipoCartao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo = comboTipoCartao.getSelectedItem().toString();
				String forma = comboPagamento.getSelectedItem().toString();

				if (forma.equals("Cartão") && tipo.equals("Crédito")) {
					comboParcelas.setEnabled(true);
				} else {
					comboParcelas.setEnabled(false);
				}
			}
		});

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(90, 345, 120, 30);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				assento telaAssento = new assento(filme, horario);
				telaAssento.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVoltar);

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.setBounds(260, 345, 120, 30);
		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				finalizarPagamento();
			}
		});
		contentPane.add(btnFinalizar);
	}

	private void finalizarPagamento() {
		String valor = txtValor.getText().trim();
		String formaPagamento = comboPagamento.getSelectedItem().toString();

		if (valor.equals("")) {
			JOptionPane.showMessageDialog(this, "Informe o valor do ingresso.");
			return;
		}

		String detalhes = "";

		if (formaPagamento.equals("Cartão")) {
			String tipo = comboTipoCartao.getSelectedItem().toString();

			if (tipo.equals("Crédito")) {
				String parcelas = comboParcelas.getSelectedItem().toString();
				detalhes = "\nTipo do cartão: Crédito"
						+ "\nParcelamento: " + parcelas;
			} else {
				detalhes = "\nTipo do cartão: Débito";
			}
		}

		JOptionPane.showMessageDialog(this,
			"Pagamento realizado com sucesso!"
			+ "\n\nFilme: " + filme
			+ "\nSessão: " + horario
			+ "\nAssento: " + assento
			+ "\nValor: R$ " + valor
			+ "\nForma de pagamento: " + formaPagamento
			+ detalhes);

		dispose();
	}
}