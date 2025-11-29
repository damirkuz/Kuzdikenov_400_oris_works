package ru.kuzdikenov.fx.chat.view;

import javafx.scene.Parent;
import ru.kuzdikenov.fx.chat.ChatApplication;

public abstract class BaseView {

    private static ChatApplication chatApplication;

    public static ChatApplication getChatApplication() {
        if (chatApplication == null) {
            throw new IllegalStateException("ChatApplication has not been initialized");
        }
        return chatApplication;
    }

    public static void setChatApplication(ChatApplication chatApplication) {
        BaseView.chatApplication = chatApplication;
    }

    public abstract Parent getView();
}