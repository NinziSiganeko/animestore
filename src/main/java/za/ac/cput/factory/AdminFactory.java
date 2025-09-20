package za.ac.cput.factory;

import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

public class AdminFactory {
    public static Admin createAdmin(String username, String name, String password, String email, String address, String phoneNumber) {
        if (Helper.isNullOrEmpty(username) ||
                Helper.isNullOrEmpty(password) ||
                Helper.isNullOrEmpty(name) ||
                Helper.isNullOrEmpty(email) ||
                Helper.isNullOrEmpty(address) ||
                Helper.isNullOrEmpty(phoneNumber)) {
            return null;
        }

        return new Admin.Builder()
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .build();
    }
}