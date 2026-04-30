package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDAO {

    // 🔐 LOGIN
    public static boolean verificar(String usuario, String senha) {

        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";

        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // encontrou = login válido

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 🆕 CADASTRAR
    public static void cadastrar(String usuario, String senha) {

        String sql = "INSERT INTO usuarios (usuario, senha) VALUES (?, ?)";

        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            stmt.executeUpdate();

    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
        
    public static boolean Administrador(String usuario, String senha) {

        String sql = "SELECT * FROM Administrador WHERE usuarioAdm = ? AND senhaAdm = ?";

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
        		
        	
        			
        	

    

    // 🔎 VERIFICA SE USUÁRIO EXISTE
    public static boolean usuarioExiste(String usuario) {

        String sql = "SELECT 1 FROM usuarios WHERE usuario = ?";

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

    // 🔄 ATUALIZAR SENHA (🔥 CORREÇÃO PRINCIPAL)
    public static void atualizarSenha(String usuario, String senha) {
    	  System.out.println("Atualizando senha de: [" + usuario + "]");

        String sql = "UPDATE usuarios SET senha = ? WHERE usuario = ?";

        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, senha);
            stmt.setString(2, usuario);

            int linhas = stmt.executeUpdate();

            if (linhas == 0) {
                System.out.println("⚠️ Nenhum usuário foi atualizado!");
            } else {
                System.out.println("✅ Senha atualizada com sucesso!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 📜 LISTAR USUÁRIOS
    public static String listarUsuarios() {

        StringBuilder lista = new StringBuilder();

        try (Connection conn = Banco.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT usuario, senha FROM usuarios")) {

            while (rs.next()) {
                lista.append("Usuário: ")
                     .append(rs.getString("usuario"))
                     .append("\nSenha: ")
                     .append(rs.getString("senha"))
                     .append("\n\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista.toString();
    }
}