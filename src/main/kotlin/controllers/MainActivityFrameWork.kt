package controllers

import constant.Library
import controller.CreateConnectControler
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
import util.DataBaseManager

abstract class MainActivityFrameWork(){
    val connectLevel: Int = 1

    val connectRootLists: ArrayList<String> = ArrayList<String>()

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

    protected fun addTreeView(children: ObservableList<Any>){
        Library.CONNECTION_INFOS.values!!.forEach {
            if (!connectRootLists.contains(it.connectName)){
                connectRootLists.add(it.connectName)

                val treeItem = TreeItem(it.connectName)
                treeItem.children.add(TreeItem("a"))
                treeItem.children.add(TreeItem("b"))

                children.add(treeItem)
            }
        }
    }

    protected fun openConnect(treeView: TreeView<*>){
        val selectedItem = treeView.selectionModel.selectedItem ?: return

        if(treeView.getTreeItemLevel(selectedItem)!=connectLevel){
            return
        }

        if (selectedItem.isExpanded) return

        if (DataBaseManager.connect(selectedItem.value.toString())) {
            selectedItem.isExpanded = true
        }else{
            showCannotConnectDialog()
        }
    }

    abstract fun showCannotConnectDialog()

    protected fun openOrCloseConnect(treeView: TreeView<*>) {
        val selectedItem = treeView.selectionModel.selectedItem
        println(selectedItem.isExpanded)
        //先展开后
        if (!selectedItem.isExpanded) {
            closeConnect(treeView)
        }else{
            openConnect(treeView)
        }
    }
    protected fun closeConnect(treeView: TreeView<*>){
        val selectedItem = treeView.selectionModel.selectedItem ?: return

        if (!selectedItem.isExpanded) return

        if (DataBaseManager.closeConnection(selectedItem.value.toString())) {
            selectedItem.isExpanded = false
        }else{
            closeConnectFailDailog()
        }
    }

    abstract fun closeConnectFailDailog()

    protected fun close() {
        DataBaseManager.closeAllConnection()
        View.stage?.close()
    }

    protected fun exit() {
        Platform.exit()
    }



    abstract fun refresh();
}