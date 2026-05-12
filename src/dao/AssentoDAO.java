package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AssentoDAO {

    // ================= SALVAR ASSENTO =================
    public static void salvarAssento(
            String filme,
            String horario,
            String tipo,
            String assento
    ) {

        String sql =
                "INSERT INTO assentos(filme, horario, tipo, assento) VALUES(?,?,?,?)";

        try (
                Connection conn = Banco.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, filme);
            stmt.setString(2, horario);
            stmt.setString(3, tipo);
            stmt.setString(4, assento);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= VERIFICA ASSENTO =================
    public static boolean assentoOcupado(
            String filme,
            String horario,
            String tipo,
            String assento
    ) {

        String sql =
                "SELECT * FROM assentos "
              + "WHERE filme=? "
              + "AND horario=? "
              + "AND tipo=? "
              + "AND assento=?";

        try (
                Connection conn = Banco.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, filme);
            stmt.setString(2, horario);
            stmt.setString(3, tipo);
            stmt.setString(4, assento);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    // ================= REMOVER ASSENTO =================
    public static void removerAssento(
            String filme,
            String horario,
            String tipo,
            String assento
    ) {

        String sql =
                "DELETE FROM assentos "
              + "WHERE filme=? "
              + "AND horario=? "
              + "AND tipo=? "
              + "AND assento=?";

        try (
                Connection conn = Banco.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, filme);
            stmt.setString(2, horario);
            stmt.setString(3, tipo);
            stmt.setString(4, assento);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}