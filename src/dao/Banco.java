package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Banco {

    private static final String URL = "jdbc:sqlite:BancoCinema.db";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL);
    }

    // 🔥 INICIALIZA E CRIA TABELAS
    public static void init() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.println("✅ Banco conectado!");

            // 🎬 TABELA FILMES
            String filmes = "CREATE TABLE IF NOT EXISTS filmes ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nome TEXT,"
                    + "classificacao TEXT,"
                    + "imagem TEXT"
                    + ");";

            stmt.execute(filmes);
            System.out.println("🎬 Tabela 'filmes' OK");

        } catch (Exception e) {
            System.err.println("❌ Erro ao inicializar banco!");
            e.printStackTrace();
        }
    }

    // 🔥 CORREÇÃO IMPORTANTE
    public static Connection conectar() {
        try {
            return getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}