package controller;

import controllers.MainActivityFrameWork;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;



public class ActivityMain extends MainActivityFrameWork {


    @FXML
    private TreeItem connectionList;

    @FXML
    private TreeView treeView;

    @FXML
    protected void createConnect(ActionEvent actionEvent) {
        createConnect();
    }

    @FXML
    protected void openConnect(ActionEvent actionEvent) {
        openConnect(treeView,true);
    }

    @FXML
    protected void closeConnect(ActionEvent actionEvent) {
        closeConnect(treeView);
    }

    @FXML
    protected void close(ActionEvent actionEvent) {
        close();
    }

    @FXML
    protected void exit(ActionEvent actionEvent) {
        exit();
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
        addTreeView(treeView);
    }



    public void treeViewClicked(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 2) {
            openConnect(treeView,false);
        }
    }
}
