package com.example.farmerassist.api;

public class Request {

    public static class LoginRequest {

        String email,password;

        public LoginRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    }

    public static class RegisterRequest {

        String name,email,password;

        public RegisterRequest(String name, String email, String password) {
            this.name = name;
            this.email = email;
            this.password = password;
        }
    }


    public static class AddproductRequest {
        String product_name;

        public AddproductRequest(String product_name) {
            this.product_name = product_name;
        }
    }

    public static class AddIncomeRequest {
        String source_of_income;
        String farm_income_belong;
        String income_amount;
        String income_date;

        public AddIncomeRequest(String source_of_income, String farm_income_belong, String income_amount, String income_date) {
            this.source_of_income = source_of_income;
            this.farm_income_belong = farm_income_belong;
            this.income_amount = income_amount;
            this.income_date = income_date;
        }
    }
}
