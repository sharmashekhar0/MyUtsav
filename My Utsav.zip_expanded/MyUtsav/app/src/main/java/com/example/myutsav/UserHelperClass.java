
    package com.example.myutsav;

    public class UserHelperClass {
        String fullname,email,phone,date,password;

        public UserHelperClass(String email, String password,String fullname, String phone, String date) {
            this.fullname = fullname;
            this.password=password;
            this.email = email;
            this.phone = phone;
            this.date = date;

        }


        public String getFullname() {
            return fullname;
        }

        public void setFullname(String fullname) {
            this.fullname = fullname;
        }
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
