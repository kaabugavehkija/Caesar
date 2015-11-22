import java.util.Scanner;

/**
 * Created by Mario on 11/21/2015.
 */

import javafx.beans.property.ListProperty;

        import java.io.BufferedReader;
        import java.io.FileNotFoundException;
        import java.io.FileReader;
        import java.util.ArrayList;
        import java.util.List;
        import java.util.Scanner;

/**
 * Created by Mario on 11/8/2015.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("sone");
        String sone = sc.nextLine();
        StringBuilder sb = new StringBuilder(); //strinbuilder

        String tahestik = "abcdefghijklmnoprstuvõäöü";
        for (int i = 0; i < sone.length(); i++) {
            // System.out.println(sone.length()+" pikkus");
            tahestik.indexOf(sone.charAt(i)); // sisestuse tähtede algpositsioon tähestikus")
            if (sone.charAt(i) == ' ') {
                    sb.append(" ");
                } else if (sone.charAt(i) == ',') {
                    sb.append(",");
                } else if (sone.charAt(i) == '.') {
                    sb.append(".");
                } else if (sone.charAt(i) == '0') {
                    sb.append("0");
                }else if (sone.charAt(i) == '1') {
                    sb.append("1");
                }else if (sone.charAt(i) == '2') {
                    sb.append("2");
                }else if (sone.charAt(i) == '3') {
                    sb.append("3");
                }else if (sone.charAt(i) == '4') {
                    sb.append("4");
                }else if (sone.charAt(i) == '5') {
                    sb.append("5");
                }else if (sone.charAt(i) == '6') {
                    sb.append("6");
                }else if (sone.charAt(i) == '7') {
                    sb.append("7");
                }else if (sone.charAt(i) == '8') {
                    sb.append("8");
                }else if (sone.charAt(i) == '9') {
                    sb.append("9");
            }else
                sb.append(tahestik.charAt(tahestik.indexOf(sone.charAt(i)) + 1)); //nihutatud positsioon

        }
        System.out.print(sb + " sifreeritud");
    }
}
