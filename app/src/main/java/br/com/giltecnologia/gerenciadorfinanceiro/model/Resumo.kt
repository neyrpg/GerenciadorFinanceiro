package br.com.giltecnologia.gerenciadorfinanceiro.model

import br.com.giltecnologia.gerenciadorfinanceiro.enums.TipoTransacaoEnum
import br.com.giltecnologia.gerenciadorfinanceiro.extension.sumByBigDecimal
import java.math.BigDecimal

/**
 * Created by Gilciney Marques on 10/12/2017.
 */
class Resumo(val transacoes : List<Transacao>) {


    fun receita() : BigDecimal{
        return somaTransacao(TipoTransacaoEnum.RECEITA)
    }

    fun despesa() : BigDecimal{
        return somaTransacao(TipoTransacaoEnum.DESPESA)
    }

    fun total() = receita().subtract(despesa())//outra forma de retornar


     private fun somaTransacao(tipo : TipoTransacaoEnum) : BigDecimal {
//        return transacoes.filter { transacao -> transacao.tipo == tipo }
//                .sumByBigDecimal { transacao -> transacao.valor } // forma tradiconal de usar lambda com variavel explicita
        return transacoes.filter { it.tipo == tipo }
                .sumByBigDecimal { it.valor } // ele possue sempre uma referência implicita chamada "it" para cada objeto do array
    }

}