package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Controle.ControlePreco;
import Controle.ControlerAssento;
import dao.FilmeDAO;
import dao.IngressoDAO;
import dao.AssentoDAO;

public class Administrador extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private JTextField campo2D;
	private JTextField campo3D;

	private JTextField campoFilme;
	private JTextField campoClass;
	private JTextField campoExcluirFilme;

	// NOVO CAMPO DATA
	private JTextField campoData;

	private String caminhoImagem = "";

	private JPanel painelAssentos;

	private JComboBox<String> comboFilme;
	private JComboBox<String> comboHorario;
	private JComboBox<String> comboTipo;

	private Set<String> assentosTemp =
			new HashSet<>();

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {

			try {

				new Administrador().setVisible(true);

			} catch (Exception e) {

				e.printStackTrace();
			}
		});
	}

	public Administrador() {

		setTitle("Painel Administrador");

		setExtendedState(JFrame.MAXIMIZED_BOTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		contentPane = new JPanel();

		contentPane.setLayout(null);

		setContentPane(contentPane);

		// ================= TÍTULO =================

		JLabel titulo =
				new JLabel("PAINEL ADMINISTRADOR");

		titulo.setBounds(650, 20, 600, 50);

		titulo.setForeground(Color.WHITE);

		titulo.setFont(
				new Font(
						"Verdana",
						Font.BOLD,
						28
				)
		);

		contentPane.add(titulo);

		// ================= CADASTRAR FILME =================

		JLabel tituloFilme =
				new JLabel("CADASTRAR FILME");

		tituloFilme.setBounds(100, 80, 300, 30);

		tituloFilme.setForeground(Color.WHITE);

		tituloFilme.setFont(
				new Font(
						"Verdana",
						Font.BOLD,
						18
				)
		);

		contentPane.add(tituloFilme);

		JLabel lblFilme =
				new JLabel("Nome do Filme:");

		lblFilme.setBounds(100, 120, 200, 30);

		lblFilme.setForeground(Color.WHITE);

		contentPane.add(lblFilme);

		campoFilme = new JTextField();

		campoFilme.setBounds(100, 150, 250, 40);

		contentPane.add(campoFilme);

		JLabel lblClass =
				new JLabel("Classificação:");

		lblClass.setBounds(100, 200, 200, 30);

		lblClass.setForeground(Color.WHITE);

		contentPane.add(lblClass);

		campoClass = new JTextField();

		campoClass.setBounds(100, 230, 250, 40);

		contentPane.add(campoClass);

		JButton btnImagem =
				new JButton("Escolher Imagem");

		btnImagem.setBounds(100, 290, 250, 40);

		contentPane.add(btnImagem);

		btnImagem.addActionListener(e -> {

			JFileChooser chooser =
					new JFileChooser();

			int res =
					chooser.showOpenDialog(null);

			if (res == JFileChooser.APPROVE_OPTION) {

				caminhoImagem =
						chooser.getSelectedFile()
								.getAbsolutePath();

				JOptionPane.showMessageDialog(
						null,
						"Imagem selecionada!"
				);
			}
		});

		JButton btnCadastrarFilme =
				new JButton("Cadastrar Filme");

		btnCadastrarFilme.setBounds(100, 350, 250, 50);

		contentPane.add(btnCadastrarFilme);

		btnCadastrarFilme.addActionListener(e -> {

			String nome =
					campoFilme.getText().trim();

			String classificacao =
					campoClass.getText().trim();

			if (
					nome.isEmpty()
					|| classificacao.isEmpty()
					|| caminhoImagem.isEmpty()
			) {

				JOptionPane.showMessageDialog(
						null,
						"Preencha todos os campos!"
				);

				return;
			}

			FilmeDAO.adicionarFilme(
					nome,
					classificacao,
					caminhoImagem
			);

			JOptionPane.showMessageDialog(
					null,
					"Filme cadastrado!"
			);

			campoFilme.setText("");

			campoClass.setText("");

			caminhoImagem = "";

			atualizarFilmes();
		});

		// ================= EXCLUIR FILME =================

		JLabel lblExcluir =
				new JLabel("Excluir Filme:");

		lblExcluir.setBounds(100, 430, 200, 30);

		lblExcluir.setForeground(Color.WHITE);

		contentPane.add(lblExcluir);

		campoExcluirFilme =
				new JTextField();

		campoExcluirFilme.setBounds(100, 460, 250, 40);

		contentPane.add(campoExcluirFilme);

		JButton btnExcluir =
				new JButton("Excluir Filme");

		btnExcluir.setBounds(100, 520, 250, 50);

		contentPane.add(btnExcluir);

		btnExcluir.addActionListener(e -> {

			String nome =
					campoExcluirFilme.getText().trim();

			if (nome.isEmpty()) {

				JOptionPane.showMessageDialog(
						null,
						"Digite um filme!"
				);

				return;
			}

			if (FilmeDAO.excluirFilme(nome)) {

				JOptionPane.showMessageDialog(
						null,
						"Filme excluído!"
				);

				campoExcluirFilme.setText("");

				atualizarFilmes();

			} else {

				JOptionPane.showMessageDialog(
						null,
						"Filme não encontrado!"
				);
			}
		});

		// ================= PREÇOS =================

		JLabel lbl2D =
				new JLabel("PREÇO 2D:");

		lbl2D.setBounds(500, 90, 150, 30);

		lbl2D.setForeground(Color.WHITE);

		contentPane.add(lbl2D);

		campo2D =
				new JTextField(
						String.valueOf(
								ControlePreco.preco2D
						)
				);

		campo2D.setBounds(500, 120, 150, 40);

		contentPane.add(campo2D);

		JLabel lbl3D =
				new JLabel("PREÇO 3D:");

		lbl3D.setBounds(700, 90, 150, 30);

		lbl3D.setForeground(Color.WHITE);

		contentPane.add(lbl3D);

		campo3D =
				new JTextField(
						String.valueOf(
								ControlePreco.preco3D
						)
				);

		campo3D.setBounds(700, 120, 150, 40);

		contentPane.add(campo3D);

		// ================= FILME =================

		JLabel lblFilmeSessao =
				new JLabel("Filme:");

		lblFilmeSessao.setBounds(500, 200, 100, 30);

		lblFilmeSessao.setForeground(Color.WHITE);

		contentPane.add(lblFilmeSessao);

		comboFilme =
				new JComboBox<>();

		comboFilme.setBounds(560, 200, 220, 35);

		contentPane.add(comboFilme);

		atualizarFilmes();

		// ================= DATA =================

		JLabel lblData =
				new JLabel("Data:");

		lblData.setBounds(820, 200, 100, 30);

		lblData.setForeground(Color.WHITE);

		contentPane.add(lblData);

		campoData =
				new JTextField();

		campoData.setBounds(870, 200, 120, 35);

		campoData.setToolTipText(
				"Ex: 25/12/2026"
		);

		contentPane.add(campoData);

		// ================= HORÁRIO =================

		JLabel lblHorario =
				new JLabel("Sessão:");

		lblHorario.setBounds(1020, 200, 100, 30);

		lblHorario.setForeground(Color.WHITE);

		contentPane.add(lblHorario);

		String[] horarios = {
				"14:00",
				"18:00"
		};

		comboHorario =
				new JComboBox<>(horarios);

		comboHorario.setBounds(1090, 200, 120, 35);

		contentPane.add(comboHorario);

		// ================= TIPO =================

		JLabel lblTipo =
				new JLabel("Tipo:");

		lblTipo.setBounds(1240, 200, 100, 30);

		lblTipo.setForeground(Color.WHITE);

		contentPane.add(lblTipo);

		String[] tipos = {
				"2D",
				"3D"
		};

		comboTipo =
				new JComboBox<>(tipos);

		comboTipo.setBounds(1290, 200, 100, 35);

		contentPane.add(comboTipo);

		// ================= BOTÃO CARREGAR =================

		JButton btnCarregar =
				new JButton("Carregar Assentos");

		btnCarregar.setBounds(1420, 200, 180, 35);

		contentPane.add(btnCarregar);

		btnCarregar.addActionListener(e -> {

			carregarAssentos();
		});

		// ================= PAINEL ASSENTOS =================

		painelAssentos =
				new JPanel();

		painelAssentos.setLayout(
				new GridLayout(4, 5, 10, 10)
		);

		painelAssentos.setBounds(500, 280, 900, 250);

		painelAssentos.setOpaque(false);

		contentPane.add(painelAssentos);

		// ================= SALVAR =================

		JButton btnSalvar =
				new JButton("Salvar");

		btnSalvar.setBounds(550, 570, 180, 50);

		contentPane.add(btnSalvar);

		btnSalvar.addActionListener(e -> {

			try {

				ControlePreco.preco2D =
						Double.parseDouble(
								campo2D.getText()
						);

				ControlePreco.preco3D =
						Double.parseDouble(
								campo3D.getText()
						);

				ControlerAssento.assentosBloqueados.clear();

				ControlerAssento.assentosBloqueados.addAll(
						assentosTemp
				);

				JOptionPane.showMessageDialog(
						null,
						"Alterações salvas!"
				);

			} catch (Exception ex) {

				JOptionPane.showMessageDialog(
						null,
						"Erro!"
				);
			}
		});

		// ================= RELATÓRIO =================

		JButton btnRelatorio =
				new JButton("Relatório");

		btnRelatorio.setBounds(770, 570, 180, 50);

		contentPane.add(btnRelatorio);

		btnRelatorio.addActionListener(e -> {

			List<String> relatorio =
					IngressoDAO.listarRelatorio();

			if (relatorio.isEmpty()) {

				JOptionPane.showMessageDialog(
						null,
						"Nenhuma compra encontrada!"
				);

				return;
			}

			DefaultListModel<String> model =
					new DefaultListModel<>();

			for (String r : relatorio) {

				model.addElement(r);

				model.addElement(
						"======================="
				);
			}

			JList<String> lista =
					new JList<>(model);

			JScrollPane scroll =
					new JScrollPane(lista);

			scroll.setPreferredSize(
					new Dimension(700, 400)
			);

			JOptionPane.showMessageDialog(
					null,
					scroll,
					"Relatório de Compras",
					JOptionPane.INFORMATION_MESSAGE
			);
		});

		// ================= VOLTAR =================

		JButton btnVoltar =
				new JButton("Voltar");

		btnVoltar.setBounds(990, 570, 180, 50);

		contentPane.add(btnVoltar);

		btnVoltar.addActionListener(e -> {

			new telalogin().setVisible(true);

			dispose();
		});

		// ================= FUNDO =================

		JLabel fundo =
				new JLabel();

		fundo.setIcon(
				new ImageIcon(
						"images/galaxy_2560x1250.png"
				)
		);

		fundo.setBounds(-20, 0, 2560, 1250);

		contentPane.add(fundo);

		contentPane.setComponentZOrder(
				fundo,
				contentPane.getComponentCount() - 1
		);
	}

	// ================= ATUALIZAR FILMES =================

	private void atualizarFilmes() {

		comboFilme.removeAllItems();

		List<String[]> filmes =
				FilmeDAO.listarFilmes();

		for (String[] filme : filmes) {

			comboFilme.addItem(
					filme[0]
			);
		}
	}

	// ================= CARREGAR ASSENTOS =================

	private void carregarAssentos() {

		painelAssentos.removeAll();

		// VERIFICA FILME
		if (comboFilme.getSelectedItem() == null) {

			JOptionPane.showMessageDialog(
					null,
					"Nenhum filme cadastrado!"
			);

			return;
		}

		// VERIFICA DATA
		if (campoData.getText().trim().isEmpty()) {

			JOptionPane.showMessageDialog(
					null,
					"Digite uma data!"
			);

			return;
		}

		String filme =
				comboFilme.getSelectedItem().toString();

		String horario =
				comboHorario.getSelectedItem().toString();

		String tipo =
				comboTipo.getSelectedItem().toString();

		// NOVA DATA
		String data =
				campoData.getText().trim();

		String[] assentos = {

				"A1","A2","A3","A4","A5",

				"B1","B2","B3","B4","B5",

				"C1","C2","C3","C4","C5",

				"D1","D2","D3","D4","D5"
		};

		for (String a : assentos) {

			JButton btn =
					new JButton(a);

			btn.setForeground(Color.WHITE);

			// ================= ASSENTO COMPRADO =================
			boolean ocupado =
					IngressoDAO.assentoOcupado(
							filme,
							horario,
							tipo,
							data,
							a
					);

			// ================= ASSENTO BLOQUEADO =================
			boolean bloqueado =
					AssentoDAO.assentoOcupado(
							filme,
							horario,
							tipo,
							data,
							a
					);

			// ================= STATUS BOTÃO =================
			if (ocupado) {

				btn.setBackground(
						new Color(120,0,0)
				);

				btn.setForeground(Color.WHITE);

				btn.setText(a + " ✓");

				btn.setEnabled(true);
			}

			// ================= BLOQUEADO =================
			else if (bloqueado) {

				btn.setBackground(Color.RED);

				btn.setForeground(Color.WHITE);

				btn.setEnabled(true);
			}

			// ================= LIVRE =================
			else {

				btn.setBackground(Color.GRAY);

				btn.setForeground(Color.WHITE);

				btn.setEnabled(true);
			}

			// ================= CLICK ASSENTO =================
			btn.addActionListener(e -> {

			    // ================= ASSENTO COMPRADO =================
			    if (
			            IngressoDAO.assentoOcupado(
			                    filme,
			                    horario,
			                    tipo,
			                    data,
			                    a
			            )
			    ) {

			        int op =
			                JOptionPane.showConfirmDialog(
			                        null,
			                        "Deseja desbloquear esse assento comprado?"
			                );

			        if (op == JOptionPane.YES_OPTION) {

			            // remove ingresso
			            IngressoDAO.removerIngresso(
			                    filme,
			                    horario,
			                    tipo,
			                    data,
			                    a
			            );

			            // remove bloqueio
			            AssentoDAO.removerAssento(
			                    filme,
			                    horario,
			                    tipo,
			                    data,
			                    a
			            );

			            btn.setBackground(Color.GRAY);

			            btn.setText(a);

			            JOptionPane.showMessageDialog(
			                    null,
			                    "Assento desbloqueado!"
			            );
			        }

			        return;
			    }

			    // ================= DESBLOQUEAR =================
			    if (
			            AssentoDAO.assentoOcupado(
			                    filme,
			                    horario,
			                    tipo,
			                    data,
			                    a
			            )
			    ) {

			        AssentoDAO.removerAssento(
			                filme,
			                horario,
			                tipo,
			                data,
			                a
			        );

			        btn.setBackground(Color.GRAY);

			        JOptionPane.showMessageDialog(
			                null,
			                "Assento desbloqueado!"
			        );
			    }

			    // ================= BLOQUEAR =================
			    else {

			        AssentoDAO.salvarAssento(
			                filme,
			                horario,
			                tipo,
			                data,
			                a
			        );

			        btn.setBackground(Color.RED);

			        JOptionPane.showMessageDialog(
			                null,
			                "Assento bloqueado!"
			        );
			    }
			});

			painelAssentos.add(btn);
		}

		// ================= ATUALIZA TELA =================
		painelAssentos.repaint();

		painelAssentos.revalidate();
	}
}