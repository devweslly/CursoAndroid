package foo.exercicio.orgs.ui.activity

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import foo.exercicio.orgs.dao.ProdutosDao
import foo.exercicio.orgs.databinding.ActivityFormularioProdutoBinding
import foo.exercicio.orgs.databinding.FormularioImagemBinding
import foo.exercicio.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configuraBotaoSalvar()
        binding.activityFormularioProdutoImagem.setOnClickListener {
            val bindingFormularioImagem = FormularioImagemBinding.inflate(layoutInflater)
            bindingFormularioImagem.formularioImagemBotaoCarregar.setOnClickListener {
                val url = bindingFormularioImagem.formularioImagemUrl.text.toString()
//                bindingFormularioImagem.formularioImagemImageview.load(url)
            }

            AlertDialog.Builder(this)
                .setView(bindingFormularioImagem.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = bindingFormularioImagem.formularioImagemUrl.text.toString()
//                    binding.activityFormularioProdutoImagem.load(url)
                }
                .setNegativeButton("cancelar") { _, _ ->

                }
                .show()
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar =
            binding.activityFormularioProdutoBotaoSalvar
        val dao = ProdutosDao()
        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            dao.adiciona(produtoNovo)
            finish()
        }
    }

    private fun criaProduto(): Produto {
        val campoNome =
            binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao =
            binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor =
            binding.activityFormularioProdutoValor
        val valorEmtexto = campoValor.text.toString()
        val valor = if (valorEmtexto.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorEmtexto)
        }

        return Produto(
            nome = nome,
            descricao = descricao,
            valor = valor
        )
    }
}