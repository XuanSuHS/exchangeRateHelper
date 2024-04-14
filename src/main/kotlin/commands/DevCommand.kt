package top.xuansu.mirai.exchangeRateHelper.commands

import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.nameCardOrNick
import top.xuansu.mirai.exchangeRateHelper.HelperMain

class DevCommand : SimpleCommand(
    owner = HelperMain,
    primaryName = "dev",
) {
    @Handler
    suspend fun CommandSender.handle() {
        // 获取发送者的信息
        val senderInfo = when (this) {
            is net.mamoe.mirai.contact.Member -> {
                val groupName = this.group.name
                val groupID = this.group.id
                val memberName = this.nameCardOrNick
                "来自群组：$groupName($groupID)，发送者：$memberName"
            }

            is net.mamoe.mirai.contact.User -> {
                val nickname = this.nick
                "来自私聊：$nickname"
            }

            else -> "未知发送者类型"
        }

        // 回复消息
        sendMessage("您的信息：$senderInfo")
    }
}