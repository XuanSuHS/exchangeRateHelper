package top.xuansu.mirai.exchangeRateHelper.commands

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import top.xuansu.mirai.exchangeRateHelper.Config
import top.xuansu.mirai.exchangeRateHelper.HelperMain
import top.xuansu.mirai.exchangeRateHelper.HelperMain.logger
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
    suspend fun CommandSender.handle(currencyIn: String = "", bankIn: String = "") {

        val zoneId = ZoneId.of("Asia/Shanghai")
        val today = LocalDate.now(zoneId)
        var findErrMessage = ""
        var currency = ""
        var bank = ""


        try {
            currency = if (currencyIn.isBlank()) {
                checkCurrency(Config.defaultCurrency)
            } else {
                checkCurrency(currencyIn)
            }

        } catch (e: IllegalArgumentException) {
            val commonSub =
                findKeyWithLongestCommonSubsequencePercentage(currencyIn.uppercase(), Config.currencyAltName)

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
            val commonSub = findKeyWithLongestCommonSubsequencePercentage(bankIn.uppercase(), Config.bankAltName)

            when {
                commonSub.second >= 40 && Config.autoFuzzyMatching -> {
                    bank = commonSub.first!!
                }

                commonSub.second >= 40 && !Config.autoFuzzyMatching -> {
                    if (findErrMessage.isNotEmpty()) {
                        findErrMessage += "\n"
                    }
                    findErrMessage += "无法匹配${bankIn}为可用银行"
                    findErrMessage += "，您想找的是否为 ${Config.bankShortNameCN[commonSub.first!!]}：${commonSub.first!!}"
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

        val localFileStatus = getLocalFile(today, bank)
        if (Config.preferLocalData && localFileStatus.first) {
            logger.info("Check Local file Successful")
            getLocalRate(this, localFileStatus.second, today, currency, bank)
        } else {
            getOnlineRate(this, localFileStatus.second, today, currency, bank)
        }
    }
}