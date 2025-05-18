package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;
import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

public class CustomerFactory {
        public static Customer createCustomer(int userId, String username, String password, String email, String address, String phoneNumber) {
            if(Helper.isNullorEmpty(username) ||
                    Helper.isNullorEmpty(password) ||
                    Helper.isNullorEmpty(address) ||
                    Helper.isNullorEmpty(phoneNumber))

                return null;
            if(!Helper.isNullorEmpty(email))
                return null;

            return new Customer.Builder()
                    .setUserId(userId)
                    .setUsername(username)
                    .setPassword(password)
                    .setEmail(email)
                    .setAddress(address)
                    .setPhoneNumber(phoneNumber)
                    .build();
        }
    }
