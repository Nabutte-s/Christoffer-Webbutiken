package Valdating;

public class AdressValidator {
        public static boolean isValidAdress(String adress) {
            String regex = "^[a-zA-Z0-9\\s,.-]+$";
            return adress.matches(regex);
        }

}
