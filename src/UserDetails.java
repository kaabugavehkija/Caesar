package src;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * UserDetails class is mainly based on KristerV solution:
 * https://github.com/KristerV/javaHarjutused/blob/lahendused/src/teema3/SQL_Login/UserDetails.java
 * Some additional improvements have been added.
 */
public class UserDetails {
    private String Username;
    private Stage stage = null;
    private Button updateButton;
    private Button logoutButton;
    private HashMap<String, String> andmed;
    Scene nextScene = null;

    public UserDetails(String kasutajaSisse, Stage stage, Scene nextScene) {
        this.stage = stage;
        this.nextScene = nextScene;
        Username = kasutajaSisse;
        setupStage();
        setupUpdate();
        setupLogout();
    }

    private void setupLogout() {
        logoutButton.setOnAction(event -> {
            stage.close();
            new LoginScreen(stage, nextScene);
        });
    }

    private void setupUpdate() {

    }

    private void setupStage() {
        TilePane tile = new TilePane();
        tile.setPrefColumns(2);
        Scene scene = new Scene(tile);
        stage.setScene(scene);

        Database a = new Database();
        andmed = a.getUser(Username);

        TextField kasutajanimiField = new TextField(andmed.get("username"));
        PasswordField paroolField = new PasswordField();
        paroolField.setText(andmed.get("password"));
        TextField fullnameField = new TextField(andmed.get("fullname"));

        updateButton = new Button("Renew data");

        updateButton.setOnAction(event -> {
            HashMap<String, String> uuedAndmed = new HashMap<String, String>();
            uuedAndmed.put("username", kasutajanimiField.getText());
            uuedAndmed.put("password", paroolField.getText());
            uuedAndmed.put("fullname", fullnameField.getText());

            a.uuendaKasutajaAndmeid(uuedAndmed);
            a.sulgeYhendus();
            stage.close();
            new UserDetails(kasutajanimiField.getText(), stage, nextScene);
        });

        Label l2 = new Label("Username");
        Label l3 = new Label("Password");
        Label l4 = new Label("Real name");

        logoutButton = new Button("Log out");
        tile.getChildren().addAll(l2, kasutajanimiField, l3, paroolField, l4, fullnameField, logoutButton, updateButton);

        stage.show();
    }
}
