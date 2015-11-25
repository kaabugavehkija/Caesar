/**
 * Created by Mario on 11/25/2015.
 */
public class Desifreerimiseks {
    public static String Desifreerimiseks (String desifreerimiseks, int nihe) {
        String desifreeritud = "";
        String tahestik = "abcdefghijklmnoprstuvõäöü";
        String tahestik_suur = "ABCDEFGHIJKLMNOPRSTUVÕÄÖÜ";

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

        }
        return desifreeritud;
    }
}
