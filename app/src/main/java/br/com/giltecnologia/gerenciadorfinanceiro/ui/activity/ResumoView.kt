package br.com.giltecnologia.gerenciadorfinanceiro.ui.activity

import android.support.v4.content.ContextCompat
import android.view.View
import br.com.giltecnologia.gerenciadorfinanceiro.R
import br.com.giltecnologia.gerenciadorfinanceiro.extension.formatoMoedaBrasileiro
import br.com.giltecnologia.gerenciadorfinanceiro.model.Resumo
import br.com.giltecnologia.gerenciadorfinanceiro.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

/**
 * Created by Gilciney Marques on 10/12/2017.
 */
class ResumoView(private val view: View,
                 private val listaTrasacao: List<Transacao>) {


    private var resumo: Resumo = Resumo(listaTrasacao)

    private val corDespesa = ContextCompat.getColor(view.context, R.color.despesa)

    private val corReceita = ContextCompat.getColor(view.context, R.color.receita)

    fun adicionaTotal() {
        val total = resumo.total()

        with(view.resumo_card_total) {
            text = total.formatoMoedaBrasileiro()
            setTextColor(determinaCor(total))

        }
    }

    private fun determinaCor(total: BigDecimal) : Int {
        return if (total < BigDecimal.ZERO) {
             corDespesa
        } else {
             corReceita
        }
    }

    fun adicionaReceita() {
        with(view.resumo_card_receita) {
            text = resumo.receita().formatoMoedaBrasileiro()
            setTextColor(ContextCompat.getColor(view.context, R.color.receita))
        }
    }

    fun adicionaDespesa() {
        with(view.resumo_card_despesa) {
            text = resumo.despesa().formatoMoedaBrasileiro()
            setTextColor(corDespesa)
        }
    }
}