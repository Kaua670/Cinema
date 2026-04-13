package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Sessao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSessoes;
	private String filme;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sessao frame = new Sessao("Batman");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Sessao(String filme) {
		this.filme = filme;

		setTitle("Sessões");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 340);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtSessoes = new JTextField();
		txtSessoes.setFont(new Font("Agency FB", Font.PLAIN, 22));
		txtSessoes.setHorizontalAlignment(SwingConstants.CENTER);
		txtSessoes.setText("Sessões - " + filme);
		txtSessoes.setBounds(120, 15, 240, 35);
		txtSessoes.setEditable(false);
		contentPane.add(txtSessoes);

		JButton btn14 = new JButton("14 Horas");
		btn14.setBounds(60, 80, 120, 30);
		btn14.addActionListener(e -> abrirAssento("14:00"));
		contentPane.add(btn14);

		JButton btn18 = new JButton("18 Horas");
		btn18.setBounds(300, 80, 120, 30);
		btn18.addActionListener(e -> abrirAssento("18:00"));
		contentPane.add(btn18);

		JButton btn20 = new JButton("20 Horas");
		btn20.setBounds(180, 120, 120, 30);
		btn20.addActionListener(e -> abrirAssento("20:00"));
		contentPane.add(btn20);

		JLabel lblImagem = new JLabel("");
		lblImagem.setBounds(160, 170, 160, 100);

		if (filme.equalsIgnoreCase("Vingadores")) {
			lblImagem.setIcon(new ImageIcon("images/Vingadores.jpg"));
		} else if (filme.equalsIgnoreCase("Batman")) {
			lblImagem.setIcon(new ImageIcon("images/Batman.jpg"));
		} else if (filme.equalsIgnoreCase("Toy Story")) {
			lblImagem.setIcon(new ImageIcon("images/ToyStory.jpg"));
		}

		contentPane.add(lblImagem);

		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(20, 260, 90, 25);
		btnVoltar.addActionListener(e -> {
			Ingresso telaIngresso = new Ingresso();
			telaIngresso.setVisible(true);
			dispose();
		});
		contentPane.add(btnVoltar);
	}

	private void abrirAssento(String horario) {
		assento telaAssento = new assento(filme, horario);
		telaAssento.setVisible(true);
		dispose();
	}
}