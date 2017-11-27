package br.com.giltecnologia.gerenciadorfinanceiro.model

import br.com.giltecnologia.gerenciadorfinanceiro.enums.TipoTransacaoEnum
import java.math.BigDecimal
import java.util.Calendar


class Transacao(val valor : BigDecimal,
                val categoria: String = "Indefinida",
                val tipo : TipoTransacaoEnum,
                val data: Calendar = Calendar.getInstance()) {


    //sobrecarga de construtores, para evitar a criação de vários c onstrutores é possível utilizar é FEATURES NAMED PARAMETER
     constructor(valor: BigDecimal, tipo : TipoTransacaoEnum) : this(valor,"Indefinida",tipo)
}