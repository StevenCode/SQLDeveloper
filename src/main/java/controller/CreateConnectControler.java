package controller;

import controllers.CreateConnectFrameWork;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




public class CreateConnectControler extends CreateConnectFrameWork {

    @FXML
    private TextField connectName;
    @FXML
    private TextField connectIp;
    @FXML
    private TextField port;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private CheckBox savePassword;


    private Stage dialogStage;

    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void confirm(ActionEvent actionEvent) {
        addNewConnection(connectName, connectIp, port, username, password, savePassword);
        dialogStage.close();
    }


    public void cancel(ActionEvent actionEvent) {
        dialogStage.close();
    }


    public void testConnect(ActionEvent actionEvent) {
        //TODO 测试网络弹窗
    }
}
