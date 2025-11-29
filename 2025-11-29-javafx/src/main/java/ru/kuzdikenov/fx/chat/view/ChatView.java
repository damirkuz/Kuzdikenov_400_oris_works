package ru.kuzdikenov.fx.chat.view;

import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import ru.kuzdikenov.fx.chat.model.CommandAction;
import ru.kuzdikenov.fx.chat.model.CommandResult;
import ru.kuzdikenov.fx.chat.service.CommandService;


public class ChatView extends BaseView {

    private AnchorPane root;
    private TextArea conversation;
    private TextArea input;
    private final CommandService commandService;

    public ChatView(CommandService commandService) {
        this.commandService = commandService;
    }

    @Override
    public Parent getView() {
        if (root == null) {
            createView();
        }
        return root;
    }

    public void append(String message) {
        if (message != null) {
            conversation.appendText(message + "\n");
        }
    }

    private void createView() {
        root = new AnchorPane();

        conversation = new TextArea();
        conversation.setEditable(false);
        conversation.setWrapText(true);

        AnchorPane.setLeftAnchor(conversation, 10.0);
        AnchorPane.setRightAnchor(conversation, 10.0);
        AnchorPane.setTopAnchor(conversation, 10.0);

        input = new TextArea();
        input.setMaxHeight(50);

        AnchorPane.setLeftAnchor(input, 10.0);
        AnchorPane.setRightAnchor(input, 10.0);
        AnchorPane.setBottomAnchor(input, 10.0);


        input.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER) {
                event.consume();

                String message = input.getText().trim();
                if (message.isEmpty()) return;

                input.clear();

                append(getChatApplication().getUsername() + ": " + message);

                CommandResult commandResult = commandService.handle(message);

                if (CommandAction.EXIT.equals(commandResult.action())) {
                    getChatApplication().setView(new StartPageView());
                } else {
                    append("server: " + commandResult.showedMessage());
                }
            }
        });

        root.getChildren().addAll(conversation, input);
    }
}