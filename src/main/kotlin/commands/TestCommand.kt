package top.xuansu.mirai.exchangeRateHelper.commands

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import top.xuansu.mirai.exchangeRateHelper.HelperMain
import top.xuansu.mirai.exchangeRateHelper.HelperMain.dataFolder

class TestCommand : SimpleCommand(
    owner = HelperMain,
    primaryName = "test",
) {
    @Handler
    suspend fun CommandSender.handle() {
        val rateJsonFolder = dataFolder.resolve("exchange_rates")
        if (!rateJsonFolder.exists()) {
            rateJsonFolder.mkdirs()
        }

        sendMessage("NICE!")
    }
}