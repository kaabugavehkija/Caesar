package MMSiffer;

/**
 * Created by Mario on 11/29/2015.
 */
public class Sifreerimine_Caesar {
    public static String siffer(String sifreerimiseks, int nihe) {
        String tahestik = "ABCDEFGHIJKLMNOPRSTUVõäöü";
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
}
