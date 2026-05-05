package view;

import java.awt.EventQueue;
import javax.swing.*;

import Controle.ControlePreco;
import Controle.ControlerAssento;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

public class Administrador extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Administrador frame = new Administrador();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Administrador() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);

		// 🔥 LISTA TEMPORÁRIA
		Set<String> assentosTemp = new HashSet<>(ControlerAssento.assentosBloqueados);

		// LABELS
		JLabel lbl1 = new JLabel("PREÇO 2D:");
		lbl1.setFont(new Font("Verdana", Font.BOLD, 22));
		lbl1.setForeground(Color.WHITE);
		lbl1.setBounds(699, 185, 172, 35);
		contentPane.add(lbl1);

		JLabel lbl2 = new JLabel("PREÇO 3D:");
		lbl2.setFont(new Font("Verdana", Font.BOLD, 22));
		lbl2.setForeground(Color.WHITE);
		lbl2.setBounds(964, 185, 172, 35);
		contentPane.add(lbl2);

		// CAMPOS COM VALORES ATUAIS
		textField = new JTextField(String.valueOf(ControlePreco.preco2D));
		textField.setBounds(671, 219, 200, 34);
		contentPane.add(textField);

		textField_1 = new JTextField(String.valueOf(ControlePreco.preco3D));
		textField_1.setBounds(929, 219, 200, 34);
		contentPane.add(textField_1);

		// PAINEL ASSENTOS
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

			JButton btn = new JButton(nomeAssento);
			btn.setFocusPainted(false);
			btn.setForeground(Color.WHITE);

			if (assentosTemp.contains(nomeAssento)) {
				btn.setBackground(Color.RED);
			} else {
				btn.setBackground(Color.GRAY);
			}

			btn.addActionListener(e -> {
				if (assentosTemp.contains(nomeAssento)) {
					assentosTemp.remove(nomeAssento);
					btn.setBackground(Color.GRAY);
				} else {
					assentosTemp.add(nomeAssento);
					btn.setBackground(Color.RED);
				}
			});

			painelAssentos.add(btn);
		}

		// 🔥 BOTÃO SALVAR (ASSENTOS + PREÇOS)
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.setBounds(671, 577, 200, 50);

		btnSalvar.addActionListener(e -> {

			// salva assentos
			ControlerAssento.assentosBloqueados.clear();
			ControlerAssento.assentosBloqueados.addAll(assentosTemp);

			try {
				// salva preços
				double preco2D = Double.parseDouble(textField.getText());
				double preco3D = Double.parseDouble(textField_1.getText());

				ControlePreco.preco2D = preco2D;
				ControlePreco.preco3D = preco3D;

				JOptionPane.showMessageDialog(null, "Dados atualizados!");

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "Digite preços válidos!");
			}
		});

		contentPane.add(btnSalvar);

		// VOLTAR
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(929, 577, 200, 50);
		btnVoltar.addActionListener(e -> {
			new telalogin().setVisible(true);
			dispose();
		});
		contentPane.add(btnVoltar);
	}
}