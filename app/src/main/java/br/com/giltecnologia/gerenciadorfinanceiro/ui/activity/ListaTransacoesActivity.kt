package br.com.giltecnologia.gerenciadorfinanceiro.ui.activity

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import  br.com.giltecnologia.gerenciadorfinanceiro.R
import br.com.giltecnologia.gerenciadorfinanceiro.adapter.TransacoesAdapter
import br.com.giltecnologia.gerenciadorfinanceiro.enums.TipoTransacaoEnum
import br.com.giltecnologia.gerenciadorfinanceiro.extension.formatoBrasileiro
import br.com.giltecnologia.gerenciadorfinanceiro.model.Transacao
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {


    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)
        //findViewById<ListView>(R.id.lista_transacoes_listview)//

        //val lista = transacoesExemplo()

        // val adapter = ArrayAdapter( this, android.R.layout.simple_list_item_1, lista)

        atualizarList()

        configuraResumo()

        lista_transacoes_adiciona_receita.setOnClickListener {

            val viewDialog = LayoutInflater.from(this).inflate(
                    R.layout.form_transacao,
                    window.decorView as ViewGroup,
                    false
            )

            val ano = 2017
            val mes = 11
            val dia = 12

            //viewDialog.form_transacao_valor.
            viewDialog.form_transacao_data.setText(Calendar.getInstance().formatoBrasileiro())
            viewDialog.form_transacao_data.setOnClickListener{
                DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, ano, mes, dia ->
                val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano,mes,dia)
                    viewDialog.form_transacao_data.setText(dataSelecionada.formatoBrasileiro())
                },ano,mes,dia).show()
            }

            val adapterCategoria = ArrayAdapter.createFromResource(this, R.array.categorias_de_receita, android.R.layout.simple_spinner_dropdown_item)
            viewDialog.form_transacao_categoria.adapter = adapterCategoria


           AlertDialog.Builder(this)
                   .setTitle(R.string.adiciona_receita)
                   .setView(viewDialog)
                   .setNegativeButton("Cancelar",null)
                   .setPositiveButton("Adicionar", DialogInterface.OnClickListener { dialogInterface, i ->
                       val valor = viewDialog.form_transacao_valor.text.toString()
                       val data = viewDialog.form_transacao_data.text.toString()
                       val categoria = viewDialog.form_transacao_categoria.selectedItem.toString()

                       val valorBigDecimal = BigDecimal(valor)
                       val sdf = SimpleDateFormat("dd/MM/yyyy")
                       val dataFormatada = sdf.parse(data)

                       val dataConvertida = Calendar.getInstance()
                       dataConvertida.time = dataFormatada

                       val transacao = Transacao(tipo = TipoTransacaoEnum.RECEITA, valor = valorBigDecimal, data = dataConvertida, categoria = categoria)
                       //Toast.makeText(this,"${transacao.categoria} - ${transacao.valor}", Toast.LENGTH_LONG).show()
                       adicionarTransacao(transacao)
                       lista_transacoes_adiciona_menu.close(true)
                   })
                   .show()
        }

    }

    private fun adicionarTransacao(transacao: Transacao) {
        transacoes.add(transacao)
        configuraResumo()
        atualizarList()
    }

    private fun atualizarList() {
        lista_transacoes_listview.adapter = TransacoesAdapter(this, transacoes)
    }

    private fun configuraResumo() {
        val viewResumo = window.decorView
        ResumoView(viewResumo, transacoes).adicionaDespesa()
        ResumoView(viewResumo, transacoes).adicionaReceita()
        ResumoView(viewResumo, transacoes).adicionaTotal()

    }


    private fun transacoesExemplo(): List<Transacao> {
        val lista = listOf(Transacao(BigDecimal(100.0), "Comida", TipoTransacaoEnum.DESPESA, Calendar.getInstance()),
                Transacao(BigDecimal(200.0), "Cobustível", TipoTransacaoEnum.DESPESA, Calendar.getInstance()),
                Transacao(BigDecimal(300.0), "Passeio pelo parque da cidade em Brasília", TipoTransacaoEnum.DESPESA, Calendar.getInstance()),
                Transacao(valor = BigDecimal(5000.0), tipo = TipoTransacaoEnum.RECEITA, data = Calendar.getInstance(), categoria = "Salário") // NAMED PARAMETER = Não importa a ordem
                // ele irá atribuir o valor ao atributo nomeado
        )
        return lista
    }
}