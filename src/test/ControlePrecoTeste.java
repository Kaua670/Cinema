package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Controle.ControlePreco;

public class ControlePrecoTeste {

    @Test
    public void testePreco2D() {

        double preco =
                ControlePreco.preco2D;

        assertEquals(20.0, preco);
    }

    @Test
    public void testePreco3D() {

        double preco =
                ControlePreco.preco3D;

        assertEquals(30.0, preco);
    }

    @Test
    public void testeAdicionarAssentoBloqueado() {

        // limpa lista antes do teste
        ControlePreco.assentosBloqueados.clear();

        // adiciona assento
        ControlePreco.assentosBloqueados.add("A1");

        // verifica
        assertTrue(
                ControlePreco.assentosBloqueados.contains("A1")
        );
    }

    @Test
    public void testeRemoverAssentoBloqueado() {

        ControlePreco.assentosBloqueados.clear();

        ControlePreco.assentosBloqueados.add("B2");

        // remove
        ControlePreco.assentosBloqueados.remove("B2");

        // verifica
        assertFalse(
                ControlePreco.assentosBloqueados.contains("B2")
        );
    }

    @Test
    public void testeQuantidadeAssentosBloqueados() {

        ControlePreco.assentosBloqueados.clear();

        ControlePreco.assentosBloqueados.add("A1");
        ControlePreco.assentosBloqueados.add("A2");
        ControlePreco.assentosBloqueados.add("A3");

        assertEquals(
                3,
                ControlePreco.assentosBloqueados.size()
        );
    }
}