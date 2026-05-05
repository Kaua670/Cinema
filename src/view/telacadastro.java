package view;

import javax.swing.*;
import java.awt.*;

public class telacadastro extends JFrame {

    private JLabel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private JPasswordField passwordConfirmField;

    private JButton btnCadastrar;
    private boolean modoRecuperacao = false;

    // 🟢 CONSTRUTOR NORMAL
    public telacadastro() {
    	setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1920, 1080);

        // 🔥 FUNDO COMO CONTAINER (CORRETO)
        JLabel fundo = new JLabel(new ImageIcon("images/galaxy_2560x1250.png"));
        fundo.setLayout(null);
        setContentPane(fundo);

        contentPane = fundo;

        // 👤 USUÁRIO
        textField = new JTextField();
        textField.setBounds(772, 368, 326, 40);
        contentPane.add(textField);

        // 🔒 SENHA
        passwordField = new JPasswordField();
        passwordField.setBounds(772, 455, 326, 40);
        contentPane.add(passwordField);

        // 🔘 BOTÃO
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(772, 506, 155, 49);
        contentPane.add(btnCadastrar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(937, 506, 161, 49);
        contentPane.add(btnVoltar);

        // 🔙 VOLTAR
        btnVoltar.addActionListener(e -> {
            new telalogin().setVisible(true);
            dispose();
        });

        // 🔥 AÇÃO PRINCIPAL
        btnCadastrar.addActionListener(e -> {

            String usuario = textField.getText();

            if (usuario == null || usuario.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite o usuário!");
                return;
            }

            usuario = usuario.trim();
            char[] senhaChars = passwordField.getPassword();

            // 🔥 RECUPERAÇÃO
            if (modoRecuperacao) {

                char[] confirmarChars = passwordConfirmField.getPassword();

                if (senhaChars.length == 0 || confirmarChars.length == 0) {
                    JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                    return;
                }

                String senha = new String(senhaChars);
                String confirmar = new String(confirmarChars);

                if (!senha.equals(confirmar)) {
                    JOptionPane.showMessageDialog(null, "As senhas não coincidem!");
                    return;
                }

                if (senha.length() < 6) {
                    JOptionPane.showMessageDialog(null, "Senha mínima de 6 caracteres!");
                    return;
                }

                dao.UsuarioDAO.atualizarSenha(usuario, senha);

                JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");

                new telalogin().setVisible(true);
                dispose();
                return;
            }

            // 🟢 CADASTRO
            if (senhaChars.length == 0) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
                return;
            }

            String senha = new String(senhaChars);

            if (dao.UsuarioDAO.usuarioExiste(usuario)) {
                JOptionPane.showMessageDialog(null, "Usuário já cadastrado!");
                new telalogin().setVisible(true);
                dispose();
                return;
            }

            dao.UsuarioDAO.cadastrar(usuario, senha);
            JOptionPane.showMessageDialog(null, "Cadastro realizado!");

            new telalogin().setVisible(true);
            dispose();
        });

        // 🎨 ELEMENTOS VISUAIS
        JLabel robo = new JLabel();
        robo.setIcon(new ImageIcon("images/Robô_futurista_em_um_cenário_cósmico-removebg-preview.png"));
        robo.setBounds(1162, 130, 1598, 1342);
        contentPane.add(robo);

        JLabel titulo = new JLabel();
        titulo.setIcon(new ImageIcon("images/image-removebg-preview (2).png"));
        titulo.setBounds(650, 191, 590, 155);
        contentPane.add(titulo);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setForeground(Color.WHITE);
        lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblUsuario.setBounds(772, 325, 128, 42);
        contentPane.add(lblUsuario);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblSenha.setBounds(772, 408, 128, 42);
        contentPane.add(lblSenha);
    }

    // 🔥 RECUPERAÇÃO DE SENHA
    public telacadastro(String usuario) {
        this();

        modoRecuperacao = true;

        textField.setText(usuario);
        textField.setEditable(false);

        passwordField.setText("");

        btnCadastrar.setText("Alterar senha");

        // 🔁 CONFIRMAR SENHA
        passwordConfirmField = new JPasswordField();
        passwordConfirmField.setBounds(772, 590, 326, 40);
        contentPane.add(passwordConfirmField);

        JLabel lblConfirmar = new JLabel("Confirmar Senha");
        lblConfirmar.setForeground(Color.WHITE);
        lblConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblConfirmar.setBounds(772, 540, 200, 60);
        contentPane.add(lblConfirmar);
    }
}