pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    plugins {
        val kotlinVersion: String by settings

        kotlin("jvm") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
    }
}

rootProject.name = "SimpleHome"

include("integrations")

include("integrations:bungee")
include("integrations:paper")
include("integrations:velocity")