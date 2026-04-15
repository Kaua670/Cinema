package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JTable;

public class Sessao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSessoes;
	private String filme;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Sessao frame = new Sessao("Batman");
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Sessao(String filme) {
		this.filme = filme;

		setTitle("Sessões");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);

		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		txtSessoes = new JTextField("Sessões");
		txtSessoes.setForeground(new Color(255, 255, 255));
		txtSessoes.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txtSessoes.setHorizontalAlignment(SwingConstants.CENTER);
		txtSessoes.setBounds(114, 11, 259, 43);
		txtSessoes.setEditable(false);
		txtSessoes.setOpaque(false);
		txtSessoes.setBorder(null);
		contentPane.add(txtSessoes);

		// ================= BOTÕES =================
		JButton btn14 = new JButton("14 Horas");
		btn14.setBounds(60, 80, 120, 30);
		btn14.addActionListener(e -> abrirAssento("14:00"));
		contentPane.add(btn14);

		JButton btn18 = new JButton("18 Horas");
		btn18.setBounds(300, 80, 120, 30);
		btn18.addActionListener(e -> abrirAssento("18:00"));
		contentPane.add(btn18);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(20, 260, 90, 25);
		btnVoltar.addActionListener(e -> {
			new Ingresso().setVisible(true);
			dispose();
		});
		contentPane.add(btnVoltar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\kaua62316556\\Documents\\Cinema\\images\\Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"));
		lblNewLabel.setBounds(1162, 130, 1598, 1342);
		contentPane.add(lblNewLabel);
		
		table = new JTable();
		table.setBackground(new Color(255, 128, 0));
		table.setBounds(103, 11, 275, 49);
		contentPane.add(table);
	}

	private void abrirAssento(String horario) {
		assento telaAssento = new assento(filme, horario);
		telaAssento.setVisible(true);
		dispose();
	}
}