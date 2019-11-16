package br.com.mobile2you.m2ybase.utils.validations;

public class IsNotEmpty {

    public static boolean isValid(String text) {
        return !text.isEmpty();
    }
}
