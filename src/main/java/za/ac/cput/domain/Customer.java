package za.ac.cput.domain;

public class Customer extends User {
        private String address;
        private String phoneNumber;

        public Customer() {}

        private Customer(Builder builder) {
            super(builder.userId, builder.username, builder.password, builder.email);
            this.userId = builder.userId;
            this.username = builder.username;
            this.password = builder.password;
            this.email = builder.email;
            this.address = builder.address;
            this.phoneNumber = builder.phoneNumber;

        }

        public String getAddress() {
            return address;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }
        public int getUserId() {
            return userId;
        }
        public String getUsername() {
            return username;
        }
        public String getPassword() {
            return password;
        }
        public String getEmail() {
            return email;
        }

    @Override
    public String toString() {
        return "Customer{" +
                "address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class Builder {
            private int userId;
            private String username;
            private String password;
            private String email;
            private String address;
            private String phoneNumber;

            public Builder setUserId(int userId) {
                this.userId = userId;
                return this;
            }

            public Builder setUsername(String username) {
                this.username = username;
                return this;
            }

            public Builder setPassword(String password) {
                this.password = password;
                return this;
            }

            public Builder setEmail(String email) {
                this.email = email;
                return this;
            }

            public Builder setAddress(String address) {
                this.address = address;
                return this;
            }

            public Builder setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
                return this;
            }

            public Builder copy(Customer customer) {
                this.userId = customer.userId;
                this.username = customer.username;
                this.password = customer.password;
                this.email = customer.email;
                this.address = customer.address;
                this.phoneNumber = customer.phoneNumber;
                return this;
            }

            public Customer build() {
                return new Customer(this);
            }
        }
    }
