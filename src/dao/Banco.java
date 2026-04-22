package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Banco {

    public static void main(String[] args) {

        // CAMINHO DO BANCO
        String url = "jdbc:sqlite:BancoCinema.db";

        try {
            Connection conn = DriverManager.getConnection(url);
            System.out.println("Conectado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao conectar!");
            e.printStackTrace();
        }
    }

	public static Connection conectar() {
		// TODO Auto-generated method stub
		return null;
	}
}