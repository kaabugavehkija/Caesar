package MMSiffer;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.logging.Logger;

import static javafx.scene.text.FontWeight.BOLD;

/**
 * Created by Mario on 11/21/2015.
 */

public class Siffer_FX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Lava seadistamine
        primaryStage.setTitle("Sifreerija/desifreerija");
        VBox layout = new VBox();

        //pealkiri keskele
        Label pealkiri = new Label("Caesar cipher");
        pealkiri.setTextAlignment(TextAlignment.CENTER);
        pealkiri.setFont(Font.font("ARIAL", 30));
        pealkiri.setTextFill(Paint.valueOf("WHITE"));
        BorderPane bp = new BorderPane();
        bp.setCenter(pealkiri);
        layout.getChildren().add(bp);

        layout.setSpacing(5);
        layout.setStyle("-fx-background-color:#336699;;");
        Scene scene = new Scene(layout, 600, 600);

        primaryStage.setScene(scene);
        primaryStage.show();
        //primaryStage.setOnCloseRequest(event -> System.exit(0));

        //Visuaalid
        Label tekstiVali = new Label("Tekst toores");
        tekstiVali.setFont(Font.font(String.valueOf(BOLD), 20));
        tekstiVali.setTextFill(Paint.valueOf("WHITE"));
        TextArea sisestusTekst = new TextArea();
        Label tekstiValiSif = new Label("Tulemus");
        tekstiValiSif.setFont(Font.font(String.valueOf(BOLD),20));
        tekstiValiSif.setTextFill(Paint.valueOf("WHITE"));
        TextArea sisestusTekstSif = new TextArea();
        Button sif = new Button("Sifreeri");
        Button desif = new Button("Desifreeri");
        Button clear = new Button("Clear tulemus");
        Button clearSisestus = new Button("Clear tekst");
        Button browse = new Button("Vali fail");
        Button salvesta = new Button("Salvesta tulemus faili");

        //paigutame nupud horisontaalselt
        HBox hbox = new HBox();
        hbox.getChildren().addAll(sif, desif, clear, clearSisestus, browse, salvesta);
        hbox.setSpacing(10);

        //choicebox
        Label ValiNihe = new Label("Vali Nihe");
        ValiNihe.setFont(Font.font(String.valueOf(BOLD), 20));
        ValiNihe.setTextFill(Paint.valueOf("WHITE"));
        ChoiceBox<Integer> cb = new ChoiceBox<Integer>(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
        cb.getValue(); //choiceboxi vaartus
        //paigutame choiceboxi horisontaalselt
        layout.getChildren().add(cb);
        HBox choice = new HBox();
        choice.setSpacing(10);
        choice.getChildren().addAll(ValiNihe, cb);

        FileChooser filechooser =new FileChooser();

        layout.getChildren().addAll (choice, tekstiVali, sisestusTekst,tekstiValiSif, sisestusTekstSif, hbox);

        //nupu tegevus
        sif.setOnAction(event -> {
            String sifreerimiseks = sisestusTekst.getText();
            int rot = cb.getValue();
            sisestusTekstSif.appendText(Sifreerimine_Caesar.siffer(sifreerimiseks, rot));

        });
        desif.setOnAction(event -> {
            String desifreerimiseks = sisestusTekst.getText();
            int rot = cb.getValue();
            sisestusTekstSif.appendText(Desifreerimine_Caesar.desiffer(desifreerimiseks, rot));

        });
        clear.setOnAction(event -> {
            sisestusTekstSif.clear();
        });
        clearSisestus.setOnAction(event -> {
            sisestusTekst.clear();
        });
        browse.setOnAction(event -> {
            File file = new FileChooser().showOpenDialog(primaryStage);

            String line = null;
            try {
                FileReader fileReader = new FileReader(file); //loeme failist
                BufferedReader bufferedReader = new BufferedReader(fileReader); //fail buffereReaderisse

                while ((line = bufferedReader.readLine()) != null) {
                    line += line;
                    sisestusTekst.appendText(line);
                }
                bufferedReader.close(); //sulgeme faili

            } catch (IOException ex) {
                System.out.println("Error reading file");
            }
        });
        salvesta.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();

            //faililaiendite filter
            //http://java-buddy.blogspot.com.ee/2015/03/javafx-example-save-textarea-to-file.html
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(primaryStage);
            if(file != null){
                SaveFile(sisestusTekstSif.getText(), file);
            }

        });
        //http://java-buddy.blogspot.com.ee/2015/03/javafx-example-save-textarea-to-file.html
    }private void SaveFile(String content, File file){
        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger("faili ei ole");
        }
    }}