package Valdating;

public class PhoneValidator {
    public static boolean isValidPhone(int phone) {
        String regex = "^\\d{10}$";
        String phoneStr = Integer.toString(phone);
        return phoneStr.matches(regex);
    }
}
