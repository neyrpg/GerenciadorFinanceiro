package br.com.giltecnologia.gerenciadorfinanceiro.extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.*

fun BigDecimal.formatoMoedaBrasileiro() : String? {
    var decimalFormat = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return  decimalFormat.format(this)

}