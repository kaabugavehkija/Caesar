import java.io.*;
import java.util.Scanner;

/**
 * Created by Mario on 11/26/2015.
 */
public class failiLugemine {
    public static void main(String path) throws IOException {
        File f = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(f));
        String line = br.readLine();
        while (line != null) {
            System.out.println(line);
            line = br.readLine();
        }br.close();
    }
}

