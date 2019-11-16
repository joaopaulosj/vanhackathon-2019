package br.com.joaopaulosj.vanhackathon2019.utils.validations;

public class IsExactText {

    public static boolean isValid(String text, String otherText) {
        return text.equals(otherText);
    }

}
