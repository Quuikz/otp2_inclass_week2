package org.otp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ResourceBundle bundle = ResourceBundle.getBundle("org.otp.messages", Locale.US);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/otp/fuel-calculator-view.fxml"), bundle);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle(bundle.getString("app.title"));
        stage.setScene(scene);
        stage.setMinWidth(540);
        stage.setMinHeight(360);
        stage.show();
    }
}