
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.logging.Logger;

/**
 * Created by Mario on 11/21/2015.
 */

/**
 * 1. Loo sisse logimise ekraan (ainult visuaal)
 * 2. Määra üks võimalik kasutaja ja parool (andmebaasi veel ei kasuta)
 * 3. Ebaõnnestunud katse näitab kasutajale errorit
 * 4. Õnnestunud katse puhul vaheta pilt uue vastu (kasvõi roheline ring), .
 */
public class SifFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Lava seadistamine
        VBox layout = new VBox();
        layout.setSpacing(5);
        Scene scene = new Scene(layout, 600, 600, Color.AQUA);


        primaryStage.setScene(scene);
        scene.setFill(Paint.valueOf("AQUA"));
        primaryStage.show();
        //primaryStage.setOnCloseRequest(event -> System.exit(0));

        //Visuaalid
        Label ROT = new Label("ROT");
        TextField nihe = new TextField();
        Label tekstiVali = new Label("Tekst toores");
        TextArea sisestusTekst = new TextArea();
        Label tekstiValiSif = new Label("Tulemus");
        TextArea sisestusTekstSif = new TextArea();
        Button sif = new Button("Sifreeri");
        Button desif = new Button("Desifreeri");
        Button clear = new Button("Clear tulemus");
        Button browse = new Button("Vali fail");
        Button salvesta = new Button("Salvesta tulemus faili");

        FileChooser filechooser =new FileChooser();


        layout.getChildren().addAll (ROT, nihe, tekstiVali, sisestusTekst,tekstiValiSif,sisestusTekstSif, sif, desif,clear,browse, salvesta);

        //mida nupud teevad
        sif.setOnAction(event -> {
            String sifreerimiseks = sisestusTekst.getText();
            int rot = Integer.parseInt(nihe.getText());
            sisestusTekstSif.appendText(CaesarSiffer.siffer(sifreerimiseks, rot));

        });
        desif.setOnAction(event -> {
            String desifreerimiseks = sisestusTekst.getText();
            int rot = Integer.parseInt(nihe.getText());
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