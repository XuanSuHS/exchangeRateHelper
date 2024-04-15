package top.xuansu.mirai.exchangeRateHelper.functions

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import top.xuansu.mirai.exchangeRateHelper.HelperMain.dataFolder
import java.nio.file.Files
import java.nio.file.Path
import java.time.LocalDate

fun getLocalFile(date: LocalDate, bank: String): Pair<Boolean, Path> {

    val fileName = "${bank}-${date}"

    val exchangeRateJsonFolder = dataFolder.resolve("exchange_rates")
    if (!exchangeRateJsonFolder.exists()) { exchangeRateJsonFolder.mkdirs() }
    val exchangeRateJsonFile = exchangeRateJsonFolder.resolve("${fileName}.json")

    if (!exchangeRateJsonFile.exists()) {
        return false to exchangeRateJsonFile.toPath()
    }

    try {
        Json.parseToJsonElement(Files.readString(exchangeRateJsonFile.toPath())).jsonArray
    } catch (e: Exception) {
        return false to exchangeRateJsonFile.toPath()
    }

    return true to exchangeRateJsonFile.toPath()
}