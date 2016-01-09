package src;

import javax.swing.*;

/**
 * Created by Mario on 11/29/2015.
 */
public class Decipher {
    public static String decipher(String ciphered, int key, String alphabet) throws CipherException {
        //Deciphered text
        String deciphered = "";

        for (int i = 0; i < ciphered.length(); i++) {
            int positionInAlphabet = alphabet.indexOf(Character.toUpperCase(ciphered.charAt(i)));
            int newLocation = (positionInAlphabet - key) % alphabet.length();
            if (newLocation<0){
                newLocation = alphabet.length()+newLocation;
            }
            char newChar = alphabet.charAt(newLocation);
             if (Character.isLetter(ciphered.charAt(i)) && !alphabet.contains(Character.toString(Character.toUpperCase(ciphered.charAt(i))))){
                 throw new CipherException("test");
            } else if (Character.isLetter(ciphered.charAt(i))) {
                deciphered += newChar;
            } else if (Character.isDigit(ciphered.charAt(i))) {
                deciphered += ciphered.charAt(i);
            } else if (!Character.isLetterOrDigit(ciphered.charAt(i))) {
                deciphered += ciphered.charAt(i);
            }
        }
        return deciphered;
    }
}
