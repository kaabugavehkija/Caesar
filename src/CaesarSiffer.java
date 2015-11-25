import java.util.Scanner;

/**
 * Created by Mario on 11/25/2015.
 */
public class CaesarSiffer {

    public static String tahestik = "abcdefghijklmnoprstuvõäöü";
    public static String tahestik_suur = "ABCDEFGHIJKLMNOPRSTUVÕÄÖÜ";


    public static String siffer(String sifreerimiseks, int nihe) {
        String sifreeritud = "";

        for (int i = 0; i < sifreerimiseks.length(); i++) {
            int asukohtTahestikus = tahestik.indexOf(sifreerimiseks.charAt(i));
            int asukohtTahestikus_suur = tahestik_suur.indexOf(sifreerimiseks.charAt(i));
            int paljuNihutame = (asukohtTahestikus + nihe) % tahestik.length();
            int paljuNihutame_suur = (asukohtTahestikus_suur + nihe) % tahestik.length();
            char uusVaartus = tahestik.charAt(paljuNihutame);
            char uusVaartus_suur = tahestik.charAt(paljuNihutame_suur);
            if (Character.isDigit(sifreerimiseks.charAt(i))) {
                sifreeritud += sifreerimiseks.charAt(i);
            } else if (!Character.isLetterOrDigit(sifreerimiseks.charAt(i))) {
                sifreeritud += sifreerimiseks.charAt(i);
            } else if (Character.isUpperCase(sifreerimiseks.charAt(i))) {
                sifreeritud += Character.toUpperCase(uusVaartus_suur);
            } else if (Character.isLetter(sifreerimiseks.charAt(i))) {
                sifreeritud += uusVaartus;
            }

        }return sifreeritud;
    }
    public static String desiffer(String desifreerimiseks, int nihe){
        String desifreeritud ="";

        for (int i = 0; i < desifreerimiseks.length(); i++) {
            int asukohtTahestikus = tahestik.indexOf(desifreerimiseks.charAt(i));
            int asukohtTahestikus_suur = tahestik_suur.indexOf(desifreerimiseks.charAt(i));
            int paljuNihutame = (asukohtTahestikus + nihe) % tahestik.length();
            int paljuNihutame_suur = (asukohtTahestikus_suur + nihe) % tahestik.length();
            char uusVaartus = tahestik.charAt(paljuNihutame);
            char uusVaartus_suur = tahestik.charAt(paljuNihutame_suur);
            if (Character.isDigit(desifreerimiseks.charAt(i))) {
                desifreeritud += desifreerimiseks.charAt(i);
            } else if (!Character.isLetterOrDigit(desifreerimiseks.charAt(i))) {
                desifreeritud += desifreerimiseks.charAt(i);
            } else if (Character.isUpperCase(desifreerimiseks.charAt(i))) {
                desifreeritud += Character.toUpperCase(uusVaartus_suur);
            } else if (Character.isLetter(desifreerimiseks.charAt(i))) {
                desifreeritud += uusVaartus;
            }

        }return desifreeritud;
    }
    public static void main(String[]args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Kas Sifreerimiseks/desifreerimiseks?");
        String sone = sc.nextLine();
        if (sone.equals("Sifreerimiseks")){
            System.out.println("tekst sifreerimiseks");
            String tekst = sc.nextLine();
            System.out.println("Nihe");
            int nihe = sc.nextInt();
            System.out.println(siffer(tekst,nihe));
        }else if(sone.equals("Desifreerimiseks")){
            System.out.println("tekst desifreerimiseks");
            String tekst = sc.nextLine();
            System.out.println("Nihe");
            int nihe = sc.nextInt();
            System.out.println(desiffer(tekst,nihe));
        }
    }
}
