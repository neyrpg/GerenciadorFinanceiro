package br.com.giltecnologia.gerenciadorfinanceiro.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import br.com.giltecnologia.gerenciadorfinanceiro.R
import br.com.giltecnologia.gerenciadorfinanceiro.adapter.TransacoesAdapter
import br.com.giltecnologia.gerenciadorfinanceiro.enums.TipoTransacaoEnum
import br.com.giltecnologia.gerenciadorfinanceiro.model.Transacao
import br.com.giltecnologia.gerenciadorfinanceiro.ui.delegate.AddTransacaoInterface
import br.com.giltecnologia.gerenciadorfinanceiro.ui.dialog.TransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity(), AddTransacaoInterface {


    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        //findViewById<ListView>(R.id.lista_transacoes_listview)//

        //val lista = transacoesExemplo()

        // val adapter = ArrayAdapter( this, android.R.layout.simple_list_item_1, lista)

        atualizarList()

        configuraResumo()

        lista_transacoes_adiciona_receita.setOnClickListener {
            TransacaoDialog(this, window.decorView as ViewGroup).apresentaDialogTransacao(TipoTransacaoEnum.RECEITA)

        }
        lista_transacoes_adiciona_despesa.setOnClickListener {
            TransacaoDialog(this, window.decorView as ViewGroup).apresentaDialogTransacao(TipoTransacaoEnum.DESPESA)

        }

    }

    override fun addTransacao(transacao: Transacao) {
        transacoes.add(transacao)
        configuraResumo()
        atualizarList()
        lista_transacoes_adiciona_menu.close(true)
    }

    private fun atualizarList() {
        lista_transacoes_listview.adapter = TransacoesAdapter(this, transacoes)
    }

    private fun configuraResumo() {
        val viewResumo = window.decorView
        ResumoView(viewResumo, transacoes).adicionaDespesa()
        ResumoView(viewResumo, transacoes).adicionaReceita()
        ResumoView(viewResumo, transacoes).adicionaTotal()

    }


    private fun transacoesExemplo(): List<Transacao> {
        val lista = listOf(Transacao(BigDecimal(100.0), "Comida", TipoTransacaoEnum.DESPESA, Calendar.getInstance()),
                Transacao(BigDecimal(200.0), "Cobustível", TipoTransacaoEnum.DESPESA, Calendar.getInstance()),
                Transacao(BigDecimal(300.0), "Passeio pelo parque da cidade em Brasília", TipoTransacaoEnum.DESPESA, Calendar.getInstance()),
                Transacao(valor = BigDecimal(5000.0), tipo = TipoTransacaoEnum.RECEITA, data = Calendar.getInstance(), categoria = "Salário") // NAMED PARAMETER = Não importa a ordem
                // ele irá atribuir o valor ao atributo nomeado
        )
        return lista
    }
}