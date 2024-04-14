package top.xuansu.mirai.exchangeRateHelper.functions

import top.xuansu.mirai.exchangeRateHelper.Config

fun checkCurrency(currency: String): String {
    for ( i in Config.currencyAltName ) {
        if (currency.uppercase() in i.value) return i.key
    }

    val commonSub = findKeyWithLongestCommonSubsequencePercentage(currency.uppercase(), Config.currencyAltName)
    if ( commonSub.second >= 40 ) {
        return commonSub.first!!
    }

    throw IllegalArgumentException()
}