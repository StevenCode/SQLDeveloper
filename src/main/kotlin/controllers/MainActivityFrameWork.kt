package controllers

import gui.View
import javafx.fxml.FXMLLoader
import javafx.scene.Parent

abstract class MainActivityFrameWork(){
    open fun createConnect(){
        showCreateConnectDialog()
    }

    protected fun showCreateConnectDialog(){
        val stage = View.stage
        val parent = FXMLLoader.load<Parent>(View::class.java.getResource("/activity_main.fxml"))
    }
}