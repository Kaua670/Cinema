package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FilmeDAO {

    // ================= LISTAR FILMES =================
    public static ArrayList<String[]> listarFilmes() {

        ArrayList<String[]> lista =
                new ArrayList<>();

        String sql =
                "SELECT nome, classificacao, imagem FROM filmes";

        try (
                Connection conn = Banco.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {

            while (rs.next()) {

                String[] filme = {

                        rs.getString("nome"),

                        rs.getString("classificacao"),

                        rs.getString("imagem")
                };

                lista.add(filme);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    // ================= ADICIONAR FILME =================
    public static void adicionarFilme(
            String nome,
            String classificacao,
            String imagem
    ) {

        String sql =
                "INSERT INTO filmes(nome, classificacao, imagem) VALUES(?,?,?)";

        try (
                Connection conn = Banco.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, nome);

            stmt.setString(2, classificacao);

            stmt.setString(3, imagem);

            stmt.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ================= EXCLUIR FILME =================
    public static boolean excluirFilme(String nome) {

        String sql =
                "DELETE FROM filmes WHERE nome=?";

        try (
                Connection conn = Banco.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, nome);

            int linhas =
                    stmt.executeUpdate();

            return linhas > 0;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }

    // ================= VERIFICAR FILME =================
    public static boolean filmeExiste(String nome) {

        String sql =
                "SELECT 1 FROM filmes WHERE nome=?";

        try (
                Connection conn = Banco.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {

            stmt.setString(1, nome);

            ResultSet rs =
                    stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
}