package controllers

import com.jfoenix.controls.JFXTreeView
import constant.Library
import controller.CreateConnectControler
import gui.View
import javafx.collections.ObservableList
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.TreeItem
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.stage.Modality
import javafx.stage.Stage

abstract class MainActivityFrameWork(){
    val connectRootLists: ArrayList<String> = ArrayList<String>()

    open fun createConnect(){
        showCreateConnectDialog()
    }

    protected fun showCreateConnectDialog(){
        val stage = View.stage
        val loader = FXMLLoader(View::class.java.getResource("/create_connect.fxml"))
        val createNewConnectView = loader.load<Parent>()

        val dialogStage: Stage = Stage()
        dialogStage.title = "Create New Connection"
        dialogStage.initModality(Modality.WINDOW_MODAL)
//        dialogStage.initStyle()
        dialogStage.isResizable = false
        dialogStage.scene = Scene(createNewConnectView)
        dialogStage.initOwner(stage)

        // Set the dialog into the controller
        val controller: CreateConnectControler = loader.getController()
        controller.dialogStage = dialogStage

        dialogStage.showAndWait()

        refresh()
    }

    protected fun addTreeView(children: ObservableList<Node>){
        Library.connections!!.forEach {
            if (!connectRootLists.contains(it.connectName)){
                connectRootLists.add(it.connectName)

                val loader = FXMLLoader(View::class.java.getResource("/connect_treeview.fxml"))

                val jFXTreeView = loader.load<JFXTreeView<String>>()
                val treeItem = TreeItem<String>()
                treeItem.setValue(it.connectName)
                treeItem.children.add(TreeItem("aa",ImageView(Image("table.png", 16.0, 16.0, false, false))))
                jFXTreeView.setRoot(treeItem)
                children.add(jFXTreeView)
            }
        }
    }

    abstract fun refresh();
}