package br.com.joaopaulosj.vanhackathon2019.utils.validations;

public class IsNotEmpty {

    public static boolean isValid(String text) {
        return !text.isEmpty();
    }
}
