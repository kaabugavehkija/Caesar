import java.util.Scanner;

/**
 * Created by Mario on 11/21/2015.
 */

import com.sun.xml.internal.fastinfoset.util.CharArray;
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
        System.out.println("sisend kasutajalt");
        String sone = sc.nextLine();
        Scanner ROT = new Scanner(System.in);
        System.out.println("Sisesta ROT");
        int nihe = ROT.nextInt();

        String tahestik = "abcdefghijklmnoprstuv????";
        String sifreeritud =" ";
        for (int i = 0; i < sone.length(); i++) {
            int asukohtTahestikus = tahestik.indexOf(sone.charAt(i));
            int paljuNihutame = (asukohtTahestikus+nihe)%tahestik.length();
            char uusVaartus = tahestik.charAt(paljuNihutame);
            sifreeritud+=uusVaartus;
   }
        System.out.println(sifreeritud);
}
}