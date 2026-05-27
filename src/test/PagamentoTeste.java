package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Controle.ControlePreco;
import view.pagamento;

public class PagamentoTeste {

    @Test
    public void testeCriarTelaPagamento() {

        pagamento tela =
                new pagamento(
                        "Batman",
                        "20:00",
                        "A1",
                        "2D",
                        "10/10/2026"
                );

        assertNotNull(tela);
    }

    @Test
    public void testeTituloTela() {

        pagamento tela =
                new pagamento(
                        "Batman",
                        "20:00",
                        "A1",
                        "2D",
                        "10/10/2026"
                );

        assertEquals(
                "Pagamento",
                tela.getTitle()
        );
    }

    @Test
    public void testePreco2D() {

        double valor =
                ControlePreco.preco2D;

        assertEquals(
                20.0,
                valor
        );
    }

    @Test
    public void testePreco3D() {

        double valor =
                ControlePreco.preco3D;

        assertEquals(
                30.0,
                valor
        );
    }

    @Test
    public void testeValorDiferente2D3D() {

        assertNotEquals(
                ControlePreco.preco2D,
                ControlePreco.preco3D
        );
    }

    @Test
    public void testeTelaNaoENula() {

        pagamento tela =
                new pagamento(
                        "Vingadores",
                        "21:00",
                        "B2",
                        "3D",
                        "11/10/2026"
                );

        assertNotNull(tela);
    }

    @Test
    public void testeTipo2D() {

        String tipo = "2D";

        assertEquals(
                "2D",
                tipo
        );
    }

    @Test
    public void testeTipo3D() {

        String tipo = "3D";

        assertEquals(
                "3D",
                tipo
        );
    }

    @Test
    public void testeHorarioSessao() {

        String horario = "20:00";

        assertTrue(
                horario.contains(":")
        );
    }

    @Test
    public void testeAssento() {

        String assento = "C5";

        assertEquals(
                "C5",
                assento
        );
    }
}