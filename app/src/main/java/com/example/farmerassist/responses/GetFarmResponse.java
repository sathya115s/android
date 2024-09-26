package com.example.farmerassist.responses;

import com.example.farmerassist.modules.GetFarm;

import java.util.ArrayList;

public class GetFarmResponse {



    boolean success;
String message;
ArrayList<GetFarm> data;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public ArrayList<GetFarm> getData() {
        return data;
    }
}
