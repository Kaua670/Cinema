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

public class Sessao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private String filme;

	// NOVAS VARIÁVEIS
	private String horarioSelecionado;
	private String tipoSelecionado;

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

		// ================= BOTÕES DE HORÁRIO =================
		JButton btn14 = new JButton("14 Horas");
		btn14.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn14.setBounds(742, 407, 138, 50);
		contentPane.add(btn14);

		JButton btn18 = new JButton("18 Horas");
		btn18.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn18.setBounds(1053, 407, 138, 50);
		contentPane.add(btn18);

		// ================= BOTÕES 2D / 3D =================
		JButton btn2D = new JButton("2D");
		btn2D.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn2D.setBounds(742, 480, 138, 50);
		btn2D.setVisible(false);
		contentPane.add(btn2D);

		JButton btn3D = new JButton("3D");
		btn3D.setFont(new Font("Tahoma", Font.BOLD, 11));
		btn3D.setBounds(1053, 480, 138, 50);
		btn3D.setVisible(false);
		contentPane.add(btn3D);

		// ================= AÇÕES DOS HORÁRIOS =================
		btn14.addActionListener(e -> {
			horarioSelecionado = "14:00";
			btn2D.setVisible(true);
			btn3D.setVisible(true);
		});

		btn18.addActionListener(e -> {
			horarioSelecionado = "18:00";
			btn2D.setVisible(true);
			btn3D.setVisible(true);
		});

		// ================= AÇÕES 2D / 3D =================
		btn2D.addActionListener(e -> {
			tipoSelecionado = "2D";
			abrirAssento();
		});

		btn3D.addActionListener(e -> {
			tipoSelecionado = "3D";
			abrirAssento();
		});

		// ================= BOTÃO VOLTAR =================
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnVoltar.setBounds(896, 550, 143, 57);
		btnVoltar.addActionListener(e -> {
			new Ingresso().setVisible(true);
			dispose();
		});
		contentPane.add(btnVoltar);

		// ================= IMAGENS =================
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("images/Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"));
		lblNewLabel.setBounds(1162, 130, 1598, 1342);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("images/image-removebg-preview (3).png"));
		lblNewLabel_1.setBounds(655, 152, 1279, 205);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("images/galaxy_2560x1250.png"));
		lblNewLabel_2.setBounds(-22, -45, 2964, 1326);
		contentPane.add(lblNewLabel_2);
	}

	// ================= MÉTODO ATUALIZADO =================
	private void abrirAssento() {
		assento telaAssento = new assento(filme, horarioSelecionado, tipoSelecionado);
		telaAssento.setVisible(true);
		dispose();
	}
}