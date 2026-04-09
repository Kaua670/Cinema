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
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ingresso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel modeloTabela;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public Ingresso() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(138, 11, 500, 500);
		lblNewLabel.setIcon(new ImageIcon("images/image-removebg-preview.png"));
		contentPane.add(lblNewLabel);
		
		table = new JTable();
		
		modeloTabela = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        modeloTabela.addColumn("ID"); 
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Classificação");

        table = new JTable(modeloTabela);
        
     // esconder ID
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(32, 23, 368, 174);
		contentPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		
		JButton btnNewButton = new JButton("Click");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 int linha = table.getSelectedRow();
				 
				 if (linha == -1) {
			            JOptionPane.showMessageDialog(btnNewButton, "Selecione uma filme!");
			            return;
			     }
				 
				 // Sessão
				 
				 
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(128, 128, 0));
		btnNewButton.setBounds(59, 227, 89, 23);
		contentPane.add(btnNewButton);
		

		


	}
}
