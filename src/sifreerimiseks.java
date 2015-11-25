/**
 * Created by Mario on 11/25/2015.
 */
public class sifreerimiseks {
    public static String Sifreerimiseks(String sifreerimiseks, int nihe) {
            String sifreeritud = "";
            String tahestik = "abcdefghijklmnoprstuvõäöü";
            String tahestik_suur = "ABCDEFGHIJKLMNOPRSTUVÕÄÖÜ";

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

            }
        return sifreeritud;
        }
    }
