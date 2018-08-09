package controllers

import constant.Library
import controller.CreateConnectControler
import extend.closeConnect
import extend.openConnect
import gui.View
import javafx.application.Platform
import javafx.collections.ObservableList
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.util.Callback
import util.DataBaseManager

abstract class MainActivityFrameWork(){
    val connectLevel: Int = 1

    val connectRootLists: ArrayList<String> = ArrayList<String>()
    val connectItems = HashMap<String,TreeItem<String>>()

    open fun createConnect(){
        showCreateConnectDialog()
    }

    protected fun showCreateConnectDialog(){
        val stage = View.stage
        val loader = FXMLLoader(View::class.java.getResource("/create_connect.fxml"))
        val createNewConnectView = loader.load<Parent>()

        val dialogStage: Stage = Stage()
        dialogStage.title = "Create New ConnectionInfo"
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

    protected fun addTreeView(treeView: TreeView<*>){
        treeView.cellFactory = Callback { return@Callback TreeItemCell() }

        val root = treeView.root
        val children = root.children as ObservableList<TreeItem<String>>

        Library.CONNECTION_INFOS.values!!.forEach {
            if (!connectRootLists.contains(it.connectName)){
                connectRootLists.add(it.connectName)

                val treeItem = TreeItem(it.connectName)
                connectItems.put(it.connectName,treeItem)
                children.add(treeItem)
            }
        }
    }
    protected fun openConnect(treeView: TreeView<*>, isMenu:Boolean){
        (treeView as TreeView<String>).openConnect(isMenu)
    }



    protected fun closeConnect(treeView: TreeView<*>){
        (treeView as TreeView<String>).closeConnect(true)
    }

    protected fun close() {
        DataBaseManager.closeAllConnection()
        View.stage?.close()
    }

    protected fun exit() {
        Platform.exit()
    }



    abstract fun refresh();
}