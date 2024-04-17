package top.xuansu.mirai.exchangeRateHelper

object DataHolder {
    val currencyAltName = (
        mapOf(
            "USD" to listOf("USD", "US", "美元", "美国"),
            "EUR" to listOf("EUR", "EU", "欧元", "欧盟"),
            "GBP" to listOf("GBP", "GB", "英镑", "英国"),
            "JPY" to listOf("JPY", "JP", "日元", "日本"),
            "AED" to listOf("AED", "AE", "阿联酋"),
            "AUD" to listOf("AUD", "AU", "澳元", "澳大利亚"),
            "BRL" to listOf("BRL", "BR", "巴西","巴西里亚尔"),
            "CAD" to listOf("CAD", "CA", "加拿大元", "加拿大", "加元"),
            "CHF" to listOf("CHF", "瑞士", "瑞士法郎"),
            "DKK" to listOf("DKK", "DK", "丹麦克朗", "丹麦"),
            "HKD" to listOf("HKD", "HK", "港币", "港元", "香港"),
            "IDR" to listOf("IDR", "ID", "印尼", "印尼卢比"),
            "INR" to listOf("INR", "IN", "印度", "印度卢比"),
            "KRW" to listOf("KRW", "KR", "韩元", "韩国","韩国元"),
            "MOP" to listOf("MOP", "MO", "澳门元", "澳门"),
            "MYR" to listOf("MYR", "MY", "马来西亚", "林吉特", "马来元"),
            "NOK" to listOf("NOK", "NO", "挪威", "挪威克朗"),
            "NZD" to listOf("NZD", "NZL", "NZ", "新西兰元", "新西兰"),
            "PHP" to listOf("PHP","PH","菲律宾","菲律宾比索"),
            "RUB" to listOf("RUB","RU","俄罗斯","俄罗斯卢布"),
            "SAR" to listOf("SAR","SA","沙特","沙特里亚尔"),
            "THB" to listOf("THB","TH","泰国","泰国铢","泰铢"),
            "TWD" to listOf("TWD","TW","新台币","台湾"),
            "SGD" to listOf("SGD","SG","新加坡","新加坡元"),
            "TRY" to listOf("TRY","TR","土耳其","土耳其里拉"),
            "ZAR" to listOf("ZAR","ZA","南非","南非兰特"),
        )
    )

    val bankAltName = (
        mapOf(
            "BOC" to listOf("BOC", "中行", "中国银行"),
            "ICBC" to listOf("ICBC", "工行", "工商银行", "中国工商银行"),
            "ABCHINA" to listOf("ABC", "ABCHINA", "农行", "农业银行", "中国农业银行"),
            "BANKCOMM" to listOf("CMM", "BANKCOMM", "交行", "交通银行"),
            "CEBBANK" to listOf("CEB", "CEBBANK", "光大", "光大银行"),
            "ECITIC" to listOf("ECI", "ECITIC", "中信", "中信银行"),
            "CIB" to listOf("CIB", "兴业", "兴业银行"),
            "SPDB" to listOf("SPDB", "浦发", "浦发银行"),
            "CCB" to listOf("CCB", "建行", "建设银行", "中国建设银行"),
            "CMBCHINA" to listOf("CMB", "CMBCHINA", "招行", "招商银行")
        )
    )

    val bankShortNameCN = (
        mapOf(
            "BOC" to "中行",
            "ICBC" to "工行",
            "ABCHINA" to "农行",
            "BANKCOMM" to "交行",
            "CEBBANK" to "光大银行",
            "ECITIC" to "中信银行",
            "CIB" to "兴业银行",
            "SPDB" to "浦发银行",
            "CCB" to "建行",
            "CMBCHINA" to "招行"
        )
    )
}