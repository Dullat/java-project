package com.notes.notesappfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class NoteApp extends Application {

    private BorderPane root;
    private Button loginButton;
    private TextField usernameTextField;
    private TextField passwordTextField;
    private Label warnText;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("noteApp.fxml"));
        root = loader.load();

//        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Note App");
        primaryStage.setScene(new Scene(root, 520, 400));
        primaryStage.setResizable(false);
        primaryStage.show();

        // get login btn directly using its fx id
        loginButton = (Button) loader.getNamespace().get("loginButton");
        usernameTextField = (TextField) loader.getNamespace().get("usernameTextField");
        passwordTextField = (TextField) loader.getNamespace().get("passwordTextField");
        warnText = (Label) loader.getNamespace().get("warnText");


        loginButton.setOnAction(event -> {
            if(usernameTextField.getText().equals("user") && passwordTextField.getText().equals("user")){
                warnText.setText("welcome");
                try {
                    // load main menu FXML
                    FXMLLoader loader2 = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
                    BorderPane mainMenuRoot = loader2.load();

                    // set main menu as center of the root borderpane
                    root.setCenter(mainMenuRoot);
                    root.setLeft(null);
                    root.setRight(null);
                    primaryStage.setResizable(true);
                    primaryStage.setHeight(800);
                    primaryStage.setWidth(1000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else
                warnText.setText("user: user / pass: user");
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
