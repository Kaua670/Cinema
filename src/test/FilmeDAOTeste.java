package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import dao.FilmeDAO;

public class FilmeDAOTeste {

    @Test
    public void testeAdicionarFilme() {

        FilmeDAO.adicionarFilme(
                "Batman",
                "16",
                "batman.jpg"
        );

        boolean existe =
                FilmeDAO.filmeExiste("Batman");

        assertTrue(existe);
    }

    @Test
    public void testeFilmeExiste() {

        FilmeDAO.adicionarFilme(
                "Vingadores",
                "14",
                "vingadores.jpg"
        );

        boolean existe =
                FilmeDAO.filmeExiste("Vingadores");

        assertTrue(existe);
    }

    @Test
    public void testeFilmeNaoExiste() {

        boolean existe =
                FilmeDAO.filmeExiste("FilmeQueNaoExiste");

        assertFalse(existe);
    }

    @Test
    public void testeExcluirFilme() {

        FilmeDAO.adicionarFilme(
                "Superman",
                "12",
                "superman.jpg"
        );

        boolean removido =
                FilmeDAO.excluirFilme("Superman");

        assertTrue(removido);
    }

    @Test
    public void testeExcluirFilmeInexistente() {

        boolean removido =
                FilmeDAO.excluirFilme("NaoExiste");

        assertFalse(removido);
    }

    @Test
    public void testeListarFilmes() {

        ArrayList<String[]> lista =
                FilmeDAO.listarFilmes();

        assertNotNull(lista);
    }

    @Test
    public void testeListaPossuiFilmes() {

        FilmeDAO.adicionarFilme(
                "Deadpool",
                "18",
                "deadpool.jpg"
        );

        ArrayList<String[]> lista =
                FilmeDAO.listarFilmes();

        assertTrue(lista.size() > 0);
    }

    @Test
    public void testeNomeFilme() {

        FilmeDAO.adicionarFilme(
                "Flash",
                "12",
                "flash.jpg"
        );

        ArrayList<String[]> lista =
                FilmeDAO.listarFilmes();

        boolean encontrou = false;

        for (String[] filme : lista) {

            if (filme[0].equals("Flash")) {

                encontrou = true;
                break;
            }
        }

        assertTrue(encontrou);
    }

    @Test
    public void testeClassificacaoFilme() {

        FilmeDAO.adicionarFilme(
                "Coringa",
                "18",
                "coringa.jpg"
        );

        ArrayList<String[]> lista =
                FilmeDAO.listarFilmes();

        boolean encontrou = false;

        for (String[] filme : lista) {

            if (
                    filme[0].equals("Coringa")
                    &&
                    filme[1].equals("18")
            ) {

                encontrou = true;
                break;
            }
        }

        assertTrue(encontrou);
    }

    @Test
    public void testeImagemFilme() {

        FilmeDAO.adicionarFilme(
                "Avatar",
                "12",
                "avatar.jpg"
        );

        ArrayList<String[]> lista =
                FilmeDAO.listarFilmes();

        boolean encontrou = false;

        for (String[] filme : lista) {

            if (
                    filme[0].equals("Avatar")
                    &&
                    filme[2].contains("avatar.jpg")
            ) {

                encontrou = true;
                break;
            }
        }

        assertTrue(encontrou);
    }
}