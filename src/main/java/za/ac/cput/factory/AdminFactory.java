package za.ac.cput.factory;

import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

public class AdminFactory {
    public static Admin createAdmin(String username, String name, String password, String email, String address, String phoneNumber) {
        if (Helper.isNullorEmpty(username) ||
                Helper.isNullorEmpty(password) ||
                Helper.isNullorEmpty(name) ||
                Helper.isNullorEmpty(email) ||
                Helper.isNullorEmpty(address) ||
                Helper.isNullorEmpty(phoneNumber)) {
            return null;
        }

        return new Admin.Builder()
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .build();
    }
}