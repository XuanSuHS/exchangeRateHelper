package top.xuansu.mirai.exchangeRateHelper

import net.mamoe.mirai.console.command.CommandManager
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.plugin.jvm.reloadPluginConfig
import net.mamoe.mirai.console.plugin.jvm.reloadPluginData
import net.mamoe.mirai.utils.info

object HelperMain : KotlinPlugin(
    JvmPluginDescription(
        id = "top.xuansu.mirai.exchangeRateHelper",
        name = "ExchangeRateHelper",
        version = "0.0.1",
    ) {
        author("XuanSu")
    }
) {

    override fun onEnable() {
        //----------------------
        //初始化命令
        ExchangeRateCommand().register()
        DevCommand().register()
        //----------------------


        //初始化Config
        reloadPluginConfig(Config)
        reloadPluginData(Data)

        //初始化下载图片
        onStart()

        logger.info { "Plugin loaded" }
    }

    override fun onDisable() {
        CommandManager.INSTANCE.unregisterAllCommands(HelperMain)
        logger.info { "Plugin Unloaded" }
    }
}