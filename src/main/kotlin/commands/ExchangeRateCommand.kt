package top.xuansu.mirai.exchangeRateHelper.commands

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
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
        var ifErrMessage = ""
        var currency = ""
        var bank = ""

        try {
            currency = checkCurrency(currencyIn)
        } catch (e: IllegalArgumentException) {
            ifErrMessage += "无法匹配${currencyIn}为已知币种\n"
        }

        try {
            bank = checkBank(bankIn)
        } catch (e: IllegalArgumentException) {
            ifErrMessage += "无法匹配${bankIn}为可用银行"
        }

        if (ifErrMessage.isNotEmpty()) {
            sendMessage(ifErrMessage)
            return
        }
        logger.info("${bank}-${currency}")

        val localFileStatus = getLocalFile(today, bank)
        if (localFileStatus.first) {
            logger.info("Check Local file Successful")
            getLocalRate(this, localFileStatus.second,today, currency, bank)
        } else {
            logger.info("Check Local file Failed")
            getOnlineRate(this, localFileStatus.second,today, currency, bank)
        }
    }
}