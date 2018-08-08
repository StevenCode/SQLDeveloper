package controllers

import extend.closeConnect
import extend.openConnect
import javafx.scene.control.ContextMenu
import javafx.scene.control.MenuItem
import javafx.scene.control.TreeCell


class TreeItemCell : TreeCell<String>() {


    private val menu = ContextMenu()

    init {
        val openConnectMenu = MenuItem("Open")
        openConnectMenu.setOnAction { treeView.openConnect()}

        val closeConectMenu = MenuItem("Close")
        closeConectMenu.setOnAction { treeView.closeConnect() }

        menu.items.add(openConnectMenu)
        menu.items.add(closeConectMenu)
    }

    override fun updateItem(item: String?, empty: Boolean) {
        super.updateItem(item, empty)

        if (empty) {
            text = null
            graphic = null
        } else {
            text = if (getItem() == null) "" else getItem().toString()
            graphic = treeItem.graphic
            if (treeView.getTreeItemLevel(treeItem) == 1) {
                contextMenu = menu
            }
        }
    }
}