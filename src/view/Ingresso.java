package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Cursor;

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
		lblBatman.setForeground(new Color(255, 255, 255));
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
		
		JTextPane txtpnClassificao = new JTextPane();
		txtpnClassificao.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnClassificao.setForeground(new Color(255, 255, 255));
		txtpnClassificao.setText("Classificação\r\n\r\n");
		txtpnClassificao.setBounds(630, 632, 94, 20);
		txtpnClassificao.setOpaque(false);
		txtpnClassificao.setBorder(null);
		contentPane.add(txtpnClassificao);
		
		JTextPane txtpnClassificao_2 = new JTextPane();
		txtpnClassificao_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpnClassificao_2.setText("12+");
		txtpnClassificao_2.setOpaque(false);
		txtpnClassificao_2.setForeground(new Color(0, 0, 0));
		txtpnClassificao_2.setBorder(null);
		txtpnClassificao_2.setBounds(726, 632, 56, 20);
		contentPane.add(txtpnClassificao_2);
		
		table = new JTable();
		table.setBackground(new Color(255, 255, 0));
		table.setBounds(720, 632, 46, 20);
		contentPane.add(table);
		
		JTextPane txtpnClassificao_1 = new JTextPane();
		txtpnClassificao_1.setText("Classificação\r\n\r\n");
		txtpnClassificao_1.setOpaque(false);
		txtpnClassificao_1.setForeground(Color.WHITE);
		txtpnClassificao_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnClassificao_1.setBorder(null);
		txtpnClassificao_1.setBounds(960, 632, 94, 20);
		contentPane.add(txtpnClassificao_1);
		
		JTextPane txtpnClassificao_2_1 = new JTextPane();
		txtpnClassificao_2_1.setText("  14+");
		txtpnClassificao_2_1.setOpaque(false);
		txtpnClassificao_2_1.setForeground(Color.BLACK);
		txtpnClassificao_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		txtpnClassificao_2_1.setBorder(null);
		txtpnClassificao_2_1.setBounds(1054, 632, 46, 20);
		contentPane.add(txtpnClassificao_2_1);
		
		table_1 = new JTable();
		table_1.setBackground(new Color(255, 128, 0));
		table_1.setBounds(1060, 632, 40, 20);
		contentPane.add(table_1);
		
		JTextPane txtpnMin = new JTextPane();
		txtpnMin.setText("143 min\r\n\r\n");
		txtpnMin.setOpaque(false);
		txtpnMin.setForeground(Color.WHITE);
		txtpnMin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnMin.setBorder(null);
		txtpnMin.setBounds(659, 663, 94, 20);
		contentPane.add(txtpnMin);
		
		JTextPane txtpnMin_2 = new JTextPane();
		txtpnMin_2.setText("152 min\r\n\r\n");
		txtpnMin_2.setOpaque(false);
		txtpnMin_2.setForeground(Color.WHITE);
		txtpnMin_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtpnMin_2.setBorder(null);
		txtpnMin_2.setBounds(997, 663, 94, 20);
		contentPane.add(txtpnMin_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images/Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"));
		lblNewLabel.setBounds(1091, 498, 759, 597);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("images/Seleção_de_filme_em_destaque-removebg-preview.png"));
		lblNewLabel_1.setBounds(567, 244, 590, 62);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("images/galaxy_2560x1250.png"));
		lblNewLabel_2.setBounds(-23, 0, 2560, 1250);
		contentPane.add(lblNewLabel_2);

		ImageIcon icon = new ImageIcon("images/Classificação_Indicativa_12_anos.svg.png");
		Image img = icon.getImage().getScaledInstance(306, 279, Image.SCALE_SMOOTH);
	}
}