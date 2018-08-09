package util

import constant.Library
import model.ConnectionInfo
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

object DataBaseManager {

    private val connectPool = HashMap<String, Connection>()
    private val urlTemplate: String = "jdbc:postgresql://{address}/{dbName}?useUnicode=true&amp;characterEncoding=utf8"

    fun DataBaseManager() {
        Class.forName("org.postgresql.Driver")
    }

    public fun connect(connectName: String): Boolean {
        val connectionInfo = Library.CONNECTION_INFOS.get(connectName)
        if (connectionInfo == null) {
            Library.CONNECTION_INFOS
        }
        return this.connect(connectionInfo!!)

    }

    public fun connect(connectName: String, dbName: String): Boolean {
        val connectionInfo = Library.CONNECTION_INFOS.get(connectName)
        connectionInfo!!.dbName = dbName
        return this.connect(connectionInfo!!)

    }

    public fun connect(connectionInfo: ConnectionInfo): Boolean {
        var connectUrl: String;

        connectionInfo?.apply {
            connectUrl = urlTemplate.replace("{address}", "$connectIP:$port")
            connectUrl = connectUrl.replace("{dbName}", dbName)

            try {
                val connection = DriverManager.getConnection(connectUrl, username, password)

                if (!connection.isClosed) {
                    connectPool.put(connectUrl, connection)
                }

            } catch (e: SQLException) {
                e.printStackTrace()
                return false
            }

        }
        return true
    }

    public fun queryDbs(connectName: String): List<String> {
        val dbGroup = ArrayList<String>()

        val connectionInfo = Library.CONNECTION_INFOS.get(connectName)
        var connectUrl: String = ""


        connectionInfo?.apply {
            connectUrl = urlTemplate.replace("{address}", "$connectIP:$port")
            connectUrl = connectUrl.replace("{dbName}", dbName)

        }

        var connection: Connection = connectPool.get(connectUrl)!!

        var sql = "SELECT datname FROM pg_database;"


        val statement = connection!!.createStatement()
        val resultSet = statement.executeQuery(sql)
        while (resultSet.next()) {
            val tableName = resultSet.getString(1)
            dbGroup.add(tableName)
        }

        return dbGroup
    }

    public fun queryTables(connectName: String,dbName: String): List<String> {
        val tableGroup = ArrayList<String>()


        val connectionInfo = Library.CONNECTION_INFOS.get(connectName)
        var connectUrl: String = ""


        connectionInfo?.apply {
            connectUrl = urlTemplate.replace("{address}", "$connectIP:$port")


        }
        connectUrl = connectUrl.replace("{dbName}", dbName)

        var connection: Connection = connectPool.get(connectUrl)!!

        var sql = "select tablename from pg_tables where schemaname='public'"


        val statement = connection!!.createStatement()
        val resultSet = statement.executeQuery(sql)
        while (resultSet.next()) {
            val tableName = resultSet.getString(1)
            tableGroup.add(tableName)
        }

        return tableGroup
    }


    public fun closeConnection(connectionName: String): Boolean {
        var success: Boolean = false

        val connectionInfo = Library.CONNECTION_INFOS.get(connectionName)
        var connectUrl: String = ""


        connectionInfo?.apply {
            connectUrl = urlTemplate.replace("{address}", "$connectIP:$port")
            connectUrl = connectUrl.replace("{dbName}", dbName)

        }

        val connection = connectPool.get(connectUrl) ?: return success

        try {
            if (connection.isClosed) {
                connection.close()
            }
            success = true
        } catch (e: SQLException) {
            return false
        }
        return success
    }

    public fun closeConnection(connectionName: String,dbName: String): Boolean {
        var success: Boolean = false

        val connectionInfo = Library.CONNECTION_INFOS.get(connectionName)
        var connectUrl: String = ""


        connectionInfo?.apply {
            connectUrl = urlTemplate.replace("{address}", "$connectIP:$port")


        }
        connectUrl = connectUrl.replace("{dbName}", dbName)

        val connection = connectPool.get(connectUrl) ?: return success

        try {
            if (connection.isClosed) {
                connection.close()
            }
            success = true
        } catch (e: SQLException) {
            return false
        }
        return success
    }

    public fun closeAllConnection() {
        connectPool.values.forEach {
            if (!it.isClosed) it.close()
        }
    }
}