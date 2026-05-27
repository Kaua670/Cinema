package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import dao.IngressoDAO;

public class IngressoDAOTeste {

    @Test
    public void testeSalvarIngresso() {

        // salva ingresso
        IngressoDAO.salvarIngresso(
                "kaua",
                "Batman",
                "20:00",
                "2D",
                "10/10/2026",
                "A1",
                "PIX",
                "20.0"
        );

        // verifica se assento ficou ocupado
        boolean ocupado =
                IngressoDAO.assentoOcupado(
                        "Batman",
                        "20:00",
                        "2D",
                        "10/10/2026",
                        "A1"
                );

        assertTrue(ocupado);
    }

    @Test
    public void testeAssentoOcupado() {

        // cria ingresso
        IngressoDAO.salvarIngresso(
                "kaua",
                "Vingadores",
                "21:00",
                "3D",
                "11/10/2026",
                "B2",
                "Cartao",
                "30.0"
        );

        boolean ocupado =
                IngressoDAO.assentoOcupado(
                        "Vingadores",
                        "21:00",
                        "3D",
                        "11/10/2026",
                        "B2"
                );

        assertTrue(ocupado);
    }

    @Test
    public void testeRemoverIngresso() {

        // salva
        IngressoDAO.salvarIngresso(
                "kaua",
                "Homem Aranha",
                "19:00",
                "2D",
                "12/10/2026",
                "C3",
                "PIX",
                "20.0"
        );

        // remove
        IngressoDAO.removerIngresso(
                "Homem Aranha",
                "19:00",
                "2D",
                "12/10/2026",
                "C3"
        );

        // verifica
        boolean ocupado =
                IngressoDAO.assentoOcupado(
                        "Homem Aranha",
                        "19:00",
                        "2D",
                        "12/10/2026",
                        "C3"
                );

        assertFalse(ocupado);
    }

    @Test
    public void testeListarRelatorio() {

        // salva ingresso
        IngressoDAO.salvarIngresso(
                "kaua",
                "Deadpool",
                "22:00",
                "3D",
                "13/10/2026",
                "D4",
                "Dinheiro",
                "30.0"
        );

        List<String> relatorio =
                IngressoDAO.listarRelatorio();

        assertNotNull(relatorio);

        assertTrue(relatorio.size() > 0);
    }

    @Test
    public void testeRelatorioContemFilme() {

        // salva ingresso
        IngressoDAO.salvarIngresso(
                "kaua",
                "Superman",
                "18:00",
                "2D",
                "14/10/2026",
                "E5",
                "PIX",
                "20.0"
        );

        List<String> relatorio =
                IngressoDAO.listarRelatorio();

        boolean encontrou = false;

        for (String texto : relatorio) {

            if (texto.contains("Superman")) {

                encontrou = true;
                break;
            }
        }

        assertTrue(encontrou);
    }
}