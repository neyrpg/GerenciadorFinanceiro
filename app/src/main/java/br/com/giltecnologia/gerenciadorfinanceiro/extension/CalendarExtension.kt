package br.com.giltecnologia.gerenciadorfinanceiro.extension

import java.util.*

fun Calendar
        .formatoBrasileiro(): String? {
    val dateFormat = java.text.SimpleDateFormat("dd/MM/yyyy")
    return dateFormat.format(time)
}