package com.example.danhpaiva;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SistemaEstoqueTest {

    private SistemaEstoque sistemaEstoque;

    @BeforeEach
    public void setUp() {
        sistemaEstoque = new SistemaEstoque();
    }

    @Test
    public void testAdicionarProdutoComSucesso() {
        sistemaEstoque.adicionarProduto("Arroz", 10);
        assertEquals(10, sistemaEstoque.consultarEstoque("Arroz"));
    }

    @Test
    public void testAdicionarProdutoComNomeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.adicionarProduto(null, 10);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.adicionarProduto(" ", 10);
        });
    }

    @Test
    public void testAdicionarProdutoComQuantidadeInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.adicionarProduto("Feijão", 0);
        });
    }

    @Test
    public void testRemoverProdutoComSucesso() {
        sistemaEstoque.adicionarProduto("Feijão", 10);
        sistemaEstoque.removerProduto("Feijão", 5);
        assertEquals(5, sistemaEstoque.consultarEstoque("Feijão"));
    }

    @Test
    public void testRemoverProdutoComQuantidadeTotal() {
        sistemaEstoque.adicionarProduto("Feijão", 5);
        sistemaEstoque.removerProduto("Feijão", 5);
        assertEquals(0, sistemaEstoque.consultarEstoque("Feijão"));
    }

    @Test
    public void testRemoverProdutoComErro() {
        assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.removerProduto("Feijão", 5);
        });

        sistemaEstoque.adicionarProduto("Feijão", 2);
        assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.removerProduto("Feijão", 5);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.removerProduto(" ", 5);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.removerProduto("Feijão", -1);
        });
    }

    @Test
    public void testConsultarEstoque() {
        sistemaEstoque.adicionarProduto("Arroz", 3);
        assertEquals(3, sistemaEstoque.consultarEstoque("Arroz"));
        assertEquals(0, sistemaEstoque.consultarEstoque("ProdutoInexistente"));
    }

    @Test
    public void testConsultarEstoqueComNomeInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.consultarEstoque("");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.consultarEstoque(null);
        });
    }

    @Test
    public void testObterHistoricoTransacoes() {
        sistemaEstoque.adicionarProduto("Arroz", 2);
        sistemaEstoque.removerProduto("Arroz", 1);
        List<String> historico = sistemaEstoque.obterHistoricoTransacoes();
        assertEquals(2, historico.size());
        assertTrue(historico.get(0).contains("Adicionado"));
        assertTrue(historico.get(1).contains("Removido"));
    }

    @Test
    public void testVerificarDisponibilidadeComSucesso() {
        sistemaEstoque.adicionarProduto("Arroz", 5);
        assertTrue(sistemaEstoque.verificarDisponibilidade("Arroz", 3));
        assertFalse(sistemaEstoque.verificarDisponibilidade("Arroz", 10));
    }

    @Test
    public void testVerificarDisponibilidadeComErro() {
        assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.verificarDisponibilidade("", 2);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            sistemaEstoque.verificarDisponibilidade("Arroz", 0);
        });
    }
}
