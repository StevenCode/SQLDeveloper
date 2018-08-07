package controllers

import model.ConnectionInfo
import constant.Library
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField

abstract class CreateConnectFrameWork() {
    open fun addNewConnection(connectName: TextField, connectIp: TextField, port: TextField, dbName: TextField,
                              username: TextField, password: TextField, savePassword: CheckBox) {
        val connection = ConnectionInfo(connectName.text, connectIp.text, port.text.toInt(), dbName.text,
                username.text, password.text, savePassword.isSelected)

        Library.CONNECTION_INFOS!!.put(connectName.text,connection)
    }
}