package com.example.farmerassist.responses;

public class EditLiveStocksResponse {
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

    public class Data {
        private int id;
        private String name;
        private String birthdate;
        private String color;
        private String vaccinated_date;
        private String image;
        private String feeding_time;
        private String gender;
        private String created_at;
        private String updated_at;
        private String vaccinated;
        private String doctor_name;
        private String age;
        private String prescription;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public String getColor() {
            return color;
        }

        public String getVaccinated_date() {
            return vaccinated_date;
        }

        public String getImage() {
            return image;
        }

        public String getFeeding_time() {
            return feeding_time;
        }

        public String getGender() {
            return gender;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public String getVaccinated() {
            return vaccinated;
        }

        public String getDoctor_name() {
            return doctor_name;
        }

        public String getAge() {
            return age;
        }

        public String getPrescription() {
            return prescription;
        }
    }
}
