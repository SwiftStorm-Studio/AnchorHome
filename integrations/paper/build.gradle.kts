import net.minecrell.pluginyml.bukkit.BukkitPluginDescription

plugins {
    id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
    id("xyz.jpenilla.run-paper") version "2.3.0"
    id("io.papermc.paperweight.userdev") version "1.7.1"
}

dependencies {
    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("org.bstats:bstats-base:3.0.2")
}

bukkit {
    name = "SimpleHome"
    version = rootProject.version.toString()
    description = "Multilingual home plugins developed with an emphasis on lightweight"
    author = "Lars"
    main = "net.rk4z.s1.simpleHome.SimpleHome"
    foliaSupported = false
    apiVersion = "1.21"
    load = BukkitPluginDescription.PluginLoadOrder.STARTUP
    contributors = listOf("Lars", "cotrin_d8")
    depend = listOf("Kotlin")
    softDepend = listOf("LuckPerms")

    //TODO("Add a commands and permissions section")
}

tasks.named<Jar>("jar") {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(sourceSets.main.get().output)
    from({
        configurations.runtimeClasspath.get().map { file ->
            if (file.isDirectory) file else { zipTree(file) }
        }
    })
}
