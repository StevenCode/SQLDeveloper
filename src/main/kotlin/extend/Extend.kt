package extend

import controllers.CommonTextDialog
import javafx.scene.control.TreeItem
import javafx.scene.control.TreeView
import util.DataBaseManager

fun  TreeView<String>.openConnect(){
    val selectedItem = selectionModel.selectedItem ?: return

    if(getTreeItemLevel(selectedItem)!=1){
        return
    }

    if (selectedItem.children.size != 0) {
        return
    }

    if (DataBaseManager.connect(selectedItem.value.toString())) {
        val tableGroup = DataBaseManager.queryTables(selectedItem.value.toString())

        tableGroup.forEach { if(selectedItem!!.children.add(TreeItem(it))) return@forEach }

        selectedItem.isExpanded = true
    }else{
        CommonTextDialog().showCreateConnectDialog("connect faled...");
    }
}

fun  TreeView<String>.closeConnect(){
    val selectedItem = selectionModel.selectedItem ?: return

    if (selectedItem.children.size == 0) {
        return
    }

    if (DataBaseManager.closeConnection(selectedItem.value.toString())) {
        selectedItem.children.removeAll(selectedItem.children)
    }else{
        CommonTextDialog().showCreateConnectDialog("connect faled...");
    }
}

