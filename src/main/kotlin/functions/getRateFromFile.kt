package top.xuansu.mirai.exchangeRateHelper.functions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import net.mamoe.mirai.console.command.CommandSender
import top.xuansu.mirai.exchangeRateHelper.DataHolder
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate

suspend fun getRateFromFile(sender: CommandSender, filePath: Path, date: LocalDate, currency: String, bank: String) {

    val localFile = withContext(Dispatchers.IO) {
        Files.readString(filePath)
    }
    val exchangeRateJson = Json.parseToJsonElement(localFile).jsonObject
    val exchangeRateTime = exchangeRateJson["time"]!!.jsonPrimitive.content
    val exchangeRateArray = exchangeRateJson["codeList"]!!.jsonArray

    for (i in exchangeRateArray) {
        if (i.jsonObject["code"]!!.jsonPrimitive.content == currency) {
            sender.sendMessage(
                "${DataHolder.bankShortNameCN[bank]}${i.jsonObject["name"]!!.jsonPrimitive.content}汇率\n"
                    .plus("时间：${date} ${exchangeRateTime}\n")
                    .plus("汇 - 卖出：${i.jsonObject["hui_out"]!!.jsonPrimitive.content}\n")
                    .plus("钞 - 卖出：${i.jsonObject["chao_out"]!!.jsonPrimitive.content}\n")
                    .plus("汇 - 买入：${i.jsonObject["hui_in"]!!.jsonPrimitive.content}\n")
                    .plus("钞 - 买入：${i.jsonObject["chao_in"]!!.jsonPrimitive.content}")

            )
            return
        } else continue
    }
    sender.sendMessage("${DataHolder.bankShortNameCN[bank]}无法兑换此货币")
}