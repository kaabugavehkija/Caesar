import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Mario on 11/25/2015.
 */
public class ReadFromFile {
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        //System.out.println("Path");
        //path = sc.nextLine();
        //System.out.println("Nihe");
        //int nihe = sc.nextInt();

        String filename = "C:/Users/Mario/Desktop/Mario/EIK/Filosoofia/test.txt"; //faili path

        String line = null; // ühe rea kaupa
        try {
            FileReader fileReader = new FileReader(filename); //loeme failist
            BufferedReader bufferedReader = new BufferedReader(fileReader); //fail buffereReaderisse

            while ((line = bufferedReader.readLine())!=null){
                System.out.println(line);
            }
            bufferedReader.close(); //sulgeme faili

        }catch (IOException ex){
            System.out.println("Error reading file"+filename);
        }
    }
}
