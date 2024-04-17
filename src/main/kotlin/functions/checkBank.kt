package top.xuansu.mirai.exchangeRateHelper.functions

import top.xuansu.mirai.exchangeRateHelper.DataHolder

fun checkBank(bank: String): String {
    for ( i in DataHolder.bankAltName ) {
        if (bank.uppercase() in i.value) return i.key
    }

    throw IllegalArgumentException()
}