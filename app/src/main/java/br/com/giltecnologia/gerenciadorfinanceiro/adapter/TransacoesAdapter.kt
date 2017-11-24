package br.com.giltecnologia.gerenciadorfinanceiro.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.giltecnologia.gerenciadorfinanceiro.R
import br.com.giltecnologia.gerenciadorfinanceiro.extension.formatoBrasileiro
import br.com.giltecnologia.gerenciadorfinanceiro.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*


class TransacoesAdapter(context: Context,
                        transacoes : List<Transacao>) : BaseAdapter() {

    private val  transacoes = transacoes
    private val  context = context

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {


         var novoView = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

         var transacao = transacoes[position]

         novoView.transacao_valor.text = transacao.valor.toString()
         novoView.transacao_categoria.text = transacao.categoria
         novoView.transacao_data.text = transacao.data.formatoBrasileiro()

      return  novoView

    }

    override fun getItem(position: Int): Transacao {
       return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
      return transacoes.size
    }


}