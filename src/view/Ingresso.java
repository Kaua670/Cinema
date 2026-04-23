package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextPane;

public class Ingresso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Ingresso frame = new Ingresso();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Ingresso() {
		setTitle("Filmes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920,1080);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 14, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 🔙 BOTÃO VOLTAR
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(798, 707, 142, 59);
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnVoltar.setOpaque(false);
		btnVoltar.setBorder(null);
		btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		contentPane.add(btnVoltar);

		btnVoltar.addActionListener(e -> {
			new telalogin().setVisible(true);
			dispose();
		});

		// ================== TABELA ==================
		DefaultTableModel modeloTabela = new DefaultTableModel(
			new Object[][] {
				{"Batman", "12 anos"},
				{"Vingadores", "14 anos"},
			},
			new String[] {
				"Filme", "Classificação"
			}
		) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// ================== IMAGEM BATMAN ==================
		JLabel lblBatman = new JLabel();
		lblBatman.setBounds(950, 401, 150, 220);
		lblBatman.setCursor(new Cursor(Cursor.HAND_CURSOR));

		ImageIcon batmanIcon = new ImageIcon("images/Batman.jpg");
		Image batmanImg = batmanIcon.getImage().getScaledInstance(150, 220, Image.SCALE_SMOOTH);
		lblBatman.setIcon(new ImageIcon(batmanImg));

		lblBatman.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Sessao telaSessao = new Sessao("Batman");
				telaSessao.setVisible(true);
				dispose();
			}
		});

		contentPane.add(lblBatman);

		// ================== IMAGEM VINGADORES ==================
		JLabel lblVingadores = new JLabel();
		lblVingadores.setBounds(616, 401, 150, 220);
		lblVingadores.setCursor(new Cursor(Cursor.HAND_CURSOR));

		ImageIcon vingadoresIcon = new ImageIcon("images/Vingadores.jpg");
		Image vingadoresImg = vingadoresIcon.getImage().getScaledInstance(150, 220, Image.SCALE_SMOOTH);
		lblVingadores.setIcon(new ImageIcon(vingadoresImg));

		lblVingadores.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				Sessao telaSessao = new Sessao("Vingadores");
				telaSessao.setVisible(true);
				dispose();
			}
		});

		contentPane.add(lblVingadores);

		// TEXTOS
		JTextPane txtpnClassificacao = new JTextPane();
		txtpnClassificacao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnClassificacao.setForeground(Color.WHITE);
		txtpnClassificacao.setText("Classificação");
		txtpnClassificacao.setBounds(630, 632, 120, 20);
		txtpnClassificacao.setOpaque(false);
		txtpnClassificacao.setBorder(null);
		contentPane.add(txtpnClassificacao);

		JTextPane txtpn12 = new JTextPane();
		txtpn12.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpn12.setText("12+");
		txtpn12.setOpaque(false);
		txtpn12.setForeground(Color.BLACK);
		txtpn12.setBorder(null);
		txtpn12.setBounds(726, 632, 56, 20);
		contentPane.add(txtpn12);

		table = new JTable();
		table.setBackground(Color.YELLOW);
		table.setBounds(720, 632, 46, 20);
		contentPane.add(table);

		JTextPane txtpnClassificacao2 = new JTextPane();
		txtpnClassificacao2.setText("Classificação");
		txtpnClassificacao2.setOpaque(false);
		txtpnClassificacao2.setForeground(Color.WHITE);
		txtpnClassificacao2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnClassificacao2.setBorder(null);
		txtpnClassificacao2.setBounds(960, 632, 120, 20);
		contentPane.add(txtpnClassificacao2);

		JTextPane txtpn14 = new JTextPane();
		txtpn14.setText("14+");
		txtpn14.setOpaque(false);
		txtpn14.setForeground(Color.BLACK);
		txtpn14.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpn14.setBorder(null);
		txtpn14.setBounds(1054, 632, 56, 20);
		contentPane.add(txtpn14);

		table_1 = new JTable();
		table_1.setBackground(Color.ORANGE);
		table_1.setBounds(1060, 632, 40, 20);
		contentPane.add(table_1);

		JTextPane txtpnMin = new JTextPane();
		txtpnMin.setText("143 min");
		txtpnMin.setOpaque(false);
		txtpnMin.setForeground(Color.WHITE);
		txtpnMin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnMin.setBorder(null);
		txtpnMin.setBounds(659, 663, 94, 20);
		contentPane.add(txtpnMin);

		JTextPane txtpnMin2 = new JTextPane();
		txtpnMin2.setText("152 min");
		txtpnMin2.setOpaque(false);
		txtpnMin2.setForeground(Color.WHITE);
		txtpnMin2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnMin2.setBorder(null);
		txtpnMin2.setBounds(997, 663, 94, 20);
		contentPane.add(txtpnMin2);

		JLabel fundo = new JLabel("");
		fundo.setIcon(new ImageIcon("images/galaxy_2560x1250.png"));
		fundo.setBounds(-23, 0, 2560, 1250);
		contentPane.add(fundo);
	}
}