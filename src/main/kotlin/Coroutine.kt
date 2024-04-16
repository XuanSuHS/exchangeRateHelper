package top.xuansu.mirai.exchangeRateHelper

import kotlinx.coroutines.*
import top.xuansu.mirai.exchangeRateHelper.HelperMain.logger
import top.xuansu.mirai.exchangeRateHelper.HelperMain.rateFolder
import top.xuansu.mirai.exchangeRateHelper.functions.downloadRateDataToFile
import java.time.LocalDate
import java.time.ZoneId
import kotlin.random.Random


object HelperCoroutine {

    fun onStart() {
        //检查文件夹是否存在
        if (!rateFolder.exists()) {
            rateFolder.mkdirs()
        }
    }

    private val IOScope = CoroutineScope(Dispatchers.IO)

    fun runScope() {
        IOScope.launch {
            Config.autoRefreshBankList.forEach {
                logger.info("refreshing $it")
                val zoneId = ZoneId.of("Asia/Shanghai")
                val today = LocalDate.now(zoneId)
                val rateFile = rateFolder.resolve("${it}-${today}.json")
                downloadRateDataToFile(rateFile, it)
                delay(String.format("%.3f", Random.nextDouble(2.0, 10.0)).toDouble().times(1000).toLong())
            }
            delay(1000 * 60 * 61)
            runScope()
            return@launch
        }
    }

    fun stopScope() {
        IOScope.cancel()
    }
}