package com.example.farmerassist.responses;

public class AddIncomeResponse {
    private boolean success;
    private String message;
    private Data data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public Data getData() {
        return data;
    }

    public class  Data {
        private Income income;

        public Income getIncome() {
            return income;
        }

        public class Income {
            private String source_of_income;
            private String farm_income_belong;
            private String income_amount;
            private String income_date;
            private String updated_at;
            private String created_at;
            private int id;

            public String getSource_of_income() {
                return source_of_income;
            }

            public String getFarm_income_belong() {
                return farm_income_belong;
            }

            public String getIncome_amount() {
                return income_amount;
            }

            public String getIncome_date() {
                return income_date;
            }

            public String getUpdated_at() {
                return updated_at;
            }

            public String getCreated_at() {
                return created_at;
            }

            public int getId() {
                return id;
            }
        }
    }
}
