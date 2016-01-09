package src;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Optional;

/**
 * Created by krister on 20.11.15.
 */
public class LoginScreen {
    TextField Username;
    PasswordField Password;
    Button loginButton;
    Button registerButton;
    Button anonymousButton;
    Stage stage = null;
    Scene nextScene = null;

    LoginScreen(Stage stage, Scene nextScene) {
        this.stage = stage;
        this.nextScene = nextScene;
        setupScene();
        setupLogin();
        setupRegister();
        setupAnonymous();
    }

    private void setupScene() {
        VBox vbox = new VBox();
        Scene scene = new Scene(vbox);

        Label l1 = new Label("Username");
        Username = new TextField();
        Label l2 = new Label("Password");
        Password = new PasswordField();
        loginButton = new Button("Log in");
        registerButton = new Button("Register");
        anonymousButton = new Button("Anonymous");
        vbox.getChildren().addAll(l1, Username, l2, Password, loginButton, registerButton, anonymousButton);

        stage.setScene(scene);
        stage.show();
    }

    private void setupLogin() {
        loginButton.setOnAction(event -> {
            String nimi = Username.getText();
            String p = Password.getText();
            if (nimi.equals("") || p.equals("")){
                Alert error = new Alert(Alert.AlertType.INFORMATION);
                error.setTitle("Error");
                error.setHeaderText("Empty username or password");
                Optional<ButtonType> closeResponse = error.showAndWait();
                if (!ButtonType.OK.equals(closeResponse.get())){
                    error.getHeaderText();
                    return;
                }
            }
            Database a = new Database();
            boolean result = a.login(nimi, p);
            a.sulgeYhendus();
            if (result) {
                stage.setScene(nextScene);
            }else {
                Alert error = new Alert(Alert.AlertType.INFORMATION);
                error.setTitle("Error");
                error.setHeaderText("Invalid username or password");
                Optional<ButtonType> closeResponse = error.showAndWait();
                if (!ButtonType.OK.equals(closeResponse.get())){
                    error.getHeaderText();
                }
            }
        });
    }

    private void setupRegister() {
        registerButton.setOnAction(event -> {
            String nimi = Username.getText();
            String p = Password.getText();
            Database a = new Database();
            a.registreeriKasutaja(nimi, p);
            a.sulgeYhendus();
        });
    }

    private void setupAnonymous(){
        anonymousButton.setOnAction(event -> {
            stage.setScene(nextScene);
        });
    }
}