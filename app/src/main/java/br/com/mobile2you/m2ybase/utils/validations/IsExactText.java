package br.com.mobile2you.m2ybase.utils.validations;

public class IsExactText {

    public static boolean isValid(String text, String otherText) {
        return text.equals(otherText);
    }

}
