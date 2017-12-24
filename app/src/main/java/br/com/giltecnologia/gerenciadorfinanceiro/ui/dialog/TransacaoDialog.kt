package br.com.giltecnologia.gerenciadorfinanceiro.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import br.com.giltecnologia.gerenciadorfinanceiro.R
import br.com.giltecnologia.gerenciadorfinanceiro.enums.TipoTransacaoEnum
import br.com.giltecnologia.gerenciadorfinanceiro.extension.formatoBrasileiro
import br.com.giltecnologia.gerenciadorfinanceiro.model.Transacao
import br.com.giltecnologia.gerenciadorfinanceiro.ui.delegate.AddTransacaoInterface
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Gilciney Marques on 18/12/2017.
 */

class TransacaoDialog(val context: Context, val viewGroup: ViewGroup) {



    val delegate: AddTransacaoInterface = context as AddTransacaoInterface



    fun apresentaDialogTransacao(tipo : TipoTransacaoEnum){

        val viewDialog = configuraDialog(tipo)


        AlertDialog.Builder(context)
                .setTitle(configuraTitulo(tipo))
                .setView(viewDialog)
                .setNegativeButton("Cancelar",null)
                .setPositiveButton("Adicionar", dialogListener(viewDialog, tipo))
                .show()
    }

    private fun configuraTitulo(tipo: TipoTransacaoEnum) : Int{
        if (tipo == TipoTransacaoEnum.RECEITA) {
            return R.string.adiciona_receita
        } else {
            return  R.string.adiciona_despesa
        }
    }

    private fun configuraDialog(tipo: TipoTransacaoEnum): View {
        val viewDialog = LayoutInflater.from(context).inflate(
                R.layout.form_transacao,
                viewGroup,
                false
        )
        val hoje = Calendar.getInstance()

        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        //viewDialog.form_transacao_valor.
        viewDialog.form_transacao_data.setText(Calendar.getInstance().formatoBrasileiro())
        viewDialog.form_transacao_data.setOnClickListener {
            DatePickerDialog(context, DatePickerDialog.OnDateSetListener { datePicker, ano, mes, dia ->
                val dataSelecionada = Calendar.getInstance()
                dataSelecionada.set(ano, mes, dia)
                viewDialog.form_transacao_data.setText(dataSelecionada.formatoBrasileiro())
            }, ano, mes, dia).show()
        }





        val adapterCategoria = ArrayAdapter.createFromResource(context,  configuraAdapterCategoria(tipo), android.R.layout.simple_spinner_dropdown_item)
        viewDialog.form_transacao_categoria.adapter = adapterCategoria
        return viewDialog
    }

    private fun configuraAdapterCategoria(tipo: TipoTransacaoEnum) : Int{
        if (tipo == TipoTransacaoEnum.RECEITA) {
            return R.array.categorias_de_receita
        } else {
            return R.array.categorias_de_despesa
        }
    }

    private fun dialogListener(viewDialog: View, tipo: TipoTransacaoEnum): DialogInterface.OnClickListener {
        return DialogInterface.OnClickListener { dialogInterface, i ->
            val valor = viewDialog.form_transacao_valor.text.toString()
            val data = viewDialog.form_transacao_data.text.toString()
            val categoria = viewDialog.form_transacao_categoria.selectedItem.toString()

            val valorBigDecimal = BigDecimal(valor)
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val dataFormatada = sdf.parse(data)

            val dataConvertida = Calendar.getInstance()
            dataConvertida.time = dataFormatada

            val transacao = Transacao(tipo = tipo, valor = valorBigDecimal, data = dataConvertida, categoria = categoria)
            //Toast.makeText(this,"${transacao.categoria} - ${transacao.valor}", Toast.LENGTH_LONG).show()
            //adicionarTransacao(transacao)
            //lista_transacoes_adiciona_menu.close(true)
            delegate.addTransacao(transacao)

        }
    }


}