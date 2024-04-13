package top.xuansu.mirai.exchangeRateHelper

object Func {

    //检查输入币种是否正确
    fun checkCurrency(currency: String): String {
        return when (currency.uppercase()) {
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

            else -> "ERR"

        }
    }

    //检查输入银行是否正确
    fun checkBank(bank: String): String {
        return when (bank.uppercase()) {
            in Config.bankAltName["BOC"]!! -> {
                "BOC"
            }

            in Config.bankAltName["ICBC"]!! -> {
                "ICBC"
            }

            in Config.bankAltName["CCB"]!! -> {
                "CCB"
            }

            in Config.bankAltName["CMB"]!! -> {
                "CMB"
            }

            else -> "ERR"
        }
    }

    //
}