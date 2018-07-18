package controller;

import controllers.MainActivityFrameWork;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.VBox;



public class ActivityMain extends MainActivityFrameWork {


    @FXML
    private VBox connectionList;

    @FXML
    protected void createConnect(ActionEvent actionEvent) {
        createConnect();
    }

    @FXML
    protected void openConnect(ActionEvent actionEvent) {

    }

    @FXML
    protected void closeConnect(ActionEvent actionEvent) {

    }

    @FXML
    protected void close(ActionEvent actionEvent) {

    }

    @FXML
    protected void exit(ActionEvent actionEvent) {

    }

    @FXML
    protected void copy(ActionEvent actionEvent) {
    }

    @FXML
    protected void paste(ActionEvent actionEvent) {
    }

    @FXML
    protected void format(ActionEvent actionEvent) {

    }

    @FXML
    protected void loadQuery(ActionEvent actionEvent) {

    }

    @FXML
    protected void saveQuery(ActionEvent actionEvent) {

    }

    @FXML
    protected void runSQL(ActionEvent actionEvent) {

    }

    @Override
    public void refresh() {
        ObservableList<Node> children = connectionList.getChildren();
        addTreeView(children);
    }

}
