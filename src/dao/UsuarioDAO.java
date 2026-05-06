package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDAO {

    // ================= LOGIN =================
    public static boolean verificar(String usuario, String senha) {

        String sql =
                "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";

        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ================= CADASTRAR =================
    public static void cadastrar(String usuario, String senha) {

        String sql =
                "INSERT INTO usuarios (usuario, senha) VALUES (?, ?)";

        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= LOGIN ADMIN =================
    public static boolean Administrador(String usuario, String senha) {

        String sql =
                "SELECT * FROM Administrador WHERE usuarioAdm = ? AND senhaAdm = ?";

        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ================= VERIFICAR USUÁRIO =================
    public static boolean usuarioExiste(String usuario) {

        String sql =
                "SELECT 1 FROM usuarios WHERE usuario = ?";

        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    // ================= ATUALIZAR SENHA =================
    public static void atualizarSenha(String usuario, String senha) {

        System.out.println("Atualizando senha de: [" + usuario + "]");

        String sql =
                "UPDATE usuarios SET senha = ? WHERE usuario = ?";

        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, senha);
            stmt.setString(2, usuario);

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {

                System.out.println(
                        "⚠️ Nenhum usuário foi atualizado!"
                );

            } else {

                System.out.println(
                        "✅ Senha atualizada com sucesso!"
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

 // ================= LISTAR USUÁRIOS =================
    public static java.util.List<String> listarUsuarios() {

        java.util.List<String> lista =
                new java.util.ArrayList<>();

        String sql =
                "SELECT usuario, senha FROM usuarios";

        try (Connection conn = Banco.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                String dados =
                        "Usuário: " +
                        rs.getString("usuario") +
                        "\nSenha: " +
                        rs.getString("senha");

                lista.add(dados);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
    // ================= EXCLUIR USUÁRIO =================
    public static boolean excluirUsuario(String usuario) {

        String sql =
                "DELETE FROM usuarios WHERE usuario = ?";

        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);

            int linhas = stmt.executeUpdate();

            return linhas > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}