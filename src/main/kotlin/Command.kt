package top.xuansu.mirai.exchangeRateHelper

import kotlinx.serialization.json.*
import net.mamoe.mirai.console.command.CommandSender
import net.mamoe.mirai.console.command.SimpleCommand
import net.mamoe.mirai.contact.nameCardOrNick

class ExchangeRateCommand : SimpleCommand(
    owner = HelperMain,
    primaryName = "exchangerate",
    secondaryNames = arrayOf("hl", "er"),
    description = "获取汇率信息"
) {
    @Handler
    suspend fun CommandSender.handle(currencyIn: String = "", bankIn: String = "") {

        val currency = Func.checkCurrency(currencyIn)

        val bank = Func.checkBank(bankIn)

        val response = Web.getExchangeRate(bank)

        val responseObject = try {
            Json.parseToJsonElement(response).jsonObject["showapi_res_body"]!!.jsonObject
        } catch (e: Exception) {
            sendMessage("出错了：${e.message}")
            return
        }

        val exchangeRateArray = responseObject["codeList"]!!.jsonArray

        for (i in exchangeRateArray) {
            if (i.jsonObject["code"]!!.jsonPrimitive.content == currency) {
                sendMessage(
                    "CNY - $currency 汇率\n"
                        .plus("时间：${responseObject["day"]!!.jsonPrimitive.content}\n")
                        .plus("卖出价-汇：${i.jsonObject["hui_out"]!!.jsonPrimitive.content}\n")
                        .plus("卖出价-钞：${i.jsonObject["chao_out"]!!.jsonPrimitive.content}\n")
                        .plus("买入价-汇：${i.jsonObject["hui_in"]!!.jsonPrimitive.content}\n")
                        .plus("买入价-钞：${i.jsonObject["chao_in"]!!.jsonPrimitive.content}\n")
                )
                return
            } else continue
        }
        sendMessage("此银行不存在此币种")
    }
}
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