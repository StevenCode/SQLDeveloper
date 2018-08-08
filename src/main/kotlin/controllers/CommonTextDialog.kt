package controllers

import controller.CommonTextDialogControler
import gui.View
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Modality
import javafx.stage.Stage

class CommonTextDialog {

    public fun showCreateConnectDialog(text: String){
        val stage = View.stage
        val loader = FXMLLoader(View::class.java.getResource("/common_text_dialog.fxml"))
        val connectResultDialog = loader.load<Parent>()

        val dialogStage: Stage = Stage()
        dialogStage.title = "Connect"
        dialogStage.initModality(Modality.WINDOW_MODAL)
        dialogStage.isResizable = false
        dialogStage.scene = Scene(connectResultDialog)
        dialogStage.initOwner(stage)

        // Set the dialog into the controller
        val controller: CommonTextDialogControler = loader.getController()
        controller.setText(text)
        controller.dialogStage = dialogStage

        dialogStage.showAndWait()

    }
}