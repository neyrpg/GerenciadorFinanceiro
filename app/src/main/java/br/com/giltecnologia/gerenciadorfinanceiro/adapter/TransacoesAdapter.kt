package br.com.giltecnologia.gerenciadorfinanceiro.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.giltecnologia.gerenciadorfinanceiro.R
import br.com.giltecnologia.gerenciadorfinanceiro.enums.TipoTransacaoEnum
import br.com.giltecnologia.gerenciadorfinanceiro.extension.formatoBrasileiro
import br.com.giltecnologia.gerenciadorfinanceiro.extension.formatoMoedaBrasileiro
import br.com.giltecnologia.gerenciadorfinanceiro.extension.limitaCaracteresApresentacao
import br.com.giltecnologia.gerenciadorfinanceiro.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*


class TransacoesAdapter(context: Context,
                        transacoes: List<Transacao>) : BaseAdapter() {

    private val transacoes = transacoes
    private val context = context

    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {


        var novoView = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[position]
        configuraApresentacao(transacao, novoView)
        carregaValores(novoView, transacao)

        return novoView

    }

    private fun carregaValores(novoView: View, transacao: Transacao) {
        novoView.transacao_valor.text = transacao.valor.formatoMoedaBrasileiro()
        novoView.transacao_categoria.text = transacao.categoria.limitaCaracteresApresentacao()
        novoView.transacao_data.text = transacao.data.formatoBrasileiro()
    }

    private fun configuraApresentacao(transacao: Transacao, novoView: View) {


        if (transacao.tipo == TipoTransacaoEnum.RECEITA) {
            novoView.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.receita))
            novoView.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_receita)
        } else {
            novoView.transacao_valor.setTextColor(ContextCompat.getColor(context, R.color.despesa))
            novoView.transacao_icone.setBackgroundResource(R.drawable.icone_transacao_item_despesa)
        }
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