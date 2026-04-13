package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ingresso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ingresso frame = new Ingresso();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ingresso() {
		setTitle("Filmes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 320);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 14, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel titulo = new JLabel("SELECIONE O FILME");
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		titulo.setBounds(130, 10, 220, 30);
		contentPane.add(titulo);

		DefaultTableModel modeloTabela = new DefaultTableModel(
			new Object[][] {
				{"Batman", "12 anos"},
				{"Vingadores", "14 anos"},
				
			},
			new String[] {
				"Filme", "Classificação"
			}
		) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		table = new JTable(modeloTabela);
		table.setRowHeight(25);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(50, 55, 380, 140);
		contentPane.add(scrollPane);

		JButton btnEscolher = new JButton("Escolher Sessão");
		btnEscolher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int linha = table.getSelectedRow();

				if (linha == -1) {
					JOptionPane.showMessageDialog(btnEscolher, "Selecione um filme!");
					return;
				}

				String filme = table.getValueAt(linha, 0).toString();

				Sessao telaSessao = new Sessao(filme);
				telaSessao.setVisible(true);
				dispose();
			}
		});
		btnEscolher.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEscolher.setForeground(Color.WHITE);
		btnEscolher.setBackground(new Color(128, 128, 0));
		btnEscolher.setBounds(160, 220, 160, 30);
		contentPane.add(btnEscolher);

		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("images/image-removebg-preview.png"));
		logo.setBounds(320, 180, 140, 90);
		contentPane.add(logo);
	}
}