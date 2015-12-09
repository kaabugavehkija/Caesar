package src.MMSiffer;

/**
 * Created by Mario on 11/29/2015.
 */
public class Desifreerimine_Caesar {
    public static String desiffer(String desifreerimiseks, int nihe, String tahestik) {
        //String tahestik = "ABCDEFGHIJKLMNOPRSTUV����";
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
}
