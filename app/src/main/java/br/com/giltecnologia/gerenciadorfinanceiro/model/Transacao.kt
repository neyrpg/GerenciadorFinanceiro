package br.com.giltecnologia.gerenciadorfinanceiro.model

import br.com.giltecnologia.gerenciadorfinanceiro.enums.TipoTransacaoEnum
import java.math.BigDecimal
import java.util.Calendar


class Transacao(val valor : BigDecimal,
                val categoria: String,
                val tipo : TipoTransacaoEnum,
                val data: Calendar)