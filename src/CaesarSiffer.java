import java.util.Scanner;

/**
 * Created by Mario on 11/25/2015.
 */
public class CaesarSiffer {

    public static String tahestik = "ABCDEFGHIJKLMNOPRSTUVõäöü";


    public static String siffer(String sifreerimiseks, int nihe) {
        String sifreeritud = "";

        for (int i = 0; i < sifreerimiseks.length(); i++) {
            int asukohtTahestikus = tahestik.indexOf(Character.toUpperCase(sifreerimiseks.charAt(i)));
            int paljuNihutame = (asukohtTahestikus + nihe) % tahestik.length();
            char uusVaartus = tahestik.charAt(paljuNihutame);
            if (Character.isDigit(sifreerimiseks.charAt(i))) {
                sifreeritud += sifreerimiseks.charAt(i);
            } else if (!Character.isLetterOrDigit(sifreerimiseks.charAt(i))) {
                sifreeritud += sifreerimiseks.charAt(i);
            } else if (Character.isLetter(sifreerimiseks.charAt(i))) {
                sifreeritud += uusVaartus;
            }

        }
        return sifreeritud;
    }

    public static String desiffer(String desifreerimiseks, int nihe) {
        String desifreeritud = "";

        for (int i = 0; i < desifreerimiseks.length(); i++) {
            int asukohtTahestikus = tahestik.indexOf(Character.toUpperCase(desifreerimiseks.charAt(i)));
            int paljuNihutame = (asukohtTahestikus - nihe) % tahestik.length();
            if (paljuNihutame<0){
                paljuNihutame = tahestik.length()+paljuNihutame;
            }
            char uusVaartus = tahestik.charAt(paljuNihutame);
            if (Character.isDigit(desifreerimiseks.charAt(i))) {
                desifreeritud += desifreerimiseks.charAt(i);
            } else if (!Character.isLetterOrDigit(desifreerimiseks.charAt(i))) {
                desifreeritud += desifreerimiseks.charAt(i);
            } else if (Character.isLetter(desifreerimiseks.charAt(i))) {
                desifreeritud += uusVaartus;
            }
        }
        return desifreeritud;
    }

    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Kas Sifreerimiseks/desifreerimiseks");
        String sone = sc.nextLine();
        if (sone.equals("Sifreerimiseks")){
            System.out.println("tekst sifreerimiseks");
            String tekst = sc.nextLine();
            System.out.println("Nihe");
            int nihe = sc.nextInt();
            System.out.println(siffer(tekst,nihe));
        }else if(sone.equals("desifreerimiseks")){
            System.out.println("tekst desifreerimiseks");
            String tekst = sc.nextLine();
            System.out.println("Nihe");
            int nihe = sc.nextInt();
            System.out.println(desiffer(tekst,nihe));
        }
    }
}