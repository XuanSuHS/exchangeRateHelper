package top.xuansu.mirai.exchangeRateHelper.functions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import top.xuansu.mirai.exchangeRateHelper.HelperMain.rateFolder
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.StandardOpenOption
import java.util.concurrent.TimeUnit


suspend fun downloadRateDataToFile(rateFile: File, bank: String) {

    val httpClient = OkHttpClient.Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .callTimeout(15, TimeUnit.SECONDS)
        .build()

    val cnyRateUrl = HttpUrl.Builder()
        .scheme("https")
        .host("www.cnyrate.com")
        .addPathSegments("api/105-32")
        .addQueryParameter("bankCode",bank)
        .addQueryParameter("showapi_appid","79245")
        .addQueryParameter("showapi_sign","3e27cbfff34e4bc1b84c74af6d44d2ab")
        .build()

    val request = Request.Builder()
        .url(cnyRateUrl)
        .build()

    val response = httpClient.newCall(request).execute().use { response ->
        if (response.isSuccessful && response.body != null) {
            response.body!!.string()
        } else throw IOException("Unexpected code $response")
    }

    val responseObject = Json.parseToJsonElement(response).jsonObject["showapi_res_body"]!!.jsonObject

    //TODO:设置每天上午11点为分界线
    //网站找不到
    if (responseObject["ret_code"]!!.jsonPrimitive.content != "0") {
        throw IOException(responseObject["remark"]!!.jsonPrimitive.content)
    }

    val exchangeRateArray = responseObject["codeList"]!!.jsonArray

    //检查文件夹是否存在
    if (!rateFolder.exists()) {
        rateFolder.mkdirs()
    }

    //写入文件
    val localExchangeRateFile = rateFile.toPath()
    withContext(Dispatchers.IO) {
        Files.deleteIfExists(localExchangeRateFile)
        Files.writeString(
            localExchangeRateFile, exchangeRateArray.toString(), StandardCharsets.UTF_8,
            StandardOpenOption.CREATE, StandardOpenOption.WRITE
        )
    }
}