package com.example.farmerassist.responses;

import java.util.ArrayList;
import java.util.List;

public class CropsActivityResponse {
    private boolean success;
    private String message;
    private ArrayList<Data> data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public class Data {
        private int id;
        private String crop;
        private String activity;
        private String start_date;
        private String end_date;
        private String type_of_planting;
        private String growth_period;
        private String soil_type;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public String getCrop() {
            return crop;
        }

        public String getActivity() {
            return activity;
        }

        public String getStart_date() {
            return start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public String getType_of_planting() {
            return type_of_planting;
        }

        public String getGrowth_period() {
            return growth_period;
        }

        public String getSoil_type() {
            return soil_type;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }
}
