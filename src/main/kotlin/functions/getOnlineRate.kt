package top.xuansu.mirai.exchangeRateHelper.functions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import net.mamoe.mirai.console.command.ConsoleCommandSender.sendMessage
import top.xuansu.mirai.exchangeRateHelper.Config
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardOpenOption
import java.time.LocalDate

suspend fun getOnlineRate(filePath: Path,date: LocalDate,currency: String, bank: String) {

    val response = getExchangeRateFromWeb(bank)

    val responseObject = try {
        Json.parseToJsonElement(response).jsonObject["showapi_res_body"]!!.jsonObject
    } catch (e: Exception) {
        sendMessage("出错了：${e.message}")
        return
    }

    //网站找不到
    if (responseObject["ret_code"]!!.jsonPrimitive.content != "0") {
        sendMessage("出错了：${responseObject["remark"]!!.jsonPrimitive.content}")
        return
    }

    val exchangeRateArray = responseObject["codeList"]!!.jsonArray

    //写入文件
    val localExchangeRateFile = filePath
    withContext(Dispatchers.IO) {
        Files.deleteIfExists(localExchangeRateFile)
        Files.writeString(localExchangeRateFile, exchangeRateArray.toString(), StandardCharsets.UTF_8,
            StandardOpenOption.CREATE, StandardOpenOption.WRITE)
    }

    for (i in exchangeRateArray) {
        if (i.jsonObject["code"]!!.jsonPrimitive.content == currency) {
            sendMessage(
                "${Config.bankShortNameCN[bank]}${i.jsonObject["name"]!!.jsonPrimitive.content}汇率\n"
                    .plus("时间：${responseObject["day"]!!.jsonPrimitive.content}\n")
                    .plus("卖出 - 汇：${i.jsonObject["hui_out"]!!.jsonPrimitive.content}\n")
                    .plus("卖出 - 钞：${i.jsonObject["chao_out"]!!.jsonPrimitive.content}\n")
                    .plus("买入 - 汇：${i.jsonObject["hui_in"]!!.jsonPrimitive.content}\n")
                    .plus("买入 - 钞：${i.jsonObject["chao_in"]!!.jsonPrimitive.content}")
            )
            return
        } else continue
    }
    sendMessage("此银行不存在此币种")
}