package foo.exercicio.orgs.ui.activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import foo.exercicio.orgs.R
import foo.exercicio.orgs.dao.ProdutosDao
import foo.exercicio.orgs.databinding.ActivityListaProdutosBinding
import foo.exercicio.orgs.ui.recyclerview.adapter.ListaProdutosAdapter

class ListaProdutosActivity : AppCompatActivity() {

    private val dao = ProdutosDao()
    private val adapter = ListaProdutosAdapter(context = this, produtos = dao.buscaTodos())

    private val binding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configuraRecyclerView()
        configuraFab()
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        adapter.atualiza(dao.buscaTodos())
    }

    private fun configuraFab() {
        val fab = binding.activityListaProdutoFab
        fab.setOnClickListener {
            vaiParaFormularioProduto()
        }
    }

    private fun vaiParaFormularioProduto() {
        val intent = Intent(
            this,
            FormularioProdutoActivity::class.java
        ) // O Intent Acessa outra Activity
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerview = binding.activityListaProdutoRecyclerview
        recyclerview.adapter = adapter
    }
}