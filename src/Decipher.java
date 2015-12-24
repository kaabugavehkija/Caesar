package src;

/**
 * Created by Mario on 11/29/2015.
 */
public class Decipher {
    public static String decipher(String ciphered, int key, String alphabet) {
        //Deciphered text
        String deciphered = "";

        for (int i = 0; i < ciphered.length(); i++) {
            int positionInAlphabet = alphabet.indexOf(Character.toUpperCase(ciphered.charAt(i)));
            int newLocation = (positionInAlphabet - key) % alphabet.length();
            if (newLocation<0){
                newLocation = alphabet.length()+newLocation;
            }
            char newChar = alphabet.charAt(newLocation);
            if (Character.isDigit(ciphered.charAt(i))) {
                deciphered += ciphered.charAt(i);
            } else if (!Character.isLetterOrDigit(ciphered.charAt(i))) {
                deciphered += ciphered.charAt(i);
            } else if (Character.isLetter(ciphered.charAt(i))) {
                deciphered += newChar;
            }
        }
        return deciphered;
    }
}
