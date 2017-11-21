package br.com.giltecnologia.gerenciadorfinanceiro.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import  br.com.giltecnologia.gerenciadorfinanceiro.R
import br.com.giltecnologia.gerenciadorfinanceiro.adapter.TransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        //findViewById<ListView>(R.id.lista_transacoes_listview)//

        val lista = listOf("Comida - R$20,00","Economia - R$50,50")

        val adapter = ArrayAdapter( this, android.R.layout.simple_list_item_1, lista)

        lista_transacoes_listview.setAdapter(TransacoesAdapter(this, lista))

    }
}