package top.xuansu.mirai.exchangeRateHelper

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("config") {
    val currencyAltName: Map<String, List<String>> by value(mapOf(
        "USD" to listOf("USD", "US", "美元", "美国"),
        "EUR" to listOf("EUR", "EU", "欧元", "欧盟"),
        "GBP" to listOf("GBP", "GB", "英镑", "英国"),
        "JPY" to listOf("JPY", "JP", "日元", "日本"),
        "AED" to listOf("AED", "AE","阿联酋"),
        "AUD" to listOf("AUD", "AU", "澳元", "澳大利亚"),
        "BRL" to listOf("BRL", "BR", "巴西"),
        "CAD" to listOf("CAD", "CA", "加拿大元", "加拿大")
    ))

    val bankAltName: Map<String, List<String>> by value(mapOf(
        "BOC" to listOf("BOC","中行","中国银行"),
        "ICBC" to listOf("ICBC","工行","工商银行","中国工商银行"),
        "CCB" to listOf("CCB","建行","建设银行","中国建设银行"),
        "CMB" to listOf("CMB","招行","招商银行")
    ))
}