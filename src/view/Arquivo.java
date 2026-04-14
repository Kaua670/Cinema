package view;

import java.io.*;

public class Arquivo {

	private static final String CAMINHO = "usuarios.txt";

	public static void salvar(String usuario, String senha) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(CAMINHO, true))) {
			bw.write(usuario + ";" + senha);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean verificar(String usuario, String senha) {
		try (BufferedReader br = new BufferedReader(new FileReader(CAMINHO))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				String[] dados = linha.split(";");
				if (dados[0].equals(usuario) && dados[1].equals(senha)) {
					return true;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}