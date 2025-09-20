package za.ac.cput.util;

import za.ac.cput.domain.Customer;


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
        if (isValidEmail(email)) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }

    //    public static boolean isValidCard(String cardNumber) {
//        if (isNullOrEmpty(cardNumber)) return false;
//        return Pattern.matches("^\\d{16}$", cardNumber);
//    }
    public static boolean isValidAmount(double amount) {

        return amount > 0;
    }
    public static LocalDateTime getPaymentDate(LocalDateTime paymentDate) {
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