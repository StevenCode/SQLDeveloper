package model

data class Connection(
        var connectName: String,
        var connectIP: String,
        var port: Int,
        var username: String,
        var password: String,
        var savePassword: Boolean
)