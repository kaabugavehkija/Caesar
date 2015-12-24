package src;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.*;
import java.util.Optional;
import java.util.logging.Logger;

import static javafx.scene.text.FontWeight.BOLD;

/**
 * Created by Mario on 11/21/2015.
 */

public class Siffer_FX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Stage setup
        primaryStage.setTitle("Cipher/Decipher");
        VBox layout = new VBox();

        //Title to centre
        Label title = new Label("Caesar cipher");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font("ARIAL", 30));
        title.setTextFill(Paint.valueOf("WHITE"));
        BorderPane bp = new BorderPane();
        bp.setCenter(title);
        layout.getChildren().add(bp);

        layout.setSpacing(5);
        layout.setStyle("-fx-background-color:#336699;");
        Scene scene = new Scene(layout, 750, 650);

        primaryStage.setScene(scene);

        //http://stackoverflow.com/questions/20094620/set-icon-on-stage-in-javafx
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("Caesar.jpg")));
        primaryStage.show();

        //close confirmation message
        //http://stackoverflow.com/questions/29997368/javafx-confusing-event-handling-on-system-exit
        primaryStage.setOnCloseRequest((WindowEvent we) ->
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirmation");
            a.setHeaderText("Do you really want to leave?");
            Optional<ButtonType> closeResponse = a.showAndWait();
            if (!ButtonType.OK.equals(closeResponse.get())) {
                we.consume();
            }
        });

        //Visuals
        VBox body = new VBox();
        Label alphabet = new Label("Alphabet");
        TextField alphabetText = new TextField();
        alphabet.setFont(Font.font(String.valueOf(BOLD), 18));
        alphabet.setTextFill(Paint.valueOf("WHITE"));

        Label plainText = new Label("Plain text");
        plainText.setFont(Font.font(String.valueOf(BOLD), 18));
        plainText.setTextFill(Paint.valueOf("WHITE"));
        TextArea plainTextArea = new TextArea();

        Label textCiphered = new Label("Ciphered/deciphered text");
        textCiphered.setFont(Font.font(String.valueOf(BOLD), 18));
        textCiphered.setTextFill(Paint.valueOf("WHITE"));
        TextArea textCipheredDeciphered = new TextArea();
        Button cipher = new Button("Cipher");
        Button deciph = new Button("Decipher");
        Button clear = new Button("Clear result");
        Button clearPlain = new Button("Clear plain text");
        Button browse = new Button("Choose file");
        Button save = new Button("Save to file");

        //body alignment
        body.getChildren().addAll(alphabet, alphabetText, plainText, plainTextArea, textCiphered, textCipheredDeciphered);
        body.setSpacing(5);
        //buttons horizontally
        HBox buttons = new HBox();
        buttons.getChildren().addAll(cipher, deciph, clear, clearPlain, browse, save);
        buttons.setSpacing(10);

        //key choicebox
        Label chooseKey = new Label("Key");
        chooseKey.setFont(Font.font(String.valueOf(BOLD), 18));
        chooseKey.setTextFill(Paint.valueOf("WHITE"));
        ChoiceBox<Integer> keyCb = new ChoiceBox<Integer>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
        keyCb.getSelectionModel().selectFirst(); //default choose the first from list

        //alphabet choicebox
        Label chooseAlphabet = new Label("Choose alphabet or insert one");
        chooseAlphabet.setFont(Font.font(String.valueOf(BOLD), 18));
        chooseAlphabet.setTextFill(Paint.valueOf("WHITE"));
        ChoiceBox<String> alphabetCb = new ChoiceBox<String>(FXCollections.observableArrayList("ABCDEFGHIJKLMNOPQRSTUVWXYZ", "ABCDEFGHIJKLMNOPQRSŠZŽTUVWÕÄÖÜXY", "غضذخثتشقصفعسنملكيطحزوهدجبا", "\n" +
                "АБВГЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЮЯ"));
        alphabetCb.getSelectionModel().selectFirst(); //default choose the first from list

        //choiceboxes horizontally
        layout.getChildren().add(keyCb);
        HBox align = new HBox();
        align.setSpacing(15);
        align.getChildren().addAll(chooseKey, keyCb, chooseAlphabet, alphabetCb);

        FileChooser filechooser = new FileChooser();

        layout.getChildren().addAll(align, body, buttons);

        //button actions
        cipher.setOnAction(event -> {
            if (textCipheredDeciphered.getText().isEmpty()) {
                String textCipher = plainTextArea.getText();
                int key = keyCb.getValue();
                String alphabetChoice = new String();
                if (alphabetText.getText() != null && !alphabetText.getText().isEmpty()) {
                    alphabetChoice = alphabetText.getText().toUpperCase();
                } else {
                    alphabetChoice = alphabetCb.getValue();
                }
                textCipheredDeciphered.appendText(Cipher.cipher(textCipher, key, alphabetChoice));
            } else {
                textCipheredDeciphered.clear(); //to function after clear
                String textCipher = plainTextArea.getText();
                int key = keyCb.getValue();
                String alphabetChoice = new String();
                if (alphabetText.getText() != null && !alphabetText.getText().isEmpty()) {
                    alphabetChoice = alphabetText.getText().toUpperCase();
                } else {
                    alphabetChoice = alphabetCb.getValue();
                }
                textCipheredDeciphered.appendText(Cipher.cipher(textCipher, key, alphabetChoice));
            }

        });

        deciph.setOnAction(event -> {
            if (textCipheredDeciphered.getText().isEmpty()) {
                String textDeciph = plainTextArea.getText();
                int key = keyCb.getValue();
                String alphabetChoice = new String();
                if (alphabetText.getText() != null && !alphabetText.getText().isEmpty()) {
                    alphabetChoice = alphabetText.getText().toUpperCase();
                } else {
                    alphabetChoice = alphabetCb.getValue();
                }
                textCipheredDeciphered.appendText(Decipher.decipher(textDeciph, key, alphabetChoice));
            } else {
                textCipheredDeciphered.clear();
                String textDeciph = plainTextArea.getText();
                int key = keyCb.getValue();
                String alphabetChoice = new String();
                if (alphabetText.getText() != null && !alphabetText.getText().isEmpty()) {
                    alphabetChoice = alphabetText.getText().toUpperCase();
                } else {
                    alphabetChoice = alphabetCb.getValue();
                }
                textCipheredDeciphered.appendText(Decipher.decipher(textDeciph, key, alphabetChoice));

            }

        });

        clear.setOnAction(event -> {
            textCipheredDeciphered.clear();
        });

        clearPlain.setOnAction(event -> {
            plainTextArea.clear();
        });
        browse.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(primaryStage);

            String line = null;
            try {
                FileReader fileReader = new FileReader(file); //read from file
                BufferedReader bufferedReader = new BufferedReader(fileReader); //file to buffereReader

                while ((line = bufferedReader.readLine()) != null) {
                    line += line;
                    plainTextArea.appendText(line);
                }
                bufferedReader.close(); //close file

            } catch (IOException ex) {
                System.out.println("Error reading file");
            }
        });
        save.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            //file extension filter
            //http://java-buddy.blogspot.com.ee/2015/03/javafx-example-save-textarea-to-file.html
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(primaryStage);
            if (file != null) {
                SaveFile(textCipheredDeciphered.getText(), file);
            }

        });
        //http://java-buddy.blogspot.com.ee/2015/03/javafx-example-save-textarea-to-file.html
    }

    private void SaveFile(String content, File file) {
        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(" ");
        }
    }
}