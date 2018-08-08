package controllers

import model.ConnectionInfo
import constant.Library
import javafx.scene.control.CheckBox
import javafx.scene.control.TextField
import util.DataBaseManager

abstract class CreateConnectFrameWork() {

    val dialog = CommonTextDialog();

    open fun addNewConnection(connectName: TextField, connectIp: TextField, port: TextField, dbName: TextField,
                              username: TextField, password: TextField, savePassword: CheckBox) {
        val connection = ConnectionInfo(connectName.text, connectIp.text, port.text.toInt(), dbName.text,
                username.text, password.text, savePassword.isSelected)

        Library.CONNECTION_INFOS!!.put(connectName.text,connection)
    }

    protected fun testConnect(connectName: TextField, connectIp: TextField, port: TextField, dbName: TextField,
                              username: TextField, password: TextField, savePassword: CheckBox) {
        val connection = ConnectionInfo(connectName.text, connectIp.text, port.text.toInt(), dbName.text,
                username.text, password.text, savePassword.isSelected)

        if (DataBaseManager.connect(connection)) {
            dialog.showCreateConnectDialog("connect success...")
        }else{
            dialog.showCreateConnectDialog("connect faild...")
        }
    }


}