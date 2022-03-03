package foo.exercicio.orgs.dao

import foo.exercicio.orgs.model.Produto
import java.math.BigDecimal

class ProdutosDao {

    fun adiciona(produto: Produto) {
        produtos.add(produto)
    }

    fun buscaTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>(
            Produto(
                nome = "Salada de Frutas",
                descricao = "Laranja, Maçã, Uva",
                valor = BigDecimal("19.99")
            )
        )
    }
}