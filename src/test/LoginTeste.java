package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

import dao.UsuarioDAO;

public class LoginTeste {

    @Test
    public void testeLoginUsuarioComum() {

        // 🔥 cria objeto falso
        UsuarioDAO usuarioDAO = mock(UsuarioDAO.class);

        // 🔥 ensina o mock o que responder
        when(usuarioDAO.usuarioExiste("kaua"))
                .thenReturn(true);

        when(usuarioDAO.verificar("kaua", "123"))
                .thenReturn(true);

        // 🔥 executa
        boolean existe = usuarioDAO.usuarioExiste("kaua");
        boolean login = usuarioDAO.verificar("kaua", "123");

        // 🔥 verifica resultado
        assertTrue(existe);
        assertTrue(login);

        // 🔥 verify confere se os métodos foram usados
        verify(usuarioDAO).usuarioExiste("kaua");
        verify(usuarioDAO).verificar("kaua", "123");
    }

    @Test
    public void testeSenhaErrada() {

        UsuarioDAO usuarioDAO = mock(UsuarioDAO.class);

        // usuário existe
        when(usuarioDAO.usuarioExiste("kaua"))
                .thenReturn(true);

        // senha errada
        when(usuarioDAO.verificar("kaua", "999"))
                .thenReturn(false);

        boolean login = usuarioDAO.verificar("kaua", "999");

        assertFalse(login);

        verify(usuarioDAO).verificar("kaua", "999");
    }

    @Test
    public void testeUsuarioNaoExiste() {

        UsuarioDAO usuarioDAO = mock(UsuarioDAO.class);

        // usuário inexistente
        when(usuarioDAO.usuarioExiste("abc"))
                .thenReturn(false);

        boolean existe = usuarioDAO.usuarioExiste("abc");

        assertFalse(existe);

        verify(usuarioDAO).usuarioExiste("abc");
    }

    @Test
    public void testeAdministrador() {

        UsuarioDAO usuarioDAO = mock(UsuarioDAO.class);

        // admin válido
        when(usuarioDAO.Administrador("adm01", "141516"))
                .thenReturn(true);

        boolean admin = usuarioDAO.Administrador("adm01", "141516");

        assertTrue(admin);

        verify(usuarioDAO).Administrador("adm01", "141516");
    }
}