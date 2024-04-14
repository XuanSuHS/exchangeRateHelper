package top.xuansu.mirai.exchangeRateHelper.functions

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.concurrent.TimeUnit

fun getExchangeRateFromWeb(bank: String): String {
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

    httpClient.newCall(request).execute().use { response ->
        if (response.isSuccessful && response.body != null) {
            return response.body!!.string()
        } else throw IOException("Unexpected code $response")
    }
}