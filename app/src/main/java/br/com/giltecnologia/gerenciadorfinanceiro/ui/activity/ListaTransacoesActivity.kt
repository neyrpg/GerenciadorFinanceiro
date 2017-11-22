package br.com.giltecnologia.gerenciadorfinanceiro.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import  br.com.giltecnologia.gerenciadorfinanceiro.R
import br.com.giltecnologia.gerenciadorfinanceiro.adapter.TransacoesAdapter
import br.com.giltecnologia.gerenciadorfinanceiro.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        //findViewById<ListView>(R.id.lista_transacoes_listview)//

        val lista = listOf(Transacao(BigDecimal(100.0), "Comida", Calendar.getInstance()),
                Transacao(BigDecimal(200.0), "Cobustível", Calendar.getInstance()),
                Transacao(BigDecimal(300.0), "Passeio", Calendar.getInstance())
                )

        val adapter = ArrayAdapter( this, android.R.layout.simple_list_item_1, lista)

        lista_transacoes_listview.setAdapter(TransacoesAdapter(this, lista))

    }
}