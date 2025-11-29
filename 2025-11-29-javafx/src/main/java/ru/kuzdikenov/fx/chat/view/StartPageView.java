package ru.kuzdikenov.fx.chat.view;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class StartPageView extends BaseView {

    private AnchorPane root;
    private VBox box;
    private TextField username;
    private Button start;


    @Override
    public Parent getView() {
        if (root == null) {
            createView();
        }
        return root;
    }

    public void createView() {
        root = new AnchorPane();
        box = new VBox();

        Label usernameLabel = new Label("Username:");
        username = new TextField();
        start = new Button("Start");

        start.setOnAction(event -> {
            if (start == event.getSource()) {
                getChatApplication().setUsername(username.getText());
                getChatApplication().setView(getChatApplication().getChatView());
            }
        });

        box.getChildren().addAll(
                usernameLabel, username, start
        );
        root.getChildren().addAll(box);
    }

}