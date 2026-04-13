package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

public class Sessao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtSesses;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sessao frame = new Sessao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sessao() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("14 Horas");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(42, 71, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("18 Horas");
		btnNewButton_1.setBounds(288, 71, 89, 23);
		contentPane.add(btnNewButton_1);
		
		txtSesses = new JTextField();
		txtSesses.setFont(new Font("Agency FB", Font.PLAIN, 18));
		txtSesses.setHorizontalAlignment(SwingConstants.CENTER);
		txtSesses.setText("Sessões");
		txtSesses.setBounds(146, 11, 143, 39);
		contentPane.add(txtSesses);
		txtSesses.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\daniela62241756\\Documents\\Cinema\\images\\Vingadores.jpg"));
		lblNewLabel.setBounds(25, 105, 143, 124);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\daniela62241756\\Documents\\Cinema\\images\\Batman.jpg"));
		lblNewLabel_1.setBounds(260, 105, 143, 129);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\daniela62241756\\Documents\\Cinema\\images\\image-removebg-preview.png"));
		lblNewLabel_2.setBounds(66, 0, 368, 265);
		contentPane.add(lblNewLabel_2);

	}
}
