
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.logging.Logger;

import static javafx.scene.text.FontWeight.BOLD;

/**
 * Created by Mario on 11/21/2015.
 */

public class SifFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Lava seadistamine
        VBox layout = new VBox();
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
        Button browse = new Button("Vali fail");
        Button salvesta = new Button("Salvesta tulemus faili");

        //choicebox
        Label ValiNihe = new Label("Vali Nihe");
        ValiNihe.setAlignment(Pos.CENTER);
        ValiNihe.setFont(Font.font(String.valueOf(BOLD), 20));
        ValiNihe.setTextFill(Paint.valueOf("WHITE"));
        ChoiceBox<Integer> cb = new ChoiceBox<Integer>(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13));
        cb.getValue(); //mis on valitud
        //cb.getSelectionModel().selectedItemProperty().addListener(ObservableValue obs);
        layout.getChildren().add(cb);


        FileChooser filechooser =new FileChooser();

        layout.getChildren().addAll (ValiNihe, tekstiVali, sisestusTekst,tekstiValiSif, sisestusTekstSif, sif, desif,clear,browse, salvesta);

        //nupu tegevus
        sif.setOnAction(event -> {
            String sifreerimiseks = sisestusTekst.getText();
            int rot = cb.getValue();
            sisestusTekstSif.appendText(CaesarSiffer.siffer(sifreerimiseks, rot));

        });
        desif.setOnAction(event -> {
            String desifreerimiseks = sisestusTekst.getText();
            int rot = cb.getValue();
            sisestusTekstSif.appendText(CaesarSiffer.desiffer(desifreerimiseks, rot));

        });
        clear.setOnAction(event -> {
            sisestusTekstSif.clear();
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