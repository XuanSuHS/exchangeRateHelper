package top.xuansu.mirai.exchangeRateHelper.functions

import top.xuansu.mirai.exchangeRateHelper.DataHolder

fun checkCurrency(currency: String): String {
    for ( i in DataHolder.currencyAltName ) {
        if (currency.uppercase() in i.value) return i.key
    }

    throw IllegalArgumentException()
}