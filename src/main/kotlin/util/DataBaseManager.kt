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
                    connectPool.put(connectName, connection)
                }

            } catch (e: SQLException) {
                e.printStackTrace()
                return false
            }

        }
        return true
    }

        public fun querySQL(connectName: String, sql: String) {
            var sql = sql
            var connection: Connection? = connectPool.get(connectName) ?: return

            sql = "SELECT datname FROM pg_database;"


            val statement = connection!!.createStatement()
            val resultSet = statement.executeQuery(sql)
            while (resultSet.next()) {
                val tableName = resultSet.getString(1)
                println(tableName)
            }
        }

        public fun closeConnection(connectionName: String): Boolean {
            var success: Boolean = false

            val connection = connectPool.get(connectionName) ?: return success

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