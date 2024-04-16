package top.xuansu.mirai.exchangeRateHelper.commands

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import top.xuansu.mirai.exchangeRateHelper.HelperMain

class TestCommand : SimpleCommand(
    owner = HelperMain,
    primaryName = "test",
) {
    @Handler
    suspend fun CommandSender.handle() {
        sendMessage("wow")
    }
}