package src;

/**
 * Created by Mario on 11/29/2015.
 */
public class Cipher {
    public static String cipher(String plainText, int key, String alphabet) {
        //Ciphered text
        String chiphered = "";

        for (int i = 0; i < plainText.length(); i++) {
            int positionInAlphabet = alphabet.indexOf(Character.toUpperCase(plainText.charAt(i)));
            int newLocation = (positionInAlphabet + key) % alphabet.length();
            if (newLocation<0){
                newLocation = alphabet.length()+newLocation;
            }
            char newChar = alphabet.charAt(newLocation);
            if (Character.isDigit(plainText.charAt(i))) {
                chiphered += plainText.charAt(i);
            } else if (!Character.isLetterOrDigit(plainText.charAt(i))) {
                chiphered += plainText.charAt(i);
            } else if (Character.isLetter(plainText.charAt(i))) {
                chiphered += newChar;
            }
        }
        return chiphered;
    }
}
