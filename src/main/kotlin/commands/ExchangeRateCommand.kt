package top.xuansu.mirai.exchangeRateHelper.commands

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import top.xuansu.mirai.exchangeRateHelper.Config
import top.xuansu.mirai.exchangeRateHelper.DataHolder
import top.xuansu.mirai.exchangeRateHelper.HelperMain
import top.xuansu.mirai.exchangeRateHelper.HelperMain.logger
import top.xuansu.mirai.exchangeRateHelper.HelperMain.rateFolder
import top.xuansu.mirai.exchangeRateHelper.functions.*
import java.time.LocalDate
import java.time.ZoneId

class ExchangeRateCommand : SimpleCommand(
    owner = HelperMain,
    primaryName = "exchangerate",
    secondaryNames = arrayOf("hl", "er", "汇率"),
    description = "获取汇率信息"
) {
    @Handler
    suspend fun CommandSender.handle(currencyIn: String = "", bankIn: String = "", refreshNow: String = "") {

        val zoneId = ZoneId.of("Asia/Shanghai")
        val today = LocalDate.now(zoneId)
        var findErrMessage = ""
        var currency = ""
        var bank = ""

        //TODO:自由指定汇率日期
        val date = today

        try {
            currency = if (currencyIn.isBlank()) {
                checkCurrency(Config.defaultCurrency)
            } else {
                checkCurrency(currencyIn)
            }

        } catch (e: IllegalArgumentException) {
            val commonSub =
                findKeyWithLongestCommonSubsequencePercentage(currencyIn.uppercase(), DataHolder.currencyAltName)

            when {
                commonSub.second >= 40 && Config.autoFuzzyMatching -> {
                    currency = commonSub.first!!
                }

                commonSub.second >= 40 && !Config.autoFuzzyMatching -> {
                    findErrMessage += "无法匹配${currencyIn}为已知币种"
                    findErrMessage += "，您想找的是否为：${commonSub.first!!}"
                }

                else -> {
                    findErrMessage += "无法匹配${currencyIn}为已知币种"
                }
            }
        }

        try {
            bank = if (bankIn.isBlank()) {
                checkBank(Config.defaultBank)
            } else {
                checkBank(bankIn)
            }

        } catch (e: IllegalArgumentException) {
            val commonSub = findKeyWithLongestCommonSubsequencePercentage(bankIn.uppercase(), DataHolder.bankAltName)

            when {
                commonSub.second >= 40 && Config.autoFuzzyMatching -> {
                    bank = commonSub.first!!
                }

                commonSub.second >= 40 && !Config.autoFuzzyMatching -> {
                    if (findErrMessage.isNotEmpty()) {
                        findErrMessage += "\n"
                    }
                    findErrMessage += "无法匹配${bankIn}为可用银行"
                    findErrMessage += "，您想找的是否为 ${DataHolder.bankShortNameCN[commonSub.first!!]}：${commonSub.first!!}"
                }

                else -> {
                    if (findErrMessage.isNotEmpty()) {
                        findErrMessage += "\n"
                    }
                    findErrMessage += "无法匹配${bankIn}为可用银行"
                }
            }
        }

        if (findErrMessage.isNotEmpty()) {
            sendMessage(findErrMessage)
            return
        }

        val rateFile = rateFolder.resolve("${bank}-${date}.json")

        val localFileStatus = checkLocalFile(rateFile)

        if (!localFileStatus.first || refreshNow.lowercase() in listOf("yes", "y") || !Config.preferLocalData) {
            try {
                downloadRateDataToFile(rateFile, bank)
            } catch (e: Exception) {
                sendMessage("出错了：${e}")
                return
            }
        } else {
            logger.info("Local file exists, using it.")
        }

        getRateFromFile(this, localFileStatus.second, date, currency, bank)
    }
}