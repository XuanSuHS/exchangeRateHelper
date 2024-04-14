package top.xuansu.mirai.exchangeRateHelper.commands

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import top.xuansu.mirai.exchangeRateHelper.Config
import top.xuansu.mirai.exchangeRateHelper.HelperMain
import top.xuansu.mirai.exchangeRateHelper.functions.findKeyWithLongestCommonSubsequencePercentage

class TestCommand : SimpleCommand(
    owner = HelperMain,
    primaryName = "test",
) {
    @Handler
    suspend fun CommandSender.handle(arg: String) {

        val result = findKeyWithLongestCommonSubsequencePercentage(arg.uppercase(), Config.currencyAltName)
        sendMessage("最长匹配项为: $result")
    }
}