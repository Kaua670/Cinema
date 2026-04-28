package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class UsuarioDAO {

    public static boolean verificar(String usuario, String senha) {

        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";

        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            return rs.next(); // se encontrou → login válido

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
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
    
    public static boolean verificar1(String usuario, String senha) {

        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";

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
    public static String listarUsuarios() {
    	StringBuilder lista = new StringBuilder();

    	try {
    		Connection conn = Banco.getConnection();
    		Statement stmt = conn.createStatement();
    		ResultSet rs = stmt.executeQuery("SELECT usuario, senha FROM usuarios");

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
    
    public static boolean usuarioExiste(String usuario) {

    	String sql = "SELECT 1 FROM usuarios WHERE usuario = ?";

    	try (Connection conn = Banco.getConnection();
    	     PreparedStatement stmt = conn.prepareStatement(sql)) {

    		stmt.setString(1, usuario);

    		try (ResultSet rs = stmt.executeQuery()) {
    			return rs.next();
    		}

    	} catch (Exception e) {
    		e.printStackTrace();
    	}

    	return false;
    }
}