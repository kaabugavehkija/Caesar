import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Mario on 11/26/2015.
 */
public class failiKirjutamine {
    public static void main(String path) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("tekst");
        String tekst = sc.nextLine();
        File fw = new File("path");
        FileWriter kirjutaja = new FileWriter(fw);
        kirjutaja = new FileWriter(fw);
        kirjutaja.write(tekst);
        kirjutaja.close();
        }
    }
