//Admin POJO class
//Author :PV Nakedi
//Date: 04 May 2025

package za.ac.cput.domain;

    public class Admin extends User {


        public Admin() {}

        private Admin(Builder builder) {
            super(builder.userId, builder.username, builder.password, builder.email);
           this.email = builder.email;
            this.userId = builder.userId;
            this.username = builder.username;
            this.password = builder.password;

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
            return "Admin{" +
                    "userId=" + userId +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", email='" + email + '\'' +
                    '}';
        }

        public static class Builder {
            protected int userId;
            protected String username;
            protected String password;
            protected String email;

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

            public Builder copy(Admin admin) {
                this.userId = admin.userId;
                this.username = admin.username;
                this.password = admin.password;
                this.email = admin.email;
                return this;
            }

            public Admin build() {
                return new Admin(this);
            }
        }
    }

