package br.com.giltecnologia.gerenciadorfinanceiro.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import  br.com.giltecnologia.gerenciadorfinanceiro.R
import br.com.giltecnologia.gerenciadorfinanceiro.adapter.TransacoesAdapter
import br.com.giltecnologia.gerenciadorfinanceiro.enums.TipoTransacaoEnum
import br.com.giltecnologia.gerenciadorfinanceiro.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        //findViewById<ListView>(R.id.lista_transacoes_listview)//

        val lista = transacoesExemplo()

        // val adapter = ArrayAdapter( this, android.R.layout.simple_list_item_1, lista)

        lista_transacoes_listview.adapter = TransacoesAdapter(this, lista)

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