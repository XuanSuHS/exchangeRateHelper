package top.xuansu.mirai.exchangeRateHelper.functions

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import java.io.File
import java.nio.file.Files
import java.nio.file.Path

fun checkLocalFile(filePath: File): Pair<Boolean, Path> {

    if (!filePath.exists()) {
        return false to filePath.toPath()
    }

    try {
        Json.parseToJsonElement(Files.readString(filePath.toPath())).jsonArray
    } catch (e: Exception) {
        return false to filePath.toPath()
    }

    return true to filePath.toPath()
}