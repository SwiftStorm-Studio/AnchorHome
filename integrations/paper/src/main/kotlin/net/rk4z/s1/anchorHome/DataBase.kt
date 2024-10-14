package net.rk4z.s1.anchorHome

import net.rk4z.s1.anchorHome.AnchorHome.Companion.logger
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class DataBase(private val plugin: AnchorHome) {
    var connection: Connection? = null

    fun connect(): Boolean {
        val url = "jdbc:sqlite:${plugin.dataFolder.absolutePath}/database.db"

        if (connection != null) {
            logger.debug("Connection is already established")
            return true
        }

        try {
            connection = DriverManager.getConnection(url)
            logger.info("Connection established")
            return true
        } catch (e: SQLException) {
            e.message?.let { logger.error(it) }
            return false
        } catch (e: Exception) {
            e.message?.let { logger.error(it) }
            return false
        }
    }

    fun disconnect() {
        try {
            connection?.close()
            logger.info("Connection closed")
        } catch (e: SQLException) {
            e.message?.let { logger.error(it) }
        } catch (e: Exception) {
            e.message?.let { logger.error(it) }
        }
    }

    fun createRequiredTableIfNotExists() {
        val sql = """
            CREATE TABLE IF NOT EXISTS homes (
                player_uuid VARCHAR(36) NOT NULL,
                home_name VARCHAR(50) NOT NULL,
                x DOUBLE NOT NULL,
                y DOUBLE NOT NULL,
                z DOUBLE NOT NULL,
                yaw FLOAT NOT NULL,
                pitch FLOAT NOT NULL,
                world VARCHAR(255) NOT NULL,
                server_name VARCHAR(255),
                PRIMARY KEY (player_uuid, home_name)
            );
        """.trimIndent()

        try {
            connection?.createStatement()?.execute(sql)
            logger.info("Table created")
        } catch (e: SQLException) {
            e.message?.let { logger.error(it) }
        } catch (e: Exception) {
            e.message?.let { logger.error(it) }
        }
    }
}