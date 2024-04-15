package top.xuansu.mirai.exchangeRateHelper

import net.mamoe.mirai.console.command.Command
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.register
import net.mamoe.mirai.console.command.CommandManager.INSTANCE.unregister
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.console.plugin.jvm.reloadPluginConfig
import net.mamoe.mirai.console.plugin.jvm.reloadPluginData
import net.mamoe.mirai.utils.info

object HelperMain : KotlinPlugin(
    JvmPluginDescription(
        id = "top.xuansu.mirai.exchangeRateHelper",
        name = "ExchangeRateHelper",
        version = "0.0.3",
    ) {
        author("XuanSu")
    }
) {
    private val commands: List<Command> by services()

    override fun onEnable() {
        //初始化命令
        commands.forEach { it.register() }


        //初始化Config
        reloadPluginConfig(Config)
        reloadPluginData(Data)

        logger.info { "Plugin loaded" }
    }

    override fun onDisable() {
        for ( command in commands ) command.unregister()
        logger.info { "Plugin Unloaded" }
    }
}