package za.ac.cput.util;

import za.ac.cput.domain.Customer;

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
        }


