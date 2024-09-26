package com.example.farmerassist.responses;

import java.util.List;

public class FarmSetupResponse {
    private boolean  success;
    private String   message;
    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public class Data {
        private int id;
        private String name_of_product;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public String getName_of_product() {
            return name_of_product;
        }

        public String getCreated_at() {
            return created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }
    }
}
