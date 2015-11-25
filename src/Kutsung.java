import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Mario on 11/25/2015.
 */
public class Kutsung {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Kas Sifreerimiseks/desifreerimiseks/failist");
        String sone = sc.nextLine();
        if (sone.equals("Sifreerimiseks")) {

            System.out.println("tekst sifreerimiseks");
            String tekst = sc.nextLine();
            System.out.println("Nihe");
            int nihe = sc.nextInt();
            System.out.println(sifreerimiseks.Sifreerimiseks(tekst, nihe));
        } else if (sone.equals("Desifreerimiseks")) {
            System.out.println("tekst desifreerimiseks");
            String tekst = sc.nextLine();
            System.out.println("Nihe");
            int nihe = sc.nextInt();
            System.out.println(Desifreerimiseks.Desifreerimiseks(tekst, nihe));
        } else if (sone.equals("failist")) {
            System.out.println("Path ");
            String path = sc.nextLine();
            System.out.println("Nihe ");
            int nihe = sc.nextInt();
            //System.out.println(ReadFromFile.failistLugemine(path, nihe));
        }
    }
}
