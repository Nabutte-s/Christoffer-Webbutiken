package Valdating;


public class NameValidator {
    public static boolean isValidName(String name) {
        return name.matches("^[A-Za-z\\s]+$");
    }
}
