package top.xuansu.mirai.exchangeRateHelper

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.value

object Config : AutoSavePluginConfig("config") {
    val defaultBank: String by value("BOC")
    val defaultCurrency: String by value("USD")

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
        "ABCHINA" to listOf("ABC","ABCHINA","农行","农业银行","中国农业银行"),
        "BANKCOMM" to listOf("CMM","BANKCOMM","交行","交通银行"),
        "CEBBANK" to listOf("CEB","CEBBANK","光大","光大银行"),
        "ECITIC" to listOf("ECI","ECITIC","中信","中信银行"),
        "CIB" to listOf("CIB","兴业","兴业银行"),
        "SPDB" to listOf("SPDB","浦发","浦发银行"),
        "CCB" to listOf("CCB","建行","建设银行","中国建设银行"),
        "CMBCHINA" to listOf("CMB","CMBCHINA","招行","招商银行")
    ))

    val bankShortNameCN: Map<String, String> by value(mapOf(
        "BOC" to "中行",
        "ICBC" to "工行",
        "ABCHINA" to "农行",
        "BANKCOMM" to "交行",
        "CEBBANK" to "光大",
        "ECITIC" to "中信",
        "CIB" to "兴业",
        "SPDB" to "浦发",
        "CCB" to "建行",
        "CMBCHINA" to "招行"
    ))
}