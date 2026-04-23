package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FilmeDAO {

    public static ArrayList<String[]> listarFilmes() {

        ArrayList<String[]> lista = new ArrayList<>();

        String sql = "SELECT nome, classificacao FROM filmes";

        try (Connection conn = Banco.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new String[]{
                    rs.getString("nome"),
                    rs.getString("classificacao")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }
}