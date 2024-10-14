package net.rk4z.s1.anchorHome

import net.rk4z.s1.anchorHome.libs.Metrics
import org.bukkit.plugin.java.JavaPlugin
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.yaml.snakeyaml.Yaml
import java.nio.file.Path
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import kotlin.io.path.notExists

class AnchorHome : JavaPlugin() {
    companion object {
        lateinit var instance: AnchorHome
            private set
        lateinit var dataBase: DataBase
            private set
        lateinit var metrics: Metrics
            private set

        const val ID = "anchorhome"
        val logger: Logger = LoggerFactory.getLogger(AnchorHome::class.java.simpleName)
    }

    val version = description.version
    val authors: MutableList<String> = description.authors
    val pluginDes = description.description

    private val executor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    private var enableMetrics: Boolean? = true
    private val configFile: Path = dataFolder.resolve("config.yml").toPath()
    private val langDir = dataFolder.resolve("lang")
    private val yaml = Yaml()
    private val availableLang = listOf("ja", "en")

    override fun onLoad() {
        instance = getPlugin(AnchorHome::class.java)
        dataBase = DataBase(this)
        metrics = Metrics(this, 23627)
    }

    override fun onEnable() {

    }

    override fun onDisable() {

    }

    private fun initializeDirectories() {
        if (dataFolder.toPath().notExists()) {
            dataFolder.mkdirs()
        }
        if (configFile.notExists()) {
            saveResource("config.yml", false)
        }
        if (!langDir.exists()) {
            langDir.mkdirs()
        }
        availableLang.forEach {
            val langFile = langDir.resolve("$it.yml")
            if (langFile.toPath().notExists()) {
                saveResource("lang/$it.yml", false)
            }
        }
    }
}