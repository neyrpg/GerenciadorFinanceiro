package br.com.giltecnologia.gerenciadorfinanceiro.extension


fun String.limitaCaracteresApresentacao() : String? {

    var valor = this
    if(valor.length > 14){
        valor = "${valor.substring(0, 14)}..."
    }

    return valor
}