package top.xuansu.mirai.exchangeRateHelper.functions

import top.xuansu.mirai.exchangeRateHelper.Config

fun checkBank(bank: String): String {
    for ( i in Config.bankAltName ) {
        if (bank.uppercase() in i.value) return i.key
    }

    val commonSub = findKeyWithLongestCommonSubsequencePercentage(bank.uppercase(), Config.bankAltName)
    if ( commonSub.second >= 40 ) {
        return commonSub.first!!
    }

    throw IllegalArgumentException()
}