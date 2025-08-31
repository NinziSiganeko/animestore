package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

public class CustomerFactory {

    public static Customer createCustomer(String username, String name, String password, String email, String address, String phoneNumber) {
        if (Helper.isNullorEmpty(username) ||
                Helper.isNullorEmpty(password) ||
                Helper.isNullorEmpty(name) ||
                Helper.isNullorEmpty(email) ||
                Helper.isNullorEmpty(address) ||
                Helper.isNullorEmpty(phoneNumber)) {
            return null;
        }

        return new Customer.Builder()
                .setUsername(username)
                .setFirstName(name)
                .setPassword(password)
                .setEmail(email)
                .setAddress(address)
                .setPhoneNumber(phoneNumber)
                .build();
    }
}
