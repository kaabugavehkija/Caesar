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
    TextField kasutajanimi;
    PasswordField parool;
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

        Label l1 = new Label("Kasutajanimi");
        kasutajanimi = new TextField();
        Label l2 = new Label("Parool");
        parool = new PasswordField();
        loginButton = new Button("logi sisse");
        registerButton = new Button("registreeri");
        anonymousButton = new Button("anonymous");
        vbox.getChildren().addAll(l1, kasutajanimi, l2, parool, loginButton, registerButton, anonymousButton);

        stage.setScene(scene);
        stage.show();
    }

    private void setupLogin() {
        loginButton.setOnAction(event -> {
            String nimi = kasutajanimi.getText();
            String p = parool.getText();
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
            String nimi = kasutajanimi.getText();
            String p = parool.getText();
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