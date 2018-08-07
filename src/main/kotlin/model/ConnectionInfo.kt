package model

data class ConnectionInfo(
        var connectName: String,
        var connectIP: String,
        var port: Int,
        var dbName:String,
        var username: String,
        var password: String,
        var savePassword: Boolean
)