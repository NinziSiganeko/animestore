package za.ac.cput.util;

import za.ac.cput.domain.Customer;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.regex.Pattern;

public class Helper {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isNullorEmpty(String s) {
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

    public static boolean isValidId(Long paymentId){
        return paymentId != null && paymentId > 0;
    }
    public static boolean isValidCard(String cardNumber) {
        if (isNullOrEmpty(cardNumber)) return false;
        return Pattern.matches("^\\d{16}$", cardNumber);
    }
    public static boolean isValidAmount(double amount) {

        return amount > 0;
    }
    public static LocalDateTime getPaymentDate(LocalDateTime paymentDate) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC"));
        return now;
    }



}


