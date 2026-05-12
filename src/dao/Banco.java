package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Banco {

    private static final String URL = "jdbc:sqlite:BancoCinema.db";

    public static Connection getConnection() throws Exception {

        return DriverManager.getConnection(URL);
    }

 // ================= INICIALIZA BANCO =================
    public static void init() {

        try (
                Connection conn = getConnection();
                Statement stmt = conn.createStatement()
        ) {


System.out.println("✅ Banco conectado!");

// ================= TABELA FILMES =================
String filmes =
        "CREATE TABLE IF NOT EXISTS filmes ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome TEXT,"
                + "classificacao TEXT,"
                + "imagem TEXT"
                + ");";

stmt.execute(filmes);

// ================= TABELA USUÁRIOS =================
String usuarios =
        "CREATE TABLE IF NOT EXISTS usuarios ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "usuario TEXT UNIQUE,"
                + "senha TEXT"
                + ");";

stmt.execute(usuarios);

// ================= TABELA ADMIN =================
String admin =
        "CREATE TABLE IF NOT EXISTS Administrador ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "usuarioAdm TEXT UNIQUE,"
                + "senhaAdm TEXT"
                + ");";

stmt.execute(admin);

// ================= ADMIN PADRÃO =================
String insertAdmin =
        "INSERT OR IGNORE INTO Administrador(usuarioAdm, senhaAdm) "
                + "VALUES('adm01', '141516')";

stmt.execute(insertAdmin);

// ================= TABELA INGRESSO =================
String ingresso =
        "CREATE TABLE IF NOT EXISTS ingresso ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "usuario TEXT,"
                + "filme TEXT,"
                + "horario TEXT,"
                + "tipo TEXT,"
                + "assento TEXT,"
                + "pagamento TEXT,"
                + "valor TEXT"
                + ");";

stmt.execute(ingresso);

System.out.println("✅ Tabelas criadas!");

} catch (Exception e) {

System.out.println("❌ Erro ao iniciar banco!");

e.printStackTrace();
}
    }


    // ================= CONECTAR =================
    public static Connection conectar() {

        try {

            return getConnection();

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}