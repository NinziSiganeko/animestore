package za.ac.cput.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Pattern;

public class Helper {

    public static boolean isNullOrEmpty(String s) {
        if (s.isEmpty() || s == null) {
            return true;
        }
        return false;
    }

    public static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    public static boolean isValidAdminEmail(String email) {
        if (isNullOrEmpty(email)) return false;

        if (!email.equals(email.toLowerCase())) return false;

        String requiredDomain = "@animestore.co.za";
        return email.endsWith(requiredDomain);
    }

    public static boolean isValidAdminPassword(String password) {
        final String ADMIN_PASSWORD = "naidoo_1@dev";
        return ADMIN_PASSWORD.equals(password);
    }

    public static boolean isValidAmount(double amount) {

        return amount > 0;
    }
    public static LocalDateTime getDate(LocalDateTime date) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        return now;
    }

    public static boolean isValidRating(int rating) {
        if (rating < 1 || rating > 5) {
            return false;
        }
        return true;
    }
    public static boolean isValidCard(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{13,19}");
    }
    public static boolean isValidBankAccount(String account) {
        return account != null && account.matches("\\d{6,20}");
    }
}