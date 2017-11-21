package br.com.giltecnologia.gerenciadorfinanceiro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.giltecnologia.gerenciadorfinanceiro.R


class TransacoesAdapter(context: Context,
                        transacoes : List<String>) : BaseAdapter() {

    private val  transacoes = transacoes
    private val  context = context

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
      return  LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

    }

    override fun getItem(position: Int): String {
       return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
      return transacoes.size
    }


}