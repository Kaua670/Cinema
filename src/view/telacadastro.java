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

    public telacadastro() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1920, 1080);

        JLabel fundo = new JLabel(new ImageIcon("images/galaxy_2560x1250.png"));
        fundo.setLayout(null);
        setContentPane(fundo);

        contentPane = fundo;

        // 👤 USUÁRIO
        textField = new JTextField();
        textField.setBounds(772, 350, 326, 40);
        contentPane.add(textField);

        // 🔒 SENHA
        passwordField = new JPasswordField();
        passwordField.setBounds(772, 430, 326, 40);
        contentPane.add(passwordField);

        // 🔘 BOTÕES
        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(772, 500, 155, 49);
        contentPane.add(btnCadastrar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(937, 500, 161, 49);
        contentPane.add(btnVoltar);

        btnVoltar.addActionListener(e -> {
            new telalogin().setVisible(true);
            dispose();
        });

        // 🔥 AÇÃO PRINCIPAL
        btnCadastrar.addActionListener(e -> {

            String usuario = textField.getText().trim();

            if (usuario.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Digite um usuário!");
                return;
            }

            char[] senhaChars = passwordField.getPassword();

            // 🔥 RECUPERAÇÃO DE SENHA
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
                    JOptionPane.showMessageDialog(null, "Senha deve ter no mínimo 6 caracteres!");
                    return;
                }

                dao.UsuarioDAO.atualizarSenha(usuario, senha);

                JOptionPane.showMessageDialog(null, "Senha alterada com sucesso!");

                new telalogin().setVisible(true);
                dispose();
                return;
            }

            // 🟢 CADASTRO NORMAL
            if (senhaChars.length == 0) {
                JOptionPane.showMessageDialog(null, "Digite a senha!");
                return;
            }

            String senha = new String(senhaChars);

            if (senha.length() < 6) {
                JOptionPane.showMessageDialog(null, "Senha deve ter no mínimo 6 caracteres!");
                return;
            }

            // 🔍 VERIFICA SE JÁ EXISTE
            if (dao.UsuarioDAO.usuarioExiste(usuario)) {

                int opcao = JOptionPane.showConfirmDialog(
                    null,
                    "Usuário já existe! Deseja ir para o login?",
                    "Usuário existente",
                    JOptionPane.YES_NO_OPTION
                );

                if (opcao == JOptionPane.YES_OPTION) {
                    new telalogin().setVisible(true);
                    dispose();
                }

                return;
            }

            // 💾 CADASTRAR
            dao.UsuarioDAO.cadastrar(usuario, senha);

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");

            new telalogin().setVisible(true);
            dispose();
        });

        // 🎨 VISUAL
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
        lblUsuario.setBounds(772, 310, 128, 42);
        contentPane.add(lblUsuario);

        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setForeground(Color.WHITE);
        lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblSenha.setBounds(772, 390, 128, 42);
        contentPane.add(lblSenha);
    }

    // 🔥 RECUPERAÇÃO
    public telacadastro(String usuario) {
        this();

        modoRecuperacao = true;

        textField.setText(usuario);
        textField.setEditable(false);

        passwordField.setText("");
        btnCadastrar.setText("Alterar senha");

        passwordConfirmField = new JPasswordField();
        passwordConfirmField.setBounds(772, 510, 326, 40);
        contentPane.add(passwordConfirmField);

        JLabel lblConfirmar = new JLabel("Confirmar Senha");
        lblConfirmar.setForeground(Color.WHITE);
        lblConfirmar.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblConfirmar.setBounds(772, 470, 200, 40);
        contentPane.add(lblConfirmar);

        btnCadastrar.setBounds(772, 570, 155, 49);
    }
}