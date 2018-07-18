package controllers

import model.Connection
import constant.Library
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField

abstract class CreateConnectFrameWork() {
    open fun addNewConnection(connectName: TextField, connectIp: TextField, port: TextField,
                              username: TextField, password: TextField, savePassword: CheckBox) {
        val connection = Connection(connectName.text, connectIp.text, port.text.toInt(),
                username.text, password.text, savePassword.isSelected)

        Library.connections!!.add(connection)
    }
}