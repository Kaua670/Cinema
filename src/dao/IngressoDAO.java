package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class IngressoDAO {

    // ================= SALVAR INGRESSO =================
    public static void salvarIngresso(
            String usuario,
            String filme,
            String horario,
            String tipo,
            String assento
    ) {

        String sql =
                "INSERT INTO ingressos "
                + "(usuario, filme, horario, tipo, assento) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, filme);
            stmt.setString(3, horario);
            stmt.setString(4, tipo);
            stmt.setString(5, assento);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= ASSENTO OCUPADO =================
    public static boolean assentoOcupado(
            String filme,
            String horario,
            String tipo,
            String assento
    ) {

        String sql =
                "SELECT 1 FROM ingressos "
                + "WHERE filme = ? "
                + "AND horario = ? "
                + "AND tipo = ? "
                + "AND assento = ?";

        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

    // ================= RELATÓRIO =================
    public static ArrayList<String> listarRelatorio() {

        ArrayList<String> lista =
                new ArrayList<>();

        String sql =
                "SELECT * FROM ingressos";

        try (Connection conn = Banco.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                String dados =
                        "Cliente: " + rs.getString("usuario")
                        + "\nFilme: " + rs.getString("filme")
                        + "\nSessão: " + rs.getString("horario")
                        + "\nTipo: " + rs.getString("tipo")
                        + "\nAssento: " + rs.getString("assento");

                lista.add(dados);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }
}