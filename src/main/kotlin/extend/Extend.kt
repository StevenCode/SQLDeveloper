package extend

import controllers.CommonTextDialog
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import util.DataBaseManager

fun  TreeView<String>.openConnect(isMenu:Boolean){
    val selectedItem = selectionModel.selectedItem ?: return


    if (selectedItem.children.size != 0) {
        return
    }

    if(getTreeItemLevel(selectedItem)==1){
        if (DataBaseManager.connect(selectedItem.value.toString())) {
            val tableGroup = DataBaseManager.queryDbs(selectedItem.value.toString())

            tableGroup.forEach { if(selectedItem!!.children.add(TreeItem(it))) return@forEach }

            selectedItem.isExpanded = true
        }else{
            CommonTextDialog().showCreateConnectDialog("connect faled...");
        }
    }

    if(getTreeItemLevel(selectedItem)==2&&!isMenu){

        val connectName = selectedItem.parent.value.toString()

        if (DataBaseManager.connect(connectName,selectedItem.value.toString())) {
            val tableGroup = DataBaseManager.queryTables(connectName, selectedItem.value.toString())

            tableGroup.forEach { if(selectedItem!!.children.add(TreeItem(it))) return@forEach }

            selectedItem.isExpanded = true
        }else{
            CommonTextDialog().showCreateConnectDialog("connect faled...");
        }
    }


}

fun  TreeView<String>.closeConnect(isMenu:Boolean){
    val selectedItem = selectionModel.selectedItem ?: return

    if (selectedItem.children.size == 0) {
        return
    }

    if(getTreeItemLevel(selectedItem)==1){
        if (DataBaseManager.closeConnection(selectedItem.value.toString())) {
            selectedItem.children.removeAll(selectedItem.children)
        }else{
            CommonTextDialog().showCreateConnectDialog("connect faled...");
        }
    }

    if(getTreeItemLevel(selectedItem)==2&&!isMenu){

        val connectName = selectedItem.parent.value.toString()

        if (DataBaseManager.closeConnection(connectName, selectedItem.value.toString())) {
            selectedItem.children.removeAll(selectedItem.children)
        }else{
            CommonTextDialog().showCreateConnectDialog("connect faled...");
        }
    }

}

