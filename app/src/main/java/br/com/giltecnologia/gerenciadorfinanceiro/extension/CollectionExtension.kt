package br.com.giltecnologia.gerenciadorfinanceiro.extension

import java.math.BigDecimal

/**
 * Created by Gilciney Marques on 10/12/2017.
 */


/**
 * Returns the sum of all values produced by [selector] function applied to each element in the collection.
 */
public inline fun <T> Iterable<T>.sumByBigDecimal(selector: (T) -> BigDecimal): BigDecimal {
    var sum: BigDecimal = BigDecimal.ZERO
    for (element in this) {
        sum = sum.plus(selector(element))
    }
    return sum
}