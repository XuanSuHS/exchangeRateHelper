package top.xuansu.mirai.exchangeRateHelper

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("config") {
    val preferLocalData: Boolean by value(true)
    val autoFuzzyMatching: Boolean by value(false)
    val defaultBank: String by value("BOC")
    val defaultCurrency: String by value("USD")

    val autoRefreshBankList: List<String> by value(
        listOf(
            "BOC", "ICBC"
        )
    )
}