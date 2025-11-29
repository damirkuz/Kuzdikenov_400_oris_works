package ru.kuzdikenov.fx.chat;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ru.kuzdikenov.fx.chat.service.CommandService;
import ru.kuzdikenov.fx.chat.view.BaseView;
import ru.kuzdikenov.fx.chat.view.ChatView;
import ru.kuzdikenov.fx.chat.view.StartPageView;

public class ChatApplication extends Application {
    public String username;
    private ChatView chatView;
    private StartPageView startPageView;
    private BorderPane root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Chat");
        stage.setOnCloseRequest(event -> System.exit(0));
        BaseView.setChatApplication(this);

        startPageView = new StartPageView();
        chatView = new ChatView(new CommandService());
        root = new BorderPane();

        Scene scene = new Scene(root, 400, 400);
        stage.setScene(scene);
        stage.show();

        setView(startPageView);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ChatView getChatView() {
        return chatView;
    }

    public StartPageView getStartPageView() {
        return startPageView;
    }

    public void setView(BaseView view) {
        root.setCenter(view.getView());
    }

    public void appendMessage(String message) {
        chatView.append(message);
    }

    public String getUsername() {
        return username;
    }
}