package com.example.farmerassist.responses;

import java.util.List;

public class GetPlanticTypeCorp {

    private boolean success;
    private String message;
    private List<String> plantingTypes;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getPlantingTypes() {
        return plantingTypes;
    }
}
