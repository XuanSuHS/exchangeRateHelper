package top.xuansu.mirai.exchangeRateHelper

object Func {

    //检查输入币种是否正确
    fun checkCurrency(currency: String): String {
        return when (currency) {
            in Config.currencyAltName["USD"]!! -> {
                "USD"
            }

            in Config.currencyAltName["GBP"]!! -> {
                "GBP"
            }

            in Config.currencyAltName["EUR"]!! -> {
                "EUR"
            }

            in Config.currencyAltName["JPY"]!! -> {
                "JPY"
            }

            else -> {
                "USD"
            }

        }
    }

    //检查输入银行是否正确
    fun checkBank(bank: String): String {
        return when (bank) {
            in Config.bankAltName["BOC"]!! -> {
                "BOC"
            }

            in Config.bankAltName["ICBC"]!! -> {
                "ICBC"
            }

            else -> {
                "BOC"
            }
        }
    }

    //
}