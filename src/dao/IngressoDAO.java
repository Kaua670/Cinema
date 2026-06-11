package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IngressoDAO {

	// ================= SALVAR INGRESSO =================
	public static void salvarIngresso(
			String usuario,
			String filme,
			String horario,
			String tipo,
			String data,
			String assento,
			String pagamento,
			String valor
	) {

		String sql =
				"INSERT INTO ingressos "
				+ "(usuario, filme, horario, tipo, data_sessao, assento, pagamento, valor) "
				+ "VALUES (?,?,?,?,?,?,?,?)";

		try (
				Connection conn = Banco.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)
		) {

			stmt.setString(1, usuario);
			stmt.setString(2, filme);
			stmt.setString(3, horario);
			stmt.setString(4, tipo);
			stmt.setString(5, data);
			stmt.setString(6, assento);
			stmt.setString(7, pagamento);
			stmt.setString(8, valor);

			stmt.executeUpdate();

			System.out.println("INGRESSO SALVO!");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// ================= VERIFICA ASSENTO =================
	public static boolean assentoOcupado(
			String filme,
			String horario,
			String tipo,
			String data,
			String assento
	) {

		String sql =
				"SELECT * FROM ingressos "
				+ "WHERE filme=? "
				+ "AND horario=? "
				+ "AND tipo=? "
				+ "AND data_sessao=? "
				+ "AND assento=?";

		try (
				Connection conn = Banco.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)
		) {

			stmt.setString(1, filme);
			stmt.setString(2, horario);
			stmt.setString(3, tipo);
			stmt.setString(4, data);
			stmt.setString(5, assento);

			ResultSet rs = stmt.executeQuery();

			return rs.next();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return false;
	}

	// ================= REMOVER INGRESSO =================
	public static void removerIngresso(
			String filme,
			String horario,
			String tipo,
			String data,
			String assento
	) {

		String sql =
				"DELETE FROM ingressos "
				+ "WHERE filme=? "
				+ "AND horario=? "
				+ "AND tipo=? "
				+ "AND data_sessao=? "
				+ "AND assento=?";

		try (
				Connection conn = Banco.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)
		) {

			stmt.setString(1, filme);
			stmt.setString(2, horario);
			stmt.setString(3, tipo);
			stmt.setString(4, data);
			stmt.setString(5, assento);

			stmt.executeUpdate();

			System.out.println("INGRESSO REMOVIDO!");

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	// ================= RELATÓRIO =================
	public static List<String> listarRelatorio() {

		List<String> lista =
				new ArrayList<>();

		String sql =
				"SELECT * FROM ingressos";

		try (
				Connection conn = Banco.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery()
		) {

			while (rs.next()) {

				String texto =

						"Cliente: " + rs.getString("usuario")
						+ "\nFilme: " + rs.getString("filme")
						+ "\nSessão: " + rs.getString("horario")
						+ "\nTipo: " + rs.getString("tipo")
						+ "\nData: " + rs.getString("data_sessao")
						+ "\nAssento: " + rs.getString("assento")
						+ "\nPagamento: " + rs.getString("pagamento")
						+ "\nValor: R$ " + rs.getString("valor");

				lista.add(texto);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return lista;
	}
	public static String relatorioUsuario(String usuario) {

	    StringBuilder sb = new StringBuilder();

	    sb.append("===== MEUS INGRESSOS =====\n\n");
	    sb.append("Cliente: ").append(usuario).append("\n\n");

	    try {

	        Connection con = Banco.conectar();

	        String sql =
	                "SELECT * FROM ingressos " +
	                "WHERE usuario=?";

	        PreparedStatement ps =
	                con.prepareStatement(sql);

	        ps.setString(1, usuario);

	        ResultSet rs = ps.executeQuery();

	        while (rs.next()) {

	            sb.append("Filme: ")
	              .append(rs.getString("filme"))
	              .append("\n");

	            sb.append("Sessão: ")
	              .append(rs.getString("horario"))
	              .append("\n");

	            sb.append("Data: ")
	              .append(rs.getString("data_sessao"))
	              .append("\n");

	            sb.append("Tipo: ")
	              .append(rs.getString("tipo"))
	              .append("\n");

	            sb.append("Assento: ")
	              .append(rs.getString("assento"))
	              .append("\n");

	            sb.append("Pagamento: ")
	              .append(rs.getString("pagamento"))
	              .append("\n");

	            sb.append("Valor: R$ ")
	              .append(rs.getString("valor"))
	              .append("\n");

	            sb.append("\n------------------------\n\n");
	        }

	        con.close();

	    } catch (Exception e) {

	        e.printStackTrace();

	        return "Erro ao gerar relatório!";
	    }

	    return sb.toString();
	}
}