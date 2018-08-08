package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import javafx.stage.Stage;



public class CommonTextDialogControler {

    private Stage dialogStage;

    @FXML
    private Label text;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setText(String text) {
        this.text.setText(text);
    }


    public void confirm(ActionEvent actionEvent) {
        dialogStage.close();
    }
}
