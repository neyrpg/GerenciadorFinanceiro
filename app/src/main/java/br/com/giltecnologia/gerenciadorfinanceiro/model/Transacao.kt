package br.com.giltecnologia.gerenciadorfinanceiro.model

import java.math.BigDecimal
import java.util.Calendar


class Transacao(valor : BigDecimal,
                categoria: String,
                data: Calendar) {


    val valor  = valor
    val categoria = categoria
    val data = data

}